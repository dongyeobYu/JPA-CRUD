package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.domain.Post;
import JPABOARD.JPACRUD.repository.CommentRepository;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.repository.PostRepository;
import JPABOARD.JPACRUD.security.MemberDetails;
import JPABOARD.JPACRUD.service.MemberService;
import JPABOARD.JPACRUD.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final CommentRepository commentRepository;


    @GetMapping("/post/new")
    public String postPage(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/new";
    }

    @PostMapping("/post/new")
    public String writePost(@Valid PostForm postForm, BindingResult bindingResult, @AuthenticationPrincipal MemberDetails memberDetails, Model model) {

        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        Member member = memberRepository.findByNickname(memberDetails.getMember().getNickname());
        Post post = new Post(member, postForm.getTitle(), postForm.getContent());
        postService.write(post);
        return "redirect:/";
    }


    @GetMapping("/post/postList")
    public String postListPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("isMe", memberDetails.getMember().getId());

        return "post/postList";
    }

    @GetMapping("/post/{postId}/updatePost")
    public String getUpdatePostPage(@PathVariable("postId") Long postId, Model model) {
        Optional<Post> id = postRepository.findById(postId);

        PostForm postForm = new PostForm(
                id.get().getMember().getNickname(),
                id.get().getTitle(),
                id.get().getContent(),
                id.get().getId()
        );

        model.addAttribute("postForm", postForm);
        return "post/UpdateForm";
    }

    @PostMapping("/post/{postId}/updatePost")
    public String updatePost(@PathVariable("postId") Long postId, @ModelAttribute("form") PostForm form) {
        postService.updatePost(postId, form.getTitle(), form.getContent());
        return "redirect:/post/postList";
    }

    @PostMapping("/post/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postRepository.deleteById(postId);
        return "post/postList";
    }

    @GetMapping("/post/{id}/page")
    public String postPage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        Optional<Post> post = postRepository.findById(id);
        model.addAttribute("member", memberRepository.findById(memberDetails.getMember().getId()));
        model.addAttribute("post", post);
        model.addAttribute("comment", commentRepository.findAll());
        model.addAttribute("isMe", memberDetails.getMember().getId());

        return "post/postPage";

    }
}

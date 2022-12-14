package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.domain.Post;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.repository.PostRepository;
import JPABOARD.JPACRUD.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/post/new")
    public String postPage(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/new";
    }

    @PostMapping("/post/new")
    public String writePost(@Valid PostForm postForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "post/new";
        }

        Member member = memberRepository.findByNickname(postForm.getWriter());
        Post post = new Post(member, postForm.getTitle(), postForm.getContent());
        postService.write(post);
        return "redirect:/";
    }


    @GetMapping("/post/postList")
    public String postListPage(Model model) {
        model.addAttribute("posts", postRepository.findAll());
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
    public String deletePost(@PathVariable("postId") Long postId){
        postRepository.deleteById(postId);
        return "post/postList";
    }
}

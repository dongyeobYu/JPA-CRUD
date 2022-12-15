package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Comment;
import JPABOARD.JPACRUD.domain.Post;
import JPABOARD.JPACRUD.repository.CommentRepository;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.repository.PostRepository;
import JPABOARD.JPACRUD.service.MemberService;
import JPABOARD.JPACRUD.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentRepository commentRepository;

    @PostMapping("/post/{id}/comment")
    public String writeComment(@PathVariable("id") Long id, @ModelAttribute("form") CommentForm commentForm) {
        Comment comment = new Comment(postRepository.findById(id).get(), memberRepository.findById(commentForm.getMemberId()).get(), commentForm.getContent());
        commentRepository.save(comment);

        return "/post/" + id + "/page";
    }
}

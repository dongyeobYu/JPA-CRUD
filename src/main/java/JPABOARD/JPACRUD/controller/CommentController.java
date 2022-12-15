package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Comment;
import JPABOARD.JPACRUD.domain.Post;
import JPABOARD.JPACRUD.repository.CommentRepository;
import JPABOARD.JPACRUD.repository.MemberRepository;
import JPABOARD.JPACRUD.repository.PostRepository;
import JPABOARD.JPACRUD.security.MemberDetails;
import JPABOARD.JPACRUD.service.CommentService;
import JPABOARD.JPACRUD.service.MemberService;
import JPABOARD.JPACRUD.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/post/{id}/comment")
    public String writeComment(@PathVariable("id") Long id, @ModelAttribute("form") CommentForm commentForm) {
        Comment comment = new Comment(postRepository.findById(id).get(), memberRepository.findById(commentForm.getMemberId()).get(), commentForm.getContent());
        commentRepository.save(comment);

        return "redirect:/post/" + id + "/page";
    }

    @PostMapping("/post/{id}/{commentId}/update")
    public String updateComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId, @ModelAttribute("form") CommentForm commentForm, @AuthenticationPrincipal MemberDetails memberDetails) {
        Optional<Comment> byId = commentRepository.findById(commentId);
        commentService.updateComment(byId.get().getId(), commentForm.getContent());
        return "redirect:/post/" + id + "/page";
    }

    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        Long id = commentRepository.findById(commentId).get().getPostId().getId();
        commentRepository.deleteById(commentId);
        return "redirect:/post/" + id + "/page";
    }
}

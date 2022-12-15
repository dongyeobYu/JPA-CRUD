package JPABOARD.JPACRUD.service;

import JPABOARD.JPACRUD.domain.Comment;
import JPABOARD.JPACRUD.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void updateComment(Long id , String comment){
        Optional<Comment> byId = commentRepository.findById(id);
        byId.get().Comment(comment);
    }
}

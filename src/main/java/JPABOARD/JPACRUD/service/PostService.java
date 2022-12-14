package JPABOARD.JPACRUD.service;

import JPABOARD.JPACRUD.domain.Post;
import JPABOARD.JPACRUD.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long write(Post post){
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void updatePost(Long postId , String title, String content){

        Optional<Post> post = postRepository.findById(postId);
        post.get().changePost(title, content);
    }
}

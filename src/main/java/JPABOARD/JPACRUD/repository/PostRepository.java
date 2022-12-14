package JPABOARD.JPACRUD.repository;

import JPABOARD.JPACRUD.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

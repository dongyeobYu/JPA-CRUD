package JPABOARD.JPACRUD.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post postId;

    private String writer;

    private String comment;

    private LocalDateTime localDateTime;

}

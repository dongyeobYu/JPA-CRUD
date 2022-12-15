package JPABOARD.JPACRUD.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    private String comment;

    private LocalDateTime localDateTime;

    public Comment(Post postId, Member memberId, String comment) {
        this.postId = postId;
        this.memberId = memberId;
        this.comment = comment;
        this.localDateTime = LocalDateTime.now().withNano(0);
    }
}

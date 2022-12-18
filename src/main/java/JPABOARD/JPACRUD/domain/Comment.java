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

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne
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


    public void setPostId(Post post){
        this.postId = post;
        postId.getComments().add(this);
    }

    public void Comment(String comment){
        this.localDateTime = LocalDateTime.now().withNano(0);
    }
}

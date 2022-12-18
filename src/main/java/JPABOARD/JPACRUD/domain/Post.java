package JPABOARD.JPACRUD.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private LocalDateTime localDateTime;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public Post(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.localDateTime = LocalDateTime.now().withNano(0);
    }


    public void changePost(String title, String content){
        this.title = title;
        this.content = content;
        this.localDateTime = LocalDateTime.now().withNano(0);
    }

}

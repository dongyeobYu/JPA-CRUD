package JPABOARD.JPACRUD.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    public Member(String name, String nickname, String password, Address address) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
    }

    public Member(String name, String nickname, String password) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
    }
}

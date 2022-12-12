package JPABOARD.JPACRUD.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    public Member(String name, String nickname, String password) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
    }


}

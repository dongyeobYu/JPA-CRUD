package JPABOARD.JPACRUD.domain;

import JPABOARD.JPACRUD.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;


    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Comment> comments = new ArrayList<>();

    public Member(String name){
        this.name = name;
    }

    public Member(String name, String nickname, String password, Address address, Role role) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public Member(String name, String nickname, String password, Role role) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public void changMember(Address address){
        this.address = address;
    }

}

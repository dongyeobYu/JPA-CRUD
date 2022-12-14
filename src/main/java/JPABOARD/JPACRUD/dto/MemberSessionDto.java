package JPABOARD.JPACRUD.dto;

import JPABOARD.JPACRUD.domain.Address;
import JPABOARD.JPACRUD.domain.Member;
import JPABOARD.JPACRUD.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSessionDto implements Serializable {

    private Long id;
    private String name;
    private String nickname;
    private String password;
    private Address address;
    private Role role;

    public MemberSessionDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
        this.address = member.getAddress();
        this.role = member.getRole();
    }

}

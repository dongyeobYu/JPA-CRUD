package JPABOARD.JPACRUD.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    private String city;
    private String zipcode;
    private String street;

}

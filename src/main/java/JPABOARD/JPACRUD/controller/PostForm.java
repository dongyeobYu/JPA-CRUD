package JPABOARD.JPACRUD.controller;

import JPABOARD.JPACRUD.domain.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    @NotEmpty(message = "작성자는 꼭 입력해야합니다.")
    private String writer;

    @NotEmpty(message = "제목은 꼭 입력해야합니다.")
    private String title;

    @NotEmpty(message = "내용은 꼭 입력해야합니다.")
    private String content;

    private Long id;


}

package JPABOARD.JPACRUD.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentForm {

    private Long memberId;
    private Long postId;
    @NotEmpty(message = "댓글을 입력해주세요.")
    private String content;
    private LocalDateTime dateTime;


}

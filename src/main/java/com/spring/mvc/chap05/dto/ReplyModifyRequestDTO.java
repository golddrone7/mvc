package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ReplyModifyRequestDTO {
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long bno;
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long rno;
    @NotBlank
    private String text;

    public Reply makeEntity(){
        return Reply.builder()
                .replyNo(this.rno)
                .boardNo(this.rno)
                .replyText(this.text)
                .build();
    }

}

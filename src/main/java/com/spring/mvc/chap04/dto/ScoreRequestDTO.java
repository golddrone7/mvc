package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
// jsp input속성의 name이랑 맴버변수랑 맞춰야함 , getter setter noargs는 필수임
public class ScoreRequestDTO {
    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수
}

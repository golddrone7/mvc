package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// 응답 데이터는 한번 세팅이 되면 고정되야 하니까 final 설계
@RequiredArgsConstructor // 파이널 필드 초기화
@Getter @ToString @EqualsAndHashCode
public class ScoreListResponseDTO {
    private final int stuNum;
    private final String maskingName; // 첫글자 빼고 *처리
    private final double average;
    private final Grade grade;


    public ScoreListResponseDTO(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getName());
        this.average = s.getAverage();
        this.grade = s.getGrade();
    }
    // 첫글자만 빼고 다 *처리
    private String makeMaskingName(String name) {
        String maskingName = String.valueOf(name.charAt(0));
        for (int i = 1; i < name.length(); i++) {
            maskingName += "*";
        }
        return maskingName;
    }

}

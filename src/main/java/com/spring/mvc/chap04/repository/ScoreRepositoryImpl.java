package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.spring.mvc.chap04.entity.Grade.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

@Repository // 스프링 빈 등록 : 객체의 생성의 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository{

    // key: 학번, value: 성적정보
    // 메모리에 딱 1개만 존재하도록 static 설정
    // 메모리구조가 딱 정해지면(해시맵) 바꾸지 못하게 final 진행
    // 내부에서 사용할 것 private
    private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호, 공유되어야 됨
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Score stu1 = new Score("뽀로로", 100, 50, 70, ++sequence, 0, 0, A);
        Score stu2 = new Score("춘식이", 33, 56, 12, ++sequence, 0, 0, A);
        Score stu3 = new Score("대길이", 88, 12, 0, ++sequence, 0, 0, A);

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }


    @Override
    public List<Score> findAll() {
        return scoreMap.values()
                .stream()
                .sorted(comparing(Score::getStuNum))
                .collect(toList());
    }
    @Override
    public boolean save(Score score) {
        if(scoreMap.containsKey(score.getStuNum())){ // 학번이 이미 포함되어있으면 false
            return false;
        }
        score.setStuNum(++sequence);
        scoreMap.put(score.getStuNum(), score);
        System.out.println(findAll());
        return true;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        if(!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }
}

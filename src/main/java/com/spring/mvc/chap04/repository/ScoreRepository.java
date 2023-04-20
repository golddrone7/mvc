package com.spring.mvc.chap04.repository;


import com.spring.mvc.chap04.entity.Score;

import java.util.List;

// 역할 명세(추상화) :
// 성적 정보를 잘 저장하고 조회하고 삭제하고 수정해야 한다.
// 그래서 어디에 저장하는 건데?
// 어디에서 조회하니? 어디에서 삭제하니?
public interface ScoreRepository {

    // 성적 정보 전체 목록 조회
    List<Score> findAll();

//    실무적으로는 인터페이스를 설계하면 다 추가해야 되기 때문에
    // default 인터페이스를 사용하자
    // 그럼 오버라이딩 강제를 방지할 수 있음, 사이드 이펙트 방지
    default List<Score> findSortName(){return null;}
    default List<Score> findSortAvg(){return null;}
    // 성적 정보 등록
    boolean save(Score score);

    // 성적 정보 한개 삭제
    boolean deleteByStuNum(int stuNum);

    // 성적 정보 개별 조회
    Score findByStuNum(int stuNum);



}

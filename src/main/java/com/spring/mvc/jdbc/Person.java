package com.spring.mvc.jdbc;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

// 엔터티 : 이 클래스는 데이터베이스 테이블과의 연동을 위한 객체
// DB table의 컬럼과 1:1로 매칭되는 필드를 적어주세요.
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private long id;
    private String personName; // 이름 룰에 대해서도 한가지 말씀드리자면 컬럼은 데이터베이스는 카멀케이스를 쓸 수가 없어서 스네이크 케이스를 쓰지만 자바는 자바스럽게 캐멀케이스로 쓰자 관례
    private int personAge;

    public Person(ResultSet rs) throws SQLException {
        this.id =  rs.getLong("id");
        this.personName = rs.getString("person_name");
        this.personAge = rs.getInt("person_age");
    }
}

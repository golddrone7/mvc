package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM tbl_score";
        switch (sort){
            case "num":
                sql += " ORDER BY stu_num";
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Score(rs));
       // return ScoreRepository.super.findAll(sort);
    }

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, score.getStuName(), score.getKor(), score.getEng(), score.getMath(), score.getStuNum(),
                score.getTotal(), score.getAverage(), String.valueOf(score.getGrade()));
        return result==1;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        String sql = "DELETE FROM tbl_score where stu_num = ?";
        int result = jdbcTemplate.update(sql, stuNum);
        return result==1;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        String sql = "SELECT * FROM tbl_score where stu_num = ?";
        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> new Score(rs) , stuNum);

    }
}

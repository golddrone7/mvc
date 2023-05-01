package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository {

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    public ScoreJdbcRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> findAll() {
        List<Score> scoreList = null;
        try(Connection conn = DriverManager.getConnection(url, username,password)){
            String sql = "SELECT * FROM tbl_score";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                if(scoreList==null) scoreList = new ArrayList<>();
                Score s = new Score(rs);
                scoreList.add(s);
            }
            return scoreList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        List<Score> scoreList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM tbl_score";
            switch (sort){
                case "num":
                    sql += " order by stu_num";
                    break;
                case "name":
                    sql += " order by stu_name";
                    break;
                case "avg":
                    sql += " order by average desc";
                    break;
            }


            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                scoreList.add(new Score(rs));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public boolean save(Score score) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);

            String sql = "INSERT INTO tbl_score " +
                    " (stu_name, kor, eng, math, total, average, grade) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getStuName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, String.valueOf(score.getGrade()));

            int result = pstmt.executeUpdate(); // 성공시 1, 실패시 0

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {

        try(Connection conn = DriverManager.getConnection(url, username, password)){
            conn.setAutoCommit(false);
            String sql = "DELETE FROM tbl_score where stu_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);
            int result = pstmt.executeUpdate();
            if(result>0){
                conn.commit();
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Score findByStuNum(int stuNum) {

        try(Connection conn = DriverManager.getConnection(url, username, password)){
            conn.setAutoCommit(false);
            String sql = "select * from tbl_score where stu_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Score score = new Score(rs);
                return score;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
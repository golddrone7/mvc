package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardSpring")
@RequiredArgsConstructor
public class BoardSpringRepository implements BoardRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum)-> new Board(rs), boardNo);
    }

    @Override
    public boolean save(Board board) {
        String sql = "INSERT INTO tbl_board values(?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, board.getBoardNo(), board.getTitle(), board.getContent(), board.getViewCount(), board.getRegDateTime());
        return result==1;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        int result = jdbcTemplate.update(sql, boardNo);
        return result==1;
    }
}

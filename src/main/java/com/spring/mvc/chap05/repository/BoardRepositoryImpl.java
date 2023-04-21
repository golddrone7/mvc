package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    private final static Map<Integer, Board> boardMap;

    private static int sequence;
    static {
        boardMap = new HashMap<>();
        for (int i = 1; i < 22; i++) {
          boardMap.put(i, new Board(++sequence,"룰루랄라~~"+i, "어쩌구 저쩌구 ....(30자 후 줄임처리)", 0, LocalDateTime.now()));
        }
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }

    @Override
    public Board findOne(int boardNo) {
        Board board = boardMap.get(boardNo);

        return board;
    }

    @Override
    public boolean save(Board board) {
        return false;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        if(!boardMap.containsKey(boardNo)) return false;
        boardMap.remove(boardNo);
        return true;
    }

    @Override
    public void save(String title, String content) {
        Board board = new Board(++sequence,title, content, 0, LocalDateTime.now());
        boardMap.put(sequence, board);
    }



    public static int getSequence() {
        return sequence;
    }
}

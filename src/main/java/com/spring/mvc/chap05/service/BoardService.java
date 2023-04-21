package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시판 전체 조회를 하는 기능입니다.
    public List<BoardDTO> boardFindAll(String sort, String searchTitle) {
        List<BoardDTO> list = new ArrayList<>();
        for (Board board : boardRepository.findAll()) {
            list.add(new BoardDTO(board));
        }
        switch (sort){
            case "numSort":
                list = list.stream().sorted(Comparator.comparing(BoardDTO::getBoardNo).reversed())
                        .collect(Collectors.toList());
                break;
            case "viewSort":
                list = list.stream().sorted(Comparator.comparing(BoardDTO::getViewCount).reversed())
                        .collect(Collectors.toList());
        }
        if(!searchTitle.equals("")){
            list = list.stream().filter(p -> p.getTitle().contains(searchTitle))
                    .collect(Collectors.toList());
        }

        return list;
    }
    // 게시판을 삭제하는 기능입니다.
    public boolean delete(int boardNo) {
        return boardRepository.deleteByNo(boardNo);
    }


    // 글을 저장하는 기능
    public void save(String title, String content) {
        boardRepository.save(title,content);
    }

    public Board detail(int boardNo) {
        Board board = boardRepository.findOne(boardNo);
        board.setViewCount(board.getViewCount()+1);
        return board;
    }

    public void update(int boardNo, String title, String content) {
        Board board = boardRepository.findOne(boardNo);
        board.setContent(content);
        board.setTitle(title);
    }


    // 중간처리 기능 자유롭게 사용


}

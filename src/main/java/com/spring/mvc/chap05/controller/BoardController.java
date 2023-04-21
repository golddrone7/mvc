package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller   // dispatcherServlet
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;


    /*
     전체 게시판 조회 기능
     /board/list : GET
     */
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "numSort") String sort,@RequestParam(defaultValue = "")String searchTitle){
        List<BoardDTO> boardList= boardService.boardFindAll(sort, searchTitle);
        model.addAttribute("boardList", boardList);
        return "chap05/list";
    }


    /*
     특정 게시판 삭제 기능
     /board/delete : GET
     */
    @GetMapping("/delete")
    public String delete(int boardNo){
        boardService.delete(boardNo);
        return "redirect:/board/list";
    }

    @GetMapping("/write")
    public String wrtie(){
        return "chap05/write";
    }

    @PostMapping("/write")
    public String save(String title, String content){
        boardService.save(title, content);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public String detail(int boardNo, Model model){
        Board board = boardService.detail(boardNo);
        model.addAttribute("board", board);
        return "chap05/detail";
    }
    @GetMapping("/back")
    public String back(){
        return "redirect:/board/list";
    }


    @GetMapping("/sort")
    public String sort(String sort){
        return "redirect:/board/list?sort="+sort;
    }

    @GetMapping("update")
    public String update(int boardNo, Model model){
        Board board = boardService.detail(boardNo);
        model.addAttribute("board", board);
        return "/chap05/update";
    }
    @PostMapping("update")
    public String update(int boardNo,String title, String content){
        boardService.update(boardNo, title, content);
        return "redirect:/board/list";
    }
}

//package com.spring.mvc.chap05.dto;
//
//import com.spring.mvc.chap05.entity.Board;
//import lombok.*;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Getter @Setter @ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//public class BoardDTO {
//    private int boardNo; // 게시글 번호
//    private String title; // 제목
//    private String content; // 내용
//    private int viewCount; // 조회수
//    private String regDateTime; // 작성일자시간
//
//    public BoardDTO(Board board){
//        this.boardNo = board.getBoardNo();
//        this.title = makeShortTitle(board.getTitle());
//        this.content = makeShortContent(board.getContent());
//        this.viewCount = board.getViewCount();
//        this.regDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(board.getRegDateTime());
//    }
//
//    private String makeShortContent(String content) {
//        return sliceString(content, 30);
//    }
//
//    private String makeShortTitle(String content) {
//        return sliceString(content, 7);
//    }
//
//    private String sliceString(String targetString, int  wishLength) {
//        return (targetString.length() >  wishLength)
//                ? targetString.substring(0, wishLength) + "..."
//                : targetString;
//    }
//}

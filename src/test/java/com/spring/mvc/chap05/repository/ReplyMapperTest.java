package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

//    @Test
//    @DisplayName("게시물 300개를 등록하고 각 게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야 한다.")
//    void bulkInsertTest(){
//        for (int i = 0; i < 300; i++) {
//            Board b = Board.builder()
//                    .title("재밌는 게시물 " + i)
//                    .content("노잼 게시물 내용 " + i)
//                    .build();
//            boardMapper.save(b);
//        }
//        assertEquals(300, boardMapper.count(new Search()));
//
//        for (int i = 1; i <= 1000; i++) {
//            Reply r = Reply.builder()
//                    .replyWriter("잼민이 " + i)
//                    .replyText("말똥이~~~" + i)
//                    .boardNo((long)(Math.random() * 300+1) )
//                    .build();
//            replyMapper.save(r);
//        }
//
//    }

    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 " +
            "3번 게시물의 총 댓글 수는 10개여야 한다.")
    @Transactional
    @Rollback // 테스트 끝난 이후 롤백해라, 언제든 통과할 수 있게 설계하자
    void saveTest(){
        // given
        long boardNo = 3L;
        Reply newReply = Reply.builder()
                .replyText("세이브세이브")
                .replyWriter("긴또깡")
                .boardNo(boardNo)
                .build();
        // when
        boolean flag = replyMapper.save(newReply);

        // then
        assertTrue(flag); // 세이브가 성공했을것이라고 단언
        assertEquals(10, replyMapper.count(boardNo));

    }


    @Test
    @DisplayName("댓글 번호가 1001번인 댓글을 삭제하면 " +
            "3번 게시물의 총 댓글 수가 8개가 되야한다.")
    @Transactional
    @Rollback
    void removeTest(){
        // given
        long replyNo = 1001L;
        long boardNo = 3L;
        // when
        boolean flag = replyMapper.deleteOne(replyNo);
        // then
        assertTrue(flag);
        assertEquals(8, replyMapper.count(boardNo));
    }
    
//    @Test
//    void blukReplyInsert(){
//        for (int i = 0; i < 300; i++) {
//            Reply reply = Reply.builder()
//                    .replyText("페이지댓글내용" + i)
//                    .replyWriter("망둥어")
//                    .boardNo(298L)
//                    .build();
//            replyMapper.save(reply);
//        }
//    }


    @Test
    @DisplayName("1002번 댓글의 내용을 달고나달고나로 수정한다.")
    @Transactional
    @Rollback
    void modifyTest(){
        //given
        Reply r = Reply.builder()
                .replyWriter("긴또깡")
                .replyText("달고나달고나")
                .boardNo(3L)
                .replyNo(1002L)
                .build();
        //when
        boolean b = replyMapper.modify(r);
        //then
        assertTrue(b);
        assertEquals(replyMapper.findOne(1002).getReplyText(), "달고나달고나");
    }

    @Test
    @DisplayName("4번 게시물을 조회한다 댓글은 3개일거다")
    void findAll(){
        // when
        List<Reply> replyList = replyMapper.findAll(4, new Page());
        // then
        assertEquals(replyList.size(), 3);
    }


}
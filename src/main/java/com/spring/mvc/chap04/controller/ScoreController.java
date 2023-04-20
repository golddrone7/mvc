package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/*
    # 요청 URL (실무가면 나옴)
    1. 학생 성적정보 등록화면을 보여주고 및 성적정보 목록조회 처리
    - /score/list : GET

    2. 성적 정보 등록 처리 요청
    - /score/register : POST

    3. 성적정보 삭제 요청
    - /score/remove : POST

    4. 성적정보 상세 조회 요청
    - /score/detail : GET

 */
@Controller
@RequestMapping("/score")
//@AllArgsConstructor  // 모든 필드를 초기화하는 생성자
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자
public class ScoreController {

    // 저장소에 의존해야 데이터를 받아서 클라이언트에게 응답할 수 있음
    private final ScoreRepository repository;

    // 만약에 클래스의 생성자가 단 1개라면
    // 자동으로 @Autowired를 써줌

//    @Autowired
    // 생성자 주입, 불변을 유지하기 위해 final
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    // 성적등록화면 띄우기 + 정보목록조회
    @GetMapping("/list")
    public String list(Model model){
        System.out.println("/score/list : GET!");
        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);
        return "chap04/score-list";
    }

    // 컴팩트하게 만들음 entity랑 DTO의 차이를 확인하자
    // DTO 를 따로 만듬
    // entity는 데이터베이스 직전에 넣기 전 보안상 문제가 있음

    // 2. 성적 정보 등록 처리 요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto ){
        // 입력데이터(쿼리스트링) 읽기
        System.out.println("/score/register : POST! - " + dto);
        // dto(ScoreDTO)를 entity(Score)로 변환해야함.
        Score score = new Score(dto);
        // save 명령
        repository.save(score);

        // 리다이렉트 : 다시 호출하는 개념

        /*
            등록요청에서 JSP 뷰 포워딩을 하면
            갱신된 목록을 다시한번 저장소에서 불러와
            모델에 담는 추가적인 코드가 필요하지만

            리다이렉트를 통해서 위에서 만든 /score/list : GET
            을 자동으로 다시 보낼 수 있다면 번거로운 코드가
            줄어들 수 있겠다.

         */
        return "redirect:/score/list"; // 리다이렉트는 요청 URL을 적어야함
    }

    // 3. 성적정보 삭제 요청
    @PostMapping("/remove")
    public String remove(){
        System.out.println("/score/remove : POST");
        return "";
    }

    // 4. 성적정보 삭제 조회 요청
    @GetMapping("/detail")
    public String detail(){
        System.out.println("/score/detail : GET!");
        return "";
    }





}

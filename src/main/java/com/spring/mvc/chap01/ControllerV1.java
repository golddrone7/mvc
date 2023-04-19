package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 관리하도록 빈을 등록
@Controller // @Component와 같은 개념
public class ControllerV1 {

    // 세부 요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/spring/hello
    public String hello(){
        System.out.println("\n====== 헬로 요청이 들어옴! ======\n");
        // 어떤 JSP를 열어줄지 경로를 적습니다.
//        return "/WEB-INF/views/hello.jsp";
        return "hello";
    }

    // /spring/food 요청이 오면 food.jsp를 열어보세요

    @RequestMapping("/food")
    public String food(){
        //jsp가 어디에 살고 있는지 적어줌
//        return "/WEB-INF/views/chap01/food.jsp";
        return "chap01/food";
    }

    // ==================== 요청 파라미터 읽기 (Query String parameter) =============
    // === 1. HttpServletRequest 사용하기
    // ==> ex ) /spring/person?name=kim&age=30

    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "";
    }

    // == 2. @RequestParam 사용하기
    // ==> ex ) /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            String stu, // @RequestParam 생략가능
            @RequestParam("major") String mj, // 이름이 겹칠 때 사용하자
            @RequestParam(defaultValue = "1") int grade
    ){
        String major = "전공전공";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }
    // == 3. 커맨드 객체 이용하기
    // == 쿼리 스트링의 양이 너무 많을 경우 또는 연관성이 있을경우
    // ==> ex) /spring/order?oNum=20230419007-P&goods=구두&amount=3&price=50000...

    @RequestMapping("/order")
    private String order(
            OrderRequestDTO dto
    ){
        System.out.println("dto = " + dto);
        return "";
    }

    // 양이 적을땐 RequestParam  (2)
    // 양이 많을땐 커맨드 객체 DTO 이용하기 (3)


    // == 4. URL에 경로로 붙어있는 데이터 읽기
    // ==> /spring/member/hong/107
    //    hong이라는 유저의 107번 게시글을 읽고싶음
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
            @PathVariable String userName,
            @PathVariable long bNo // 생략불가, 생략하면 @RequestParam으로 처리됨
    ){
        System.out.println("userName = " + userName);
        System.out.println("bNo = " + bNo);
        return "";
    }








}

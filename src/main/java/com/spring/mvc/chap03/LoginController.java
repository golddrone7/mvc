package com.spring.mvc.chap03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hw")
public class LoginController {


    /**
     *  1번요청: 로그인 폼 화면 열어주기
     */

    @GetMapping("/s-login-form")
    public String loginForm(){
        System.out.println("로그인 폼 열었당");
        return "chap03/s-form";
    }


    @PostMapping("/s-login-check")
    public String loginCheck(
            @RequestParam(defaultValue = "") String id,
            @RequestParam(defaultValue = "" )String password,
            Model model)
    {
        System.out.println("ㅎㅇㅎㅇㅇㅇㅇ");
        String answer = "";
        if(!id.equals("grape111")){
            answer = "아이디가 존재하지 않습니다.";
        } else if(password.equals("ggg9999")){
            answer = "로그인 성공.";
        } else{
            answer = "비밀번호가 틀렸습니다.";
        }
        model.addAttribute("answer", answer);
        return "chap03/s-result";
    }


}

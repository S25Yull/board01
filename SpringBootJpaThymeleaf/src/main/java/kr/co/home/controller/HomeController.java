package kr.co.home.controller;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {
    
    //메인 페이지 이동
    @GetMapping("/main")
    public String main(Model m) throws Exception {
    	log.info("Controller @GetMapping(/main) 홈 화면이동 >>>>>>>>>>>>>>> ");
        return "home/main";
    }

    //로그인 페이지 이동
    @GetMapping("/login")
    public String login(Model m) throws Exception {
    	log.info("Controller @GetMapping(/login) 홈 화면이동 >>>>>>>>>>>>>>> ");
        return "login/login";
    }

    //회원가입페이지 이동
    @GetMapping("/join")
    public String join(Model m) throws Exception {
    	log.info("Controller @GetMapping(/join) 홈 화면이동 >>>>>>>>>>>>>>> ");
        return "login/join";
    }
}
package kr.co.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import kr.co.login.domain.TbUser;
import kr.co.login.dto.TbUserDto;
import kr.co.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
    
    //게시판 목록 페이지 이동
    @GetMapping("/login")
    public String login(Model m) throws Exception {
    	log.info("Controller @GetMapping(/home) 홈 화면이동 >>>>>>>>>>>>>>> ");
        return "login/login";
    }

    //로그인 정보 조회
    @GetMapping("/selectLogin")
    public void selectLogin(Model m) throws Exception {
    	
    	TbUserDto tbUserDto = TbUserDto.builder().userId("mk").userPw("1234").build();
    	
    	TbUserDto resultDto= loginService.selectLogin(tbUserDto);
    
    	log.info("회원정보결과 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: "+resultDto.toString());
    	
    }

    //회원 정보 등록
    @GetMapping("/insertUser")
    public void insertUser(Model m) throws Exception {
    	
    	TbUserDto tbUserDto = TbUserDto.builder().userId("test").userPw("1234").userNm("테스터").userMail("test@naver.com").build();
    
    	log.info("회원등록결과>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: "+loginService.insertUser(tbUserDto));
    	
    }
}
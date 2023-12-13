package com.seoyul.member.controller;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.seoyul.member.dto.MemberDTO;
import com.seoyul.member.service.MemberService;


@Slf4j
@Controller
//메뉴 공통 리뭬스트맵핑영 /member
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//로그인 html 이동
    @GetMapping("/login")//단순 html로 이동할때 GetMapping
    public String loginForm() {
    	log.info(">>>>>>>>>>>>>>@GetMapping('/member/login') html 호출");
    	return "member/login";
    }
    
    //로그인
    @PostMapping("/login")//PostMapping 저장한 정보를 담음
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {

    	MemberDTO loginResult = memberService.selectMember(memberDTO);
    	
    	if(loginResult != null) { //등록된 유저인지 판단
    		// login 성공
    		session.setAttribute( "loginEmail", loginResult.getMemberEmail());
    		// 성공 후 메인페이지 컨트롤러 호출
    		return "redirect:/";
        } else {
            // login 실패후 다시 로그인페이지 컨트롤러 호출
            return "redirect:/member/login";
        }

    }

    //회원가입 html 이동
    @GetMapping("/save")
    public String saveForm(){
    	log.info(">>>>>>>>>>>>>>@GetMapping('/member/save') html 호출");
    	return "member/save";
    }

    //회원가입 저장(isnert)
    @PostMapping("/save")//입력해서 저장한 정보를 로그인에 담음
    public String save(@ModelAttribute MemberDTO memberDTO){
    	log.info("MemberController.save");
        log.info("memberDTO = {}",memberDTO.toString());
        //마이바티스에서는 정상적으로 insert가 수행되면 1을 반호나하고 실패하면 0을 반환한다.
        if(memberService.insertMember(memberDTO) > 0 ) {
            return "redirect:/member/login";        	
        }
        return "redirect:/member/save";
    }

    //save.html 에서 Ajax 정보 받아오기
    //Ajax사용시 메서드에 항상 @ResponseBody 가 있어야함
    @ResponseBody
    @PostMapping("/email-check")
    public int emailCheck(@RequestParam("memberEmail") String memberEmail) {

    	log.info("memberEmail = {}",memberEmail);

    	//이메일이 존재하면 1, 없으면 0을 반환한다.
        int checkResult = memberService.selectEmailCheck(memberEmail);
        
        return checkResult;
    }


    //회원 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session){
    	log.info(">>>>>>>>>>>>>>@GetMapping('/member/logout') 호출");
    	//모든 세션 제거
    	session.invalidate();
    	return "redirect:/member/login";
    }
}

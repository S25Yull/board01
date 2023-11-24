package com.seoyul.member.controller;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.seoyul.member.dto.MemberDTO;
import com.seoyul.member.service.MemberService;


@Controller
@RequiredArgsConstructor
public class MemberController {

	//생성자 주입
private final MemberService memberService;
	


  //회원가입 페이지 출력 요청
	@GetMapping("/member/save")//단순 html로 이동할때 GetMapping
    public String saveForm(){
        return "member/save";
    }
	
    @PostMapping("/member/save")//입력해서 저장한 정보를 로그인에 담음
    public String save(@ModelAttribute MemberDTO memberDTO){

        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "member/login";
        
}
    //로그인 화면으로 가기
    @GetMapping("/member/login")//단순 html로 이동할때 GetMapping
    public String loginForm() {
        return "member/login";
    }
    
    //DB에 저장된 로그인 값인지 판단
    @PostMapping("/member/login")//PostMapping 저장한 정보를 담음
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) { //등록된 유저인지 판단
            // login 성공
           session.setAttribute(  "loginEmail", loginResult.getMemberEmail());
            return "redirect:/main";
        } else {
            // login 실패
            return "member/login";
        }
    }
    
    //멤버조회하기
    @GetMapping("/member/") //home.html에 있는 member를 말함
    public String findAll(Model model) {
    	List<MemberDTO>memberDTOList = memberService.findAll(); //여러개의 게시글을 가져오려면 리스트
    	 // 어떠한 html로 가져갈 데이터가 있다면 model사용
    	model.addAttribute("memberList",memberDTOList);
    	return "member/list";
    }
    
    //회원 아이디 가져오기
    @GetMapping("/member/{id}")//경로상의 있는것을 담아온다는 것
    public String findById(@PathVariable Long id ,Model model) {
    	MemberDTO memberDTO = memberService.findById(id);
    	model.addAttribute("member",memberDTO);
    	return "member/detail";
    }
    
    //업데이트
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
    	String myEmail = (String)session.getAttribute("loginEmail");
    	MemberDTO memberDTO = memberService.updateForm(myEmail);
    	model.addAttribute( "updateMember",memberDTO);
    	return "member/update";
    }
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
    	memberService.update(memberDTO);
    	 return "redirect:/member/" + memberDTO.getId();
    }
    
    //삭제
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
    	memberService.deleteById(id); //아이디를 넣는 이유는 pk이기 때문
    	return "redirect:/member/"; //여기서 그냥 return "list" 로 가면 안됨 삭제 적용이 안되기 때문
    }
    
    //로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "home/home";
    }
    
    //save.html 에서 Ajax 정보 받아오기
    //Ajax사용시 메서드에 항상 @ResponseBody 가 있어야함
    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
//        if (checkResult != null) {
//            return "ok";
//        } else {
//            return "no";
//        }
    }
    
}
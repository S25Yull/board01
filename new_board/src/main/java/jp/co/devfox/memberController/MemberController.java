package jp.co.devfox.memberController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.devfox.memberService.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	//회원가입
	@RequestMapping(value="/member/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
	    return new ModelAndView("member/signup");
	}
	
	//save
	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public ModelAndView memberSave(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String Id = this.memberService.create(map);
	    if (Id == null) {
	        mav.setViewName("redirect:/member/signup");
	    }else {
	        mav.setViewName("redirect:/member/detail?Id=" + Id); 
	    }  

	    return mav;
	}
	
	//로그인
	@RequestMapping(value="/member/login", method = RequestMethod.GET)
	public ModelAndView login() {
	    return new ModelAndView("member/login");
	}
	
	
	
	
	
	
	
	
}//controller

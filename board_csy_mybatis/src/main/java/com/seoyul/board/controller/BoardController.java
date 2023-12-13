package com.seoyul.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seoyul.board.dto.BoardDTO;
import com.seoyul.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//게시판 목록화면 이동
	@GetMapping("/list")
    public String boardList(Model model, BoardDTO boardDTO){
    	log.info(">>>>>>>>>>>>>>@GetMapping('/board/list') html 호출");
		//게시판 목록 조회결과를 model에 담아 html에서 출력한다.
		model.addAttribute("list", boardService.selectBoardList(boardDTO));
		return "board/list";
    }

	//게시판 글작성 화면 이동
	@GetMapping("/write")
    public String boardWrite(HttpSession session) throws Exception{
		
    	log.info(">>>>>>>>>>>>>>@GetMapping('/board/write') html 호출");
    	
		try {
			String loginEmail = (String)session.getAttribute("loginEmail");
			log.info(">>>>>>>>>>>>> loginEmail : {}",loginEmail);
			//로그인 회원만 게시판 작성페이지에 접근할 수있다.
			if(loginEmail == null) {
				throw new Exception("로그인 후 이용 가능합니다.");
			}
		} catch (Exception e) {
			//비로그인 회원일때 로그인 페이지로 강제 이동
			e.printStackTrace();
			return "redirect:/member/login";
		}
		
		return "board/write";
    }

	//게시판 글작성 화면 이동
	@PostMapping("/save")
    public String boardSave(BoardDTO boardDTO, HttpSession session) throws Exception{
		
		try {
			String loginEmail = (String)session.getAttribute("loginEmail");
			//로그인 회원만 게시판 작성페이지에 접근할 수있다.
			if(loginEmail == "") {
				throw new Exception("로그인 후 이용 가능합니다.");
			}
			
			//글 작성자 값 셋팅(로그인한 아이디로 셋팅한다.)
			boardDTO.setRegInfo(loginEmail);
			//게시물 작성 서비스 호출
			boardService.insertBoard(boardDTO);
			
		} catch (Exception e) {
			//비로그인 회원일때 로그인 페이지로 강제 이동
			e.printStackTrace();
			return "redirect:/member/login";
		}
		
		return "redirect:/board/list";
    }

	//게시판 목록화면 이동
	@GetMapping("/detail")
    public String boardDetail(Model model, BoardDTO boardDTO){
    	log.info(">>>>>>>>>>>>>>@GetMapping('/board/detail') html 호출");
		//게시판 목록 조회결과를 model에 담아 html에서 출력한다.
		//model.addAttribute("list", boardService.selectBoardList(boardDTO));
		return "board/detail";
    }
}

package com.seoyul.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.seoyul.board.dto.BoardDTO;
import com.seoyul.board.service.BoardService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor //생성자 주입하기 위한 것
//글쓰기
public class BoardController {
	//생성자 주입
	private final BoardService boardService;
	//글쓰기 메인화면
	@GetMapping("/board/boardWrite")//단순 html로 이동할때 GetMapping
    public String writeForm(){
	      return "board/boardWrite";
	    }
	//글쓰기
	@GetMapping("/board/boardSave")//단순 html로 이동할때 GetMapping
    public String saveForm(){
	      return "board/boardSave";
	    }
	//게시글 저장하기
	@PostMapping("/board/boardSave")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "board/boardWrite";
    }
	//글목록
	@GetMapping("/board/boardList")//단순 html로 이동할때 GetMapping / boardWrite.html 은 board에서 index.html 역할을 함
    public String findAll(Model model){
		// DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "board/boardList";
	    }
	//게시글 조회수 올리기
	@GetMapping("/board/{id}")//경로상의 있는것을 담아온다는 것
	public String findById(@PathVariable Long id ,Model model) {
		boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
		return "board/boardDetail";
	}
	//업데이트 창으로 가기
	@GetMapping("/board/update/{id}")//boardUpdate.html의 action에서 정의해준 경로
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "board/boardUpdate";
    }
	//업데이트 하기
	 @PostMapping("/board/update")
	    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
	        BoardDTO board = boardService.update(boardDTO);
	        model.addAttribute("board", board);
	        return "board/boardDetail";
	        //return "redirect:/board/" + boardDTO.getId();
	    }
	 //게시글 삭제
	 @GetMapping("board/delete/{id}")
	    public String delete(@PathVariable Long id) {
	        boardService.delete(id);
	        return "redirect:/board/boardList";
	    }
	 //게시글 페이징
	 
///깃허브 테스트
	
}

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
import org.springframework.web.bind.annotation.RequestParam;

import com.seoyul.board.dto.BoardDTO;
import com.seoyul.board.dto.CommentDTO;
import com.seoyul.board.entity.BoardEntity;
import com.seoyul.board.service.BoardService;
import com.seoyul.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // 생성자 주입하기 위한 것
//글쓰기
public class BoardController {
	// 생성자 주입
	private final BoardService boardService;
	private final CommentService commentService;

	// 글쓰기 메인화면
	@GetMapping("/board/boardWrite") // 단순 html로 이동할때 GetMapping
	public String writeForm() {
		return "board/boardWrite";
	}

	// 글쓰기
	@GetMapping("/board/boardSave") // 단순 html로 이동할때 GetMapping
	public String saveForm() {
		return "board/boardSave";
	}

	// 게시글 저장하기
	@PostMapping("/board/boardSave")
	public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
		System.out.println("boardDTO = " + boardDTO);
		boardService.save(boardDTO);
		return "board/boardWrite";
	}

	// 글목록
	@GetMapping("/board/boardList") // 단순 html로 이동할때 GetMapping / boardWrite.html 은 board에서 index.html 역할을 함
	public String findAll(Model model) {
		// DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		return "board/boardList";
	}

	// 게시글 조회수 올리기 (상세화면)
	@GetMapping("/board/{id}") // 경로상의 있는것을 담아온다는 것
	public String findById(@PathVariable Long id, Model model, @RequestParam("page") Integer page) {

		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		// 댓글 목록 가져오기
		List<CommentDTO> commentDTOList = commentService.findAll(id);
		model.addAttribute("commentList", commentDTOList);
		model.addAttribute("board", boardDTO);
		model.addAttribute("page", page);
		return "board/boardDetail";
	}

	// 등록된 고유 번호를 이용하여 업데이트 창으로 가기
	@GetMapping("/board/boardUpdate/{id}") // boardUpdate.html의 action에서 정의해준 경로
	public String updateForm(@PathVariable Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("boardUpdate", boardDTO);
		return "board/boardUpdate";
	}

	// 업데이트 하기 -> 업데이트 후 페이징리스트 호출
	@PostMapping("/board/boardUpdate")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		BoardDTO board = boardService.update(boardDTO);
		model.addAttribute("board", board);
		return "redirect:/board/boardPaging";
		// return "redirect:/board/" + boardDTO.getId();
	}

	// 게시글 삭제 -> 삭제 후 페이징리스트 호출
	@GetMapping("/board/delete/{id}")
	public String delete(@PathVariable Long id) {
		boardService.delete(id);
		return "redirect:/board/boardPaging";
	}

	// 게시글 페이징
	@GetMapping("/board/boardPaging")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model,String searchKeyword) {// 데이터로 담아갈땐 모델
		// pageable.getPageNumber(); //몇 페이지가 요청되었는지 확인 가능
		
		//Page<BoardDTO> boardList = boardService.paging(pageable);
		Page<BoardEntity> boardList = boardService.boardSearchList(searchKeyword);
		//Page<BoardDTO> boardList = null;
		
		//if(searchKeyword == null) { //검색기능이 추가되면서 검색했을때와 안했을때를 구분
		//	boardList = boardService.paging(pageable);
		//}else {
		//	boardList = boardService.boardSearchList(searchKeyword, pageable);
		//}
		
		
		int blockLimit = 3;
		int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; 
		int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1
				: boardList.getTotalPages();
	

		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "board/boardPaging";

	}
	//검색기능
	///@GetMapping("/board/boardPaging")
    //public String search(@RequestParam(value="keyword")String keyword, Model model) {

      //  List<BoardDTO> searchList = boardService.boardSearchList(keyword);

        //model.addAttribute("searchList", searchList);

        //return "board/boardPaging";
   // }

}

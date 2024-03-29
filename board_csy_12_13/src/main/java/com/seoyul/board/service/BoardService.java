package com.seoyul.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.seoyul.board.dto.BoardDTO;

import com.seoyul.board.entity.BoardEntity;
import com.seoyul.board.entity.BoardFileEntity;
import com.seoyul.board.repository.BoardFileRepository;
import com.seoyul.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

//DTO -> Entity (Entity Class)
//Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardFileRepository boardFileRepository;

	public void save(BoardDTO boardDTO) throws IOException {
		// 파일 첨부 여부에 따라 로직 분리
		if (boardDTO.getBoardFile().isEmpty()) {
			// 첨부 파일 없음
			BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
			boardRepository.save(boardEntity);
		} else {
			// 첨부 파일 있음
			/*
			 * 1. DTO에 담긴 파일을 꺼냄 2. 파일의 이름 가져옴 3. 서버 저장용 이름을 만듦 // 내사진.jpg =>
			 * 839798375892_내사진.jpg 4. 저장 경로 설정 5. 해당 경로에 파일 저장 6. board_table에 해당 데이터 save
			 * 처리 7. board_file_table에 해당 데이터 save 처리
			 */
			// 부모 객체를 가져옴
			BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
			Long savedId = boardRepository.save(boardEntity).getId(); // 저장 후 아이디 값 얻어옴 부모 게시글의 대한 PK값
			BoardEntity board = boardRepository.findById(savedId).get();

			// 다중파일처리
			for (MultipartFile boardFile : boardDTO.getBoardFile()) {
				// MultipartFile boardFile = boardDTO.getBoardFile(); // 1.
				String originalFilename = boardFile.getOriginalFilename(); // 2.
				String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
				String savePath = "/Users/choeseoyull/Desktop/board_csy_file/" + storedFileName; // 4.
																									// C:/springboot_img/9802398403948_내사진.jpg
				// 맥의 경우 String savePath = "/Users/사용자이름/springboot_img/" + storedFileName; //
				// C:/springboot_img/9802398403948_내사진.jpg
				boardFile.transferTo(new File(savePath)); // 5. 여기까지가 파일 저장만 한 것
				BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename,
						storedFileName);
				boardFileRepository.save(boardFileEntity);// 디비에 저장
			} // for

		} // else

	}// save메서드

	@Transactional
	public List<BoardDTO> findAll() {
		List<BoardEntity> boardEntityList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for (BoardEntity boardEntity : boardEntityList) {
			boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
		}
		return boardDTOList;
	}

	@Transactional
	public void updateHits(Long id) {
		boardRepository.updateHits(id);

	}

	// 조회할때
	@Transactional
	public BoardDTO findById(Long id) {
		Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
		if (optionalBoardEntity.isPresent()) {
			BoardEntity boardEntity = optionalBoardEntity.get();
			BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
			return boardDTO;
		} else {
			return null;
		}

	}

	public BoardDTO update(BoardDTO boardDTO) {
		BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
		boardRepository.save(boardEntity);
		return findById(boardDTO.getId());
	}

	public void delete(Long id) {
		boardRepository.deleteById(id);

	}

	// 페이징
	@Transactional
	public Page<BoardDTO> paging(Pageable pageable, String searchKeyword, String searchWriter) {
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 10; // 한 페이지에 보여줄 글 갯수
		// 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
		// page 위치에 있는 값은 0부터 시작(원래 자바에서 그런것) , 실제로 보여주는 값은 1페이지 = 사용자가 헷갈리지 않기위해
		 Page<BoardEntity> boardEntities =
		 boardRepository.defaultSearch(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
		//Page<BoardEntity> boardEntities;// 페이징초기화

		 //searchWriter가 null이거나 비어 있을 때 defaultSearch가 호출되도록 로직을 수정
		 if (searchKeyword.isEmpty() && (searchWriter == null || searchWriter.isEmpty())) {
			    boardEntities = boardRepository.defaultSearch(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
			} else {
			    boardEntities = boardRepository.customSearch(searchKeyword, searchWriter, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
			}

		// Page 인터페이스를 사용하면 엔티티에 없는 변수도 사용가능
		System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
		System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
		System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
		System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
		System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
		System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
		System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
		System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

		// 목록: id, writer, title, hits, createdTime 엔티티를 보드 디티오 객체로 바꾸어줌
		Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(),
				board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
		return boardDTOS;

	}

}

package com.seoyul.board.dto;



import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.seoyul.board.entity.BoardEntity;
import com.seoyul.board.entity.BoardFileEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
	
	    private Long id;
	    private String boardWriter;
	    private String boardPass;
	    private String boardTitle;
	    private String boardContents;
	    private int boardHits;
	    private LocalDateTime boardCreatedTime;
	    private LocalDateTime boardUpdatedTime;
	    
	    //파일첨부용 
	    private List<MultipartFile> boardFile; // save.html -> Controller 파일 담는 용도 직접적으로 디티오에 영향
	    private List<String> originalFileName; // 원본 파일 이름 ,서비스
	    private List<String> storedFileName; // 서버 저장용 파일 이름 사용자가 정할수 있는 파일이름,서비스
	    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0) boolean보다 int 타입이 간단,서비스

	    //검색용
	    private String searchKeyword;
	    private String searchWriter;
	    
	    //페이징 전용 DTO
	    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
	        this.id = id;
	        this.boardWriter = boardWriter;
	        this.boardTitle = boardTitle;
	        this.boardHits = boardHits;
	        this.boardCreatedTime = boardCreatedTime;
	    }
	    
	    //첨부파일
	    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
	        BoardDTO boardDTO = new BoardDTO();
	        boardDTO.setId(boardEntity.getId());
	        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
	        boardDTO.setBoardPass(boardEntity.getBoardPass());
	        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
	        boardDTO.setBoardContents(boardEntity.getBoardContents());
	        boardDTO.setBoardHits(boardEntity.getBoardHits());
	        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
	        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime()); 
	        if(boardEntity.getFileAttached() == 0) {
	        	boardDTO.setFileAttached(boardEntity.getFileAttached());//0 파일이 없음
	        }else {
	        	List<String> originalFileNameList = new ArrayList<>();
	        	List<String> storedFileNameList = new ArrayList<>();
	        	boardDTO.setFileAttached(boardEntity.getFileAttached());//1 파일이 있음
	        	// 파일 이름을 가져가야 함.
	            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
	            // join
	            // select * from board_table b, board_file_table bf where b.id=bf.board_id
	            // and where b.id=?
	        	for(BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
	        		originalFileNameList.add(boardFileEntity.getOriginalFileName());
	        		storedFileNameList.add(boardFileEntity.getStoredFileName());
	        	}
	        	boardDTO.setOriginalFileName(originalFileNameList);
	            boardDTO.setStoredFileName(storedFileNameList);
	        
	        }
	        return boardDTO;
	}//첨부파일
}//class

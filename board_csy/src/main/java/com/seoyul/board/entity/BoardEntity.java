package com.seoyul.board.entity;
//DB의 테이블 역할을 하는 클래스
import lombok.*;
import javax.persistence.*;

import com.seoyul.board.dto.BoardDTO;




@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
	
	  @Id // pk 컬럼 지정. 필수
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	    private Long id;

	    @Column(length = 20, nullable = false) // 크기 20, not null
	    private String boardWriter;

	    @Column // 크기 255, null 가능
	    private String boardPass;

	    @Column
	    private String boardTitle;

	    @Column(length = 500)
	    private String boardContents;

	    @Column
	    private int boardHits;
	    
	    //글쓰기
	    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
	    	
	    	BoardEntity boardEntity = new BoardEntity();
	        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
	        boardEntity.setBoardPass(boardDTO.getBoardPass());
	        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
	        boardEntity.setBoardContents(boardDTO.getBoardContents());
	        boardEntity.setBoardHits(0);
	        
	        return boardEntity;
	    }
	    //업데이트
		public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
			BoardEntity boardEntity = new BoardEntity();
			boardEntity.setId(boardDTO.getId());
	        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
	        boardEntity.setBoardPass(boardDTO.getBoardPass());
	        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
	        boardEntity.setBoardContents(boardDTO.getBoardContents());
	        boardEntity.setBoardHits(boardDTO.getBoardHits());
	        
	        return boardEntity;
		}
		
		
}

    
    
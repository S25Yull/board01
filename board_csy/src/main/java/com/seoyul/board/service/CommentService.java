package com.seoyul.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.seoyul.board.dto.CommentDTO;
import com.seoyul.board.entity.BoardEntity;
import com.seoyul.board.entity.CommentEntity;
import com.seoyul.board.repository.BoardRepository;
import com.seoyul.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	
	public Long save(CommentDTO commentDTO) { //타입을 롱으로 해줘야함
		 /* 부모엔티티(BoardEntity) 조회 */
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        }//if
        else {
            return null;
        }//else
    }//save

	public List<CommentDTO> findAll(Long boardId) {
		// select * from comment_table where board_id=? order by id desc;
		//아이디를 기준으로 내림차순 (최근에 작성한 댓글이 가장 먼저 보이도록)
		BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }//for
        return commentDTOList;
	}//findAll
}//class
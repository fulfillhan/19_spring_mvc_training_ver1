package com.application.trainingVer1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.trainingVer1.dao.BoardDAO;
import com.application.trainingVer1.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	// 해당클래스는 인터페이스를 구현했다. 외부에서 인잭션으로 들어가야해서 인터페이스 ? service란 무엇인가?


	@Autowired 
	private BoardDAO boardDAO;// dao연결
	
	@Override
	public void createBoard(BoardDTO boardDTO) {
		boardDAO.createBoard(boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		return boardDAO.getBoardList();
	}

	@Override
	public BoardDTO getBoardDetail(long boardId) {
		boardDAO.updateReadCnt(boardId);// 조회수 올리기
		return boardDAO.getBoardDetail(boardId);
	}
	
	

}

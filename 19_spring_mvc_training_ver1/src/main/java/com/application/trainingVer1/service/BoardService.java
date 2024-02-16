package com.application.trainingVer1.service;

import java.util.List;

import com.application.trainingVer1.dto.BoardDTO;

public interface BoardService {

	public void createBoard(BoardDTO boardDTO);
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardDetail(long boardId);
	public boolean checkAuthorized(BoardDTO boardDTO);
	public void updateBoard(BoardDTO boardDTO);
	public void deleteBoard(long boardId);
	

}

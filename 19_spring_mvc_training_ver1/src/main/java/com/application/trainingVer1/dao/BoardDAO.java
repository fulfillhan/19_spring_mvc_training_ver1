package com.application.trainingVer1.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.application.trainingVer1.dto.BoardDTO;

@Mapper// sql과 1:1 매핑
public interface BoardDAO {
	
	public void createBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);
	
	public void updateReadCnt(long boardId);

	public String getEncodePasswd(long boardId);

	public void updateBoard(BoardDTO boardDTO);

	public void deleteBoard(long boardId);
	
}

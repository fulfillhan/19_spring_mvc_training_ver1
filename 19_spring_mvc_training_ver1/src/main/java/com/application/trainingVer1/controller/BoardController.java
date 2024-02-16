package com.application.trainingVer1.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.trainingVer1.dto.BoardDTO;
import com.application.trainingVer1.service.BoardService;

@Controller
@RequestMapping("/board")// rest api=
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/createBoard")
	public String createBoard() {
		return "board/createBoard";
	}
	
	@PostMapping("/createBoard")
	@ResponseBody//리액션 나오게 하기
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		
		
		//단위 테스트함
		//System.out.println(boardDTO);
		
		boardService.createBoard(boardDTO);
		
		String jsScript ="""
				<script>
				 alert('작성 되었습니다.');
				 location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		//System.out.println(boardService.getBoardList());
		
		model.addAttribute("boardList", boardService.getBoardList());
		
		return "board/boardList";
	}
	
	@GetMapping("/boardDetail")
	public String boardDetail(Model model,@RequestParam("boardId") long boardId){
		
		//단위테스트
		//System.out.println(boardService.getBoardDetail(boardId)); 
		//boardService.getBoardDetail(boardId);
		
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		
		
		return "board/boardDetail";// html넘겨주기
	}

}

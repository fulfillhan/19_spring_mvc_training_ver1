package com.application.trainingVer1.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	
	@GetMapping("/authentication")
	public String authentication(Model model, @RequestParam("boardId") long boardId,
								  @RequestParam("menu") String menu) {
		//boardService.getBoardDetail(boardId);
		
		model.addAttribute("boardDTO",boardService.getBoardDetail(boardId) );
		model.addAttribute("menu", menu);
		
		return "board/authentication";
	}
	@PostMapping("/authentication")
	@ResponseBody
	public String authentication(@ModelAttribute BoardDTO boardDTO, @RequestParam("menu") String menu) {
		
		String jsScript="";
		
		if(boardService.checkAuthorized(boardDTO)) {
			
			if(menu.equals("delete")) {
				jsScript="<script>";
				jsScript += "location.href='/board/deleteBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript += "</script>";
				
			}else if(menu.equals("update")) {
				jsScript="<script>";
				jsScript += "location.href='/board/updateBoard?boardId="+boardDTO.getBoardId()+"';";
				jsScript += "</script>";
			}
		}else {
			jsScript = """
					<script>
						alert('패스워드를 확인하세요');
						history.go(-1);
					</script>
					""";
		}
		
		return jsScript;
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(Model model, @RequestParam("boardId") long boardId) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/updateBoard";
	}
	
	@PostMapping("/updateBoard")
	@ResponseBody
	public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
		
		boardService.updateBoard(boardDTO);
		
		String jsScript ="""
				<script>
				 alert('수정 완료되었습니다.');
				 location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}
	
	//(type=Bad Request, status=400 오류 발생
	//Failed to convert value of type 'java.lang.String' to required type 'long'; For input string: "${boardDTO.boardId}"
	//해결: html에서 <form action>코드 작성 안함
	@GetMapping("/deleteBoard")
	public String deleteBoard(Model model, @RequestParam("boardId") long boardId) {		
		
		model.addAttribute("boardId", boardId);
		
		return "board/deleteBoard";
	}
	
//	오류 발생
//No primary or single unique constructor found for long :  long타입에 대한 생성자가 없는경우
// 해결 : 파라메타의 데이터가 @ModelAttribute는 어려 파라메타를 객체로 바인딩 할 때 사용, 데이터가 단일 혹은 단순할 때는 @RequestParam 사용해야 한다.
	@PostMapping("/deleteBoard")
	@ResponseBody
	public String deleteBoard(@RequestParam("boardId") long boardId) {
		boardService.deleteBoard(boardId);
		
		String jsScript ="""
				<script>
				 alert('삭제되었습니다..');
				 location.href='/board/boardList';
				</script>
				""";
		return jsScript;
	}
	
}

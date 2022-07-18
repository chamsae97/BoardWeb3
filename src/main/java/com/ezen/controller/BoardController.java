package com.ezen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.board.domain.Board;
import com.ezen.service.BoardService;

@Controller
@RequestMapping("/board") // board와 관련된 컨트롤러임을 명시
public class BoardController { 

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/getBoardList") // 게시판 글 목록 
	public String getBoardList(Model model) {
		List<Board> pageList = boardService.getBoardList().getContent();
		
		model.addAttribute("boardList", pageList);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard") // 글 상세보기
	public String getBoardView(Board board, Model model) {
		
		boardService.getBoard(board).getMember();
		
		model.addAttribute("board", boardService.getBoard(board));
		
		return "board/getBoard";
	}

	@PostMapping("/insertBoard") // 게시글 상세에서 글 등록하기
	public String insertBoard(Board board) {
		
		boardService.insertBoard(board);
		
		return "redirect:getBoardList"; // 글 등록 후 게시판 글 목록으로 이동
	}
	
	/*
	 * 글 수정하기: 화면에서 수정 내용을 입력받아야 하니 커맨드 객체 필요
	 */
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {

		boardService.updateBoard(board);
		
		return "redirect:getBoardList"; // 글 등록 후 게시판 글 목록으로 이동
	}
	
	@GetMapping("/deleteBoard") // 게시글 삭제하기
	public String deleteBoard(Board board) {

		boardService.deleteBoard(board);
		
		return "redirect:getBoardList";
	}
	
	/*
	 * 게시글 목록에서 "새글 등록" 버튼을 요청했을 시 출력되는 등록 폼 (GET)
	 */
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		
		return "board/insertBoard";
	}
	
	/*
	 * 게시글 상세에서 "글 등록"을 눌렀을 때 위에 구현한 insertBoard가 요청된다.
	 */
	@GetMapping("/insertBoardView")
	public String insertBoardViewInsert() {
		return "board/insertBoard";
	}
}



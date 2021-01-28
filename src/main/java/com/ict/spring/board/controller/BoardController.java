package com.ict.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

	@RequestMapping("blist.do")
	public String boardList() {
		return "board/boardListView";
	}
}
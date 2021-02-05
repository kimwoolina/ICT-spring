package com.ict.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ict.spring.board.model.service.ReplyService;
import com.ict.spring.board.model.vo.Reply;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="rinsert.do", method=RequestMethod.POST)
	public String replyInsertMethod(Reply reply, Model model) {
		if(replyService.insertReply(reply) > 0) {
			return "redirect:bdetail.do?bid=" + reply.getRef_bid();
		}else {
			model.addAttribute("msg", reply.getRef_bid() + "번 글에 대한 댓글달기 실패.");
			return "common/errorPage";
		}
	}
}

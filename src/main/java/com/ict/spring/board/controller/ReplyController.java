package com.ict.spring.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.spring.board.model.service.ReplyService;
import com.ict.spring.board.model.vo.Board;
import com.ict.spring.board.model.vo.Reply;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "rinsert.do", method = RequestMethod.POST)
	public String replyInsertMethod(Reply reply, Model model) {
		if (replyService.insertReply(reply) > 0) {
			return "redirect:bdetail.do?bid=" + reply.getRef_bid();
		} else {
			model.addAttribute("msg", reply.getRef_bid() + "번 글에 대한 댓글달기 실패.");
			return "common/errorPage";
		}
	}

	// ajax 원글에 대한 댓글 조회 처리용
	@RequestMapping(value = "rlist.do", method = RequestMethod.POST)
	@ResponseBody
	public String replyListMethod(@RequestParam("ref_bid") int ref_bid) throws UnsupportedEncodingException {
		// 원글에 대한 댓글 조회 요청
		ArrayList<Reply> list = replyService.selectList(ref_bid); // 결과를 받아줌

		// 전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		// list 옮길 json 배열 준비
		JSONArray jarr = new JSONArray();

		// list 를 jarr 로 옮기기(복사)
		for (Reply reply : list) {
			// reply 필드값 저장할 json 객체 생성
			JSONObject job = new JSONObject();

			job.put("rid", reply.getRid()); 
			job.put("rwriter", reply.getRwriter()); // 인코딩 해서 제이슨 객체 안에 담는다
			job.put("rcontent", URLEncoder.encode(reply.getRcontent(), "utf-8"));
			job.put("r_create_date", reply.getR_create_date().toString());
			job.put("ref_bid", reply.getRef_bid());

			// job 를 jarr 에 저장
			jarr.add(job);
		}

		// 전송용 json 객체에 jarr 담음
		sendJson.put("list", jarr);

		return sendJson.toJSONString(); // jsonView 가 리턴됨

	}
	
	@RequestMapping("rdel.do")
	public String replyDeleteMethod(@RequestParam("rid") int rid, @RequestParam("bid") int bid, Model model) {
		if (replyService.deleteReply(rid) > 0) {
			return "redirect:bdetail.do?bid=" + bid;
		} else {
			model.addAttribute("msg", rid + "번 댓글 삭제 실패.");
			return "common/errorPage";
		}
	}
}

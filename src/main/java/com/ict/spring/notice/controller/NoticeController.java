package com.ict.spring.notice.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.spring.notice.model.service.NoticeService;
import com.ict.spring.notice.model.vo.Notice;

@Controller
public class NoticeController {
	//잘연결되었는지 로그로 확인 -> 로그 객체 선언
	private static final Logger logger = LoggerFactory.getLogger("NoticeController.class");
	
	//자동으로 의존성 주입(자동으로 DI선언) //컨트롤러-서비스연결
	@Autowired
	private NoticeService noticeService;
	
	//공지사항 전체 목록보기 요청 처리용
	@RequestMapping("nlist.do")
	public String noticeListMethod(Model model) {
		ArrayList<Notice> list = noticeService.selectAll();
		
		if(list.size() > 0) {
			model.addAttribute("list", list);
			return "notice/noticeListView";
		}else {
			model.addAttribute("msg", "등록된  공지사항 정보가 없습니다.");
			return "common/errorPage";
		}
	}
	
	
}

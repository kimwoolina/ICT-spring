package com.ict.spring.notice.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.spring.member.model.vo.Member;
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
	
	//공지글 상세보기 요청 처리용
	@RequestMapping("ndetail.do")
	public String noticeDetailMethod(@RequestParam("nid") int nid, Model model,
							HttpSession session) {
		Notice notice = noticeService.selectnotice(nid);
		
		if(notice != null) {
			model.addAttribute("notice", notice);
			//관리자가 상세보기 요청했을 떄
			Member loginUser = (Member)session.getAttribute("loginUser");
			if(loginUser != null && loginUser.getId().equals("admin")) {
				return "notice/";
			}else {
				//관리자가 아닌 고객이 상세보기 요청했을 때
				return "notice/noticeDetailView";
			}
		}else {
			model.addAttribute("msg", nid + "번 공지 상세보기 실패");
			return "common/errorPage";
		}
	}
	
	//공지글 등록 페이지 요청 처리용
	@RequestMapping("nwform.do")
	public String noticeWriteForm() {
		return "notice/noticeWriteForm";
	}
	
	//파일업로드 기능이 있는 공지글 등록 요청 처리용
	@RequestMapping(value="ninsert.do", method=RequestMethod.POST)
	public String noticeInsertMethod(Notice notice, HttpServletRequest request,
					@RequestParam(name="upfile", required=false) MultipartFile mfile, Model model) {
		//업로드된 파일 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_files");
		
		//업로드된 파일을 지정 폴더로 옮기기
		try {
			mfile.transferTo(new File(savePath + "\\" + mfile.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "전송 파일 저장 실패");
			return "common/errorPage";
		}
		notice.setFile_path(mfile.getOriginalFilename());
		logger.info("ninsert.do : " + notice);
		
		if(noticeService.insertNotice(notice) > 0) {
			return "redirect:nlist.do";
		}else {
			model.addAttribute("msg","공지글 등록 실패.");
			return "common/errorPage";
		}
	}
	
	//첨부파일 다운로드 요청 처리용
	@RequestMapping("nfdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, @RequestParam("file_path") String fileName) {
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_files");
		File downFile = new File(savePath + "\\" + fileName);
		
		/* ModelAndView(String viewName, String modelName, Object modelObject)
		 * Model 클래스 객체 = request + response
		 * modelName == 이름, modelObject == 객체
		 * request.setAttribute("이름","객체")와 같은 의미
		 * */
		
		//스프링에서는 파일다운하려면, 스프링이 제공하는 View 클래스를 상속받은 
		//파일다운처리용 뷰클래스를 별도로 작성하고, DispatcherServlet에
		//파일다운로드용 뷰클래스를 실행시키는 뷰리졸버를 등록해야함
		return new ModelAndView("filedown", "downFile", downFile);
	}
}

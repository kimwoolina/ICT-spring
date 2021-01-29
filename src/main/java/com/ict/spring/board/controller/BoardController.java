package com.ict.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.ict.spring.board.model.service.BoardService;
import com.ict.spring.board.model.vo.Board;
import com.ict.spring.board.model.vo.PageInfo;
import com.ict.spring.board.model.vo.Reply;
import com.ict.spring.common.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	/**
	 * 게시판 목록 조회
	 * @param mv
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("blist.do")
	public ModelAndView boardList(ModelAndView mv,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		/*
		 * 1. @RequestParam(value="currentPage") int currentPage --> 값이 넘어오지 않았을 때 400
		 *  * 에러 (값을 주입할 수가 없다)
		 * 
		 * 2. @RequestParam(value="currentPage", required=false) int currentPage
		 * required : 해당 파라미터가 필수인지의 여부(true : 필수) -> required=true일 때 NULL 입력 시 400에러
		 * 발생 -> 따라서 required=false로 지정하여 값을 받아줄 필요없다라고 선언(null이 들어올 수 있다.)
		 * 
		 * -> null이라는 데이터가 기본형이 int형에 들어갈 수 없기 때문에
		 * 
		 * 3. 그럼 null일경우 기본값이 들어갈 수 있도록 지정을 해보자.
		 * 
		 * @RequestParam(value="currentPage", required=false, defaultValue="1") int
		 * currentPage defaultValue : 넘어오는 값이 null일 경우 해당 파라미터의 기본값을 지정할 수 있다 .
		 * 
		 */

		System.out.println(currentPage);

		int listCount = bService.getListCount(); 

		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

		ArrayList<Board> list = bService.selectList(pi); 

		mv.addObject("list", list);
		mv.addObject("pi", pi);
		mv.setViewName("board/boardListView"); 

		return mv;
	}

	/**
	 * 게시글 등록 페이지로 이동
	 * @return
	 */
	@RequestMapping("binsertView.do")
	public String boardInsertView() {
		return "board/boardInsertForm"; 
	}

	/**
	 * 게시글 등록
	 * @param b
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("binsert.do")
	public String insertBoard(Board b, HttpServletRequest request,
			@RequestParam(name = "uploadFile", required = false) MultipartFile file) {
		// @RequestParam어노테이션을 이용한 업로드 파일 접근
		// 파일 업로드를 할 때는 form의 enctype = multipart/form-data로 작성해야하고, method = post여야 한다.
		// 그래야만 MultipartResolver가 multipartFile객체를 컨트롤러에 전달할 수 있습니다.  <--꼭 기억해두자

		// System.out.println(b);
		// System.out.println(file.getOriginalFilename());
		// 그냥 한번 출력해보면 다 null로 나오는 걸 확인할 수 있다. --> Multipart로 넘어오기 때문이다.
		// 따라서 pom.xml에 파일업로드 관련 라이브러리를 추가해줘야된다.
		// 추가 후에 root-context에서 파일사이즈 지정해주자

		// 이제 넘어온 file을 폴더에 저장해주는 작업을진행 
		// 아래의 메소드에서 saveFile메소드는 다른곳에서도 사용할 수 있어서 따로 메소드로 빼서 관리를 시켜주기로하자
		if (!file.getOriginalFilename().equals("")) {

			// 서버에 업로드 해야된다
			// saveFile이라는 메소드 따로 빼서 작업할 것이다.
			// 즉, 내가 저장하고자 하는 file과 request를 전달하여 서버에 업로드 시키고 그 저장된 파일명 반환해주는 그런 메소드
			String renameFileName = saveFile(file, request);

			if (renameFileName != null) { // 파일이 잘 저장된 경우
				b.setOriginalFileName(file.getOriginalFilename()); // 파일명만 DB에 저장
				b.setRenameFileName(renameFileName);
			}
		}

		int result = bService.insertBoard(b); 

		if (result > 0) {
			return "redirect:blist.do";
		} else {
			return "common/errorPage";
		}
	}

	public String saveFile(MultipartFile file, HttpServletRequest request) {

		// 이제 파일이 저장될 경로를 설정해야된다.
		// 웹 서버 contextPath 불러와서 폴더의 경로를 가져온다.(webapp하위의 resources이다.)
		String root = request.getSession().getServletContext().getRealPath("resources");

		// 파일의 경로는 root 하위의 buploadFiles이다.
		// \를 문자로 인식하기 위해 \\를 사용한다.
		String savePath = root + "\\buploadFiles";

		File folder = new File(savePath); // savePath의 폴더를 불러와서

		if (!folder.exists()) { // 해당 경로에 폴더가 있는지 여부를 판단하는 메소드
			folder.mkdirs(); // 폴더가 없다면 새롭게 생성
		}

		// 게시판같은 경우 많은 회원들이 동시에 올릴수도 있고 겹치는 파일을 올릴수도 잇기 때문에
		// 파일명을 rename하는 과정이 필요하다. rename할때 "년월일시분초.확장자"로 변경하자

		String originFileName = file.getOriginalFilename();

		// lastIndexOf(".") - 뒤에 있는 . 의 index번호
		// [참고] : SimpleDateFormat 클래스는 java.text패키지에 있는 클래스로 생성한 날짜의 표기법을 
		//         우리가 원하는 형태로 표현해주기위해서 사용하는 클래스
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "."
				+ originFileName.substring(originFileName.lastIndexOf(".") + 1);

		String renamePath = folder + "\\" + renameFileName; // 실제 저장될 파일 경로 + 파일명

		try {
			file.transferTo(new File(renamePath)); // 전달받은 file이 rename명으로 저장 이때 파일 저장된다.

		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}

		return renameFileName;
	}

	@RequestMapping("bdetail.do")
	public ModelAndView boardDetail(ModelAndView mv, int bId,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {

		Board b = bService.selectBoard(bId);

		if (b != null) {
			mv.addObject("b", b).addObject("currentPage", currentPage).setViewName("board/boardDetailView");
		} else {
			mv.addObject("msg", "게시글 상세조회 실패").setViewName("common/errorPage");
		}

		return mv;
	}

	@RequestMapping("bupView.do")
	public ModelAndView boardUpdateView(ModelAndView mv, int bId) {
		mv.addObject("b", bService.selectUpdateBoard(bId)).setViewName("board/boardUpdateForm"); 
		return mv;
	}

	@RequestMapping("bupdate.do")
	public ModelAndView boardUpdate(ModelAndView mv, Board b, HttpServletRequest request,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file) {

		if (file != null && !file.isEmpty()) {// 새로 업로드된 파일이 있다면

			if (b.getRenameFileName() != null) { // 기존의 파일이 존재했을 경우 기존 파일 삭제해줘야됨
				deleteFile(b.getRenameFileName(), request); // 이따가 구현할 게시판 삭제의 경우에도 파일을 지워야 되니깐 따로 메소드로 구현
				// 지우고자 하는 파일명과 request를 전달했을 때 서버에 있는 파일 삭제 되는
			}

			String renameFileName = saveFile(file, request);

			if (renameFileName != null) {
				b.setOriginalFileName(file.getOriginalFilename());
				b.setRenameFileName(renameFileName);
			}
		}

		int result = bService.updateBoard(b);

		if (result > 0) {
			mv.addObject("bId", b.getbId()).setViewName("redirect:bdetail.do");
		} else {
			mv.addObject("msg", "수정 실패").setViewName("common/errorPage");
		}

		return mv;
	}

	public void deleteFile(String fileName, HttpServletRequest request) {

		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";

		File f = new File(savePath + "\\" + fileName);

		if (f.exists()) {
			f.delete();
		}

	}

	@RequestMapping("bdelete.do")
	public String boardDelete(int bId, HttpServletRequest request) {
		Board b = bService.selectUpdateBoard(bId);

		if (b.getRenameFileName() != null) {
			deleteFile(b.getRenameFileName(), request);
		}

		int result = bService.deleteBoard(bId);

		if (result > 0) {
			return "redirect:blist.do";
		} else {
			return "common/errorPage";
		}
	}

	// ------------------ ajax 적용 ------------------------------
	/**
	 * 1번. Stream 이용해서 json배열 보내기
	 * 
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping("topList.do") 
	public void boardTopList(HttpServletResponse response) throws IOException {

		response.setContentType("application/json; charset=utf-8");

		ArrayList<Board> list = bService.selectTopList(); 

		// 조회수 top 리스트들을 조회해온 list를 JSONArray로 바꿈 
		JSONArray jArr = new JSONArray();

		// 다만 createDate와 같은 Date 형식은 SimpleDateFormat을 이용해서 문자열로 바꿔주자 
		// Why? json객체에 date 값 못담는다. --> 담으면 통신오류 발생 
		SimpleDateFormat sdf = new	SimpleDateFormat("yyyy-MM-dd");

		for(Board b : list) {
			JSONObject jObj = new JSONObject(); 
			jObj.put("bId",b.getbId()); 
			jObj.put("bTitle", b.getbTitle()); 
			jObj.put("bWriter",b.getbWriter()); 
			jObj.put("originalFileName", b.getOriginalFileName());
			jObj.put("bCount", b.getbCount()); 
			jObj.put("bCreateDate",sdf.format(b.getbCreateDate()));
			jArr.add(jObj); 
		}

		System.out.println(jArr);

		PrintWriter out = response.getWriter();
		out.print(jArr.toJSONString());
	}

	/**
	 * 2. Gson이용하는 방법
	 * 
	 * 컬렉션을 아주 쉽게 json 객체로 전송하는 방법으로 Stream을 이용하거나 Jackson을 이용하는 방법에서는 어떤 처리를 했었지만
	 * Gson을 이용한다면 어떠한 처리없이 바로 컬렉션을 보낼 수 있다. --> Googel만세!
	 * 
	 * 보통 Gson을 그냥 생성해서 보내지만 어떠한 셋팅을 해야된다면 GsonBuilder를 이용해서 Gson을 생성하면된다.
	 * 
	 * @param response
	 * @throws JsonIOException
	 * @throws IOException
	 */

//	@RequestMapping(value="topList.do")
//	public void boardTopList(HttpServletResponse response) throws JsonIOException, IOException {
//
//		ArrayList<Board> list = bService.selectTopList(); // selectTopList 메소드 추가해주자
//
//		response.setContentType("application/json; charset=utf-8");
//
//		// gson 라이브러리 추가
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // 날짜 포맷하기 위해 GsonBuilder를 이용해서 Gson객체 생성
//		gson.toJson(list, response.getWriter());
//	}

	
	/**
	 * 댓글 리스트 조회
	 * @param response
	 * @param bId
	 * @throws JsonIOException
	 * @throws IOException
	 */
	@RequestMapping("rList.do")
	public void getReplyList(HttpServletResponse response, int bId) throws JsonIOException, IOException {

		ArrayList<Reply> rList = bService.selectReplyList(bId);

		response.setContentType("application/json; charset=utf-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(rList, response.getWriter());
	}

	/**
	 * 댓글 추가
	 * @param r
	 * @return
	 */
	@RequestMapping("addReply.do")
	@ResponseBody
	public String addReply(Reply r) {

		int result = bService.insertReply(r);

		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}

package com.ict.spring.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ict.spring.member.model.service.MemberService;
import com.ict.spring.member.model.vo.Member;

/* //세션 여러개로 처리하고 싶을 경우(로그인 분기처리)
 * @SessionAttributes({"loginUser","loginAdmin"}) // 세션이 분할되어 처리된다.
 */
@SessionAttributes("loginUser") //실행할때 모델 자체에 loginUser라는 키값을 가지고 있음
								//session 매번 선언 안해도됨!! ModelAttribute만 해주면 된다.
@Controller //Controller타입의 클래스로 선언해서 빈 스캐닝에 자동으로 등록시켜서 빈객체를 만들겠다.
			//이름을 지정하지 않으면 클래스명 앞에 글자를 소문자로 해서 id가 만들어진다.
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	// 암호화 처리(spring-security에 bean등록 후) 후 작성 
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	// 로깅시 추가
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/*
	 * @RequestMapping(value="login.do",method=RequestMethod.POST)
	 * 
	 *  @RequestMapping 타입의 어노테이션을 붙여줌으로써 HandlerMapping 등록
	 *  
	 *  @RequestMapping의 속성
	 *  여러개의 속성을 명시 할 때는 "value=","method="를 명시해야되고
	 *  value만 명시해야되는 경우 직접적으로 @RequestMapping("login.do")처럼 생략가능
	 *  
	 *  (value="",method="")와 같이 매핑 조건을 부여하고 전달하는 method방식을 지정해준다.
	 */
	
	// 파라미터를 전송하는 방법
	/*
	 * 1. HttpServletRequest를 통해 전송받기(기존 jsp/servlet때 방식) 
	 *    메소드의 매개변수로 HttpServletRequest를 작성하면 메소드 실행 시
	 *    스프링 컨테이너가 자동으로 객체를 인자로 주입해준다.
	 */
	
//	@RequestMapping(value="login.do",method=RequestMethod.POST)
//	public String memberLogin(HttpServletRequest request) {
//		String id  = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//	}
	
	/*
	 * 2. @RequestParam 어노테이션 방식
	 *  
	 *    스프링에서는 조금 더 간단하게 파라미터를 받아올 수 있는 방법 제공
	 *    HttpServleet과 비슷하게 request객체를 이용하여 데이터를 전송받는 방법
	 *    
	 *    파라미터 value속성에 없는 값이 넘어오는 경우 ""이기 때문에 에러는 안난다.
	 */
	
//	@RequestMapping(value="login.do",method=RequestMethod.POST)
//	public String memberLogin(@RequestParam("id") String id,
//			                  @RequestParam("pwd") String pwd) {
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//		
//	}
	
	/*
	 * 3. @RequestParam 어노테이션 생략
	 * 
	 *   위의 어노테이션을 생략해도 파라미터 값을 가져와서 변수에 저장할 수 있다.
	 *   (단, 매개변수를 name값과 동일하게 해야 자동으로 값이 주입된다.)
	 *   
	 *   다만 어노테이션을 생략할 경우 defaultValue와 required설정 불가
	 *   없는 파라미터일 경우 null값이 입력
	 */
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(String id, String pwd) {
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//	}
	
	/*
	 *  4. @ModelAttribute를 이용한 값 전달 방법
	 *  
	 *  요청 파라미터가 많은 경우 객체 타입으로 넘겨 받게 된다.
	 *  -->  기본생성자와 setter를 이용한 주입방식
	 *       꼭, 기본생성자와 setter생성자가 둘다 있어야한다.
	 *       
	 *   이를 커맨드객체 방식이라고 한다.
	 *   스프링 컨테이너가 기본생성자를 통해서 Member객체를 생성하고
	 *   setter메소드로 꺼낸 파라미터들로 값을 변경한 후에
	 *   현재 이 메소드를 호출할 때 인자로 전달하며 호출하는 방식
	 *   (주의 : 반드시 name속성의 값과 필드명이 동일해야한다, 또한 setter작성하는 규칙에 맞게 작성되어야한다.)
	 *   
	 */
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(@ModelAttribute Member m) {
//		System.out.println("ID : " + m.getId());
//		System.out.println("PWD: " + m.getPwd());
//		
//		return "home";
//	}
	
	/*
	 * 5. @ModelAttribute 어노테이션 생략하고 객체로 바로 전달하는 방법
	 *    어노테이션을 생략해도 자동으로 커맨드 객체로 매핑이된다.
	 *    생략 가능한 어노테이션이 많지만 가독성을 위해 보통 다 적어주는게 좋다.
	 * 
	 */
//	@RequestMapping(value="login.do",method=RequestMethod.POST)
//	public String memberLogin(Member m, HttpSession session) {
//		System.out.println("ID : " + m.getId());
//		System.out.println("PWD: " + m.getPwd());
//	
//		Member loginUser = mService.loginMember(m);
//		
//		if(loginUser != null) {
//			// 로그인 성공
//			session.setAttribute("loginUser", loginUser);
//		}else {
//			// 로그인 실패
//			// errorPage로 보내준다.
//			return "common/errorPage";
//		}
//		return "redirect:home.do";
//	}
	
	// 전달받는것에 대한게 아니라 요청 후 전달하고자 하는 데이터가 있을 경우에 대한 방법
	/*
	 	1. Model 객체를 사용하는 방법
	 	 커맨드 객체로 Model을 사용하게되면 뷰로 전달하고자하는 데이터를 맵형식(key,value)로 담을 때 사용한다.
	 */
	
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(Member m,Model model,HttpSession session) {
//		
//		Member loginUser = mService.loginMember(m);
//		
//		if(loginUser != null) {
//			// 로그인 성공
//			session.setAttribute("loginUser", loginUser);
//			return "redirect:home.do";
//		}else {
//			model.addAttribute("msg","로그인 실패");
//			return "common/errorPage";
//		}
//	}
	
	/*
	 * 2. ModelAndView 객체를 사용하는 방법
	 * 
	 * Model은 전달하고자 하는 데이터를 맵 형식으로 담는 공간이라고 했다면
	 * View는 requestDispatcher처럼 forward 할 뷰 페이지 정보를 담은 객체
	 * 
	 * ModelAndView는 이 두가지를 합쳐놓은 객체
	 */
//	@RequestMapping(value="login.do",method=RequestMethod.POST)
//	public ModelAndView memberLogin(Member m, ModelAndView mv,HttpSession session) {
//		
//		Member loginUser = mService.loginMember(m);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//			mv.setViewName("redirect:home.do");
//		}else {
//			mv.addObject("msg", "로그인 실패!");
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
//	}

	/**
	 * 로그아웃용 컨트롤러
	 * @param session
	 * @return
	 */
//	@RequestMapping("logout.do")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:home.do";
//	}
	
	/*
	 * 3. session에 저장할 때 @SessionAttributes 
	 *    Model에 Attribute가 추가될 때 자동으로 키값을 찾아서 세션에 등록하는 기능을 제공하는 어노테이션 
	 */
//	@RequestMapping(value="login.do",method=RequestMethod.POST)
//	public String memberLogin(@ModelAttribute Member m, Model model) {
//		
//		Member loginUser = mService.loginMember(m);
//		
//		if(loginUser != null) {
//			model.addAttribute("loginUser", loginUser);
//			/* model.addAttribute("loginAdmin", loginUser); */
//			return "redirect:home.do";
//		}else {
//			model.addAttribute("msg", "로그인 실패");
//			return "common/errorPage";
//		}
//	}
	
	@RequestMapping("logout.do")
	public String logout(SessionStatus status) {
		// 로그아웃을 처리를 위해서 커맨드 객체로 세션의 상태를 관리할 수있는 SessionStatus 객체가 필요하다.
		
		// 세션의 상태를 확정지어주는 메소드 호출
		status.setComplete();
		
		return "redirect:home.do";
	}
	
	// -------------------------------- 회원 가입 -------------------------------//
	@RequestMapping("enrollView.do")
	public String enrollView() {
		
		if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
			logger.debug("회원등록페이지");
		
		return "member/memberInsertForm";
	}
	
	@RequestMapping("minsert.do")
	public String insertMember(@ModelAttribute Member m, Model model, 
							   @RequestParam("post") String post,
							   @RequestParam("address1") String address1,
							   @RequestParam("address2") String address2) {
		
		// 회원가입전에 회원정보를 출력
		System.out.println("Member 정보 : " + m);
		System.out.println("Address 정보 : " + post + ", " + address1 + ", " + address2);
		
		System.out.println("암호화 처리 후 값 : " + bcryptPasswordEncoder.encode(m.getPwd()));
		
		/*
		 * 비밀번호 -> 평문으로 되어있다. 1234 
		 * DB에 저장을 할때 평문으로 저장하면 안되기 때문에 "암호화" 처리를 한다.
		 * 
		 * 스프링 시큐리티라는 모듈에서 제공하는 bcrypt라는 암호화 방식으로 암호화 처리를 할꺼다.
		 * 
		 * * bcrypt란?
		 *   DB에 비밀번호를 저장할 목적으로 설계되었다.
		 *   
		 *   jsp/servlet 에서 했던 SHA-512암호화(단방향해쉬알고리즘)
		 *   
		 *   단점 : 111 평문 동일한 암호화 코드를 반화한다. 
		 *   
		 *   해결점 : 솔팅(salting) -> 원문에 아주작은랜덤문자열 추가해서 암호화 코드를 발생시킨다.
		 */
		
		// 기존의 평문을 암호문으로 바꾸서 m객체에 다시 담자.
		String encPwd = bcryptPasswordEncoder.encode(m.getPwd());
		
		// setter를 통해서 Member객체의 pwd를 변경
		m.setPwd(encPwd);
		
		// 주소데이터를 ", "를 구분자로 저장
		if(!post.equals("")) {
			m.setAddress(post + ", " + address1 + ", " + address2);
		}
		
		System.out.println("수정된 Member객체 : " + m);
		
		// 회원가입 서비스를 호출
		int result = mService.insertMember(m);
		
		if(result > 0) {
			return "redirect:home.do";
		}else {
			model.addAttribute("msg","회원가입실패!");
			return "common/errorPage";
		}
		
	}
	
	/**
	 * 암호화 처리 후에 로그인 부분 수정
	 * @param m
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public String memberLogin(@ModelAttribute Member m, Model model) {
		
		Member loginUser = mService.loginMember(m);
		
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getPwd(), loginUser.getPwd())) {
			model.addAttribute("loginUser", loginUser);
			return "redirect:home.do";
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("myInfo.do")
	public String myInfoView() {
		return "member/myPage"; // myPage.jsp로 이동한다.
	}
	
	@RequestMapping("mupdate.do")
	public String memberUpdate(@ModelAttribute Member m,Model model,
			                   @RequestParam("post") String post,
			                   @RequestParam("address1") String addr1,
			                   @RequestParam("address2") String addr2) {
		System.out.println("Member : " + m);
		
		String encPwd = bcryptPasswordEncoder.encode(m.getPwd());
		
		// setter를 통해서 Member객체의 pwd를 변경
		m.setPwd(encPwd);
		
		// 주소 데이터를 ","를 구분자로 두고 저장
		if(!post.equals("")) {
			m.setAddress(post + ","+addr1 + "," +addr2);
		}
		
		int result = mService.updateMember(m);
		
		if(result > 0) {
			model.addAttribute("loginUser", m);
			return "redirect:home.do";
		}else {
			model.addAttribute("msg","회원 정보 수정 실패!");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("mdelete.do")
	public String memberDelete(SessionStatus status
			                  ,@RequestParam("id") String id
			                  ,Model model) {
		
		int result = mService.deleteMember(id);
		
		if(result > 0) {
			return "redirect:logout.do";
		}else {
			model.addAttribute("msg", "회원 탈퇴 실패!");
			return "common/errorPage";
		}
	}
	
	/** 1. 기존의 방식대로 Stream을 이용한 방식
	 * @param id
	 * @param response
	 */
//	@RequestMapping("idCheck.do")
//	public void idCheck(String id, HttpServletResponse response) throws IOException {
//		int result = mService.idCheck(id);
//
//		PrintWriter out = response.getWriter();
//
//		if(result > 0) { // 중복 존재
//			out.print("fail");
//		}else {
//			out.print("ok");
//		}
//	}

	@ResponseBody
	@RequestMapping("idCheck.do")
	public String idCheck(String id) {
		int result = mService.idCheck(id);
		
		if(result>0) {
			return "fail";
		}else {
			return "ok";
		}
	}
	
}
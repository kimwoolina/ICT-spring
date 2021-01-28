package com.ict.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ict.spring.member.model.vo.Member;

public class BlistInterceptor extends HandlerInterceptorAdapter {
	/*
	 * Interceptor 개요
	 * 
	 * HandlerInterceptor(-> HandlerInterceptorAdapter를 상속해서 구현)
	 * 
	 * - 세가지의 메소드를 오버라이딩해서 사용
	 * 
	 * 1. preHandle(전처리) : DispatcherServlet이 컨트롤러를 호출하기전
	 * 2. postHandle(후처리): 컨트롤러에서 DispatcherServlet으로 리턴되는 순간(ModelAndView를 통해 작업결과 참조가능)
	 * 3. afterCompletion(뷰단처리후) : 모든 뷰에서 최종 결과를 생성하는 일을 포함한 모든 작업이 완료된 후 
	 *                              요청처리 중에 사용한 리소스를 반환해주기에 적당한 메소드
	 */

	private Logger logger = LoggerFactory.getLogger(BlistInterceptor.class);

	// 게시판 리스트 조회 요청 전 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		Member loginUser = (Member)session.getAttribute("loginUser");

		if(loginUser == null) {
			logger.info("비로그인 상태에서 [" + request.getRequestURI() + "] 접근하려고함");

			request.setAttribute("msg", "로그인 후 이용하세요.");
			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);

			return false; // false를 반환해야 controller를 실행 안한다.
		}

		return true;
	}


}
package com.ict.spring.common.interceptor;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ict.spring.member.model.vo.Member;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	//로그인 요청 후 처리
	//성공적으로 로그인 되었을 경우 로그인 이력을 남기게 하자
	//postHandle() 메서드는 컨트롤러가 실행된 후에 호출된다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			InetAddress local = InetAddress.getLocalHost();
			logger.info(loginUser.getId() + " " + local.getHostAddress());
		}
	}


	
	

	
}

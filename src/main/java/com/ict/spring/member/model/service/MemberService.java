package com.ict.spring.member.model.service;

import com.ict.spring.member.model.vo.Member;

public interface MemberService {

	/**
	 * 1. 회원 로그인 서비스를 위한 메소드
	 * @param m
	 * @return
	 */
	Member loginMember(Member m);
}

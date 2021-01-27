package com.ict.spring.member.model.service;

import com.ict.spring.member.model.vo.Member;

public interface MemberService {

	/**
	 * 1. 회원 로그인 서비스를 위한 메소드
	 * @param m
	 * @return
	 */
	Member loginMember(Member m);
	
	/** 2. 회원 가입 서비스를 위한 메소드
	 * @param m
	 * @return
	 */
	int insertMember(Member m);
	
	
	/** 3. 회원 정보 수정 서비스를 위한 메소드
	 * @param m
	 * @return
	 */
	int updateMember(Member m);
	
	
	/** 4. 회원 탈퇴 서비스를 위한 메소드
	 * @param id
	 * @return
	 */
	int deleteMember(String id);
}

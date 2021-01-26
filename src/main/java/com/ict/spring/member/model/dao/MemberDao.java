package com.ict.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.spring.member.model.vo.Member;

@Repository("mDao")
public class MemberDao {
	
	//데이터베이스 연결객체 (root-context에서 작성한 bean으로 생성되어 주입이된다.)
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public Member loginMember(Member m) {
		return (Member)sqlSession.selectOne("memberMapper.loginMember",m);
	}

	public int insertMember(Member m) {
		return sqlSession.insert("memberMapper.insertMember",m);
	}

}

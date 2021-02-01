package com.ict.spring.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.spring.notice.model.vo.Notice;

@Repository("noticeDao")
public class NoticeDao {
	//스프링-마이바티스 연동 객체 연결함 : root-context.xml에 선언되어 있음
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public NoticeDao() {}
	
	public ArrayList<Notice> selectList(){
		List<Notice> list = sqlSession.selectList("noticeMapper.selectAll");
	
		return (ArrayList<Notice>)list;
	}
	
	public Notice selectOne(int nid) {
		return sqlSession.selectOne("noticeMapper.selectNotice",nid);
	}
	
	public int insertNotice(Notice notice) {
		return sqlSession.insert("noticeMapper.insertNotice", notice);
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public int deletenotice(int nid) {
		return 0;
	}
	
	//최근 공지글 몇개 조회
	public ArrayList<Notice> selectNewTop3(){
		return null;
	}
	
}
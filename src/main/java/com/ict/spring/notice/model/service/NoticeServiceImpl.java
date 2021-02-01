package com.ict.spring.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.spring.notice.model.dao.NoticeDao;
import com.ict.spring.notice.model.vo.Notice;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	//의존성 주입(new 객체 생성 구문 자동으로)
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public ArrayList<Notice> selectAll() {
		return noticeDao.selectList();
	}

	@Override
	public Notice selectnotice(int nid) {
		return null;
	}

	@Override
	public int insertNotice(Notice notice) {
		return 0;
	}

	@Override
	public int updateNotice(Notice notice) {
		return 0;
	}

	@Override
	public int deleteNotice(int nid) {
		return 0;
	}

	@Override
	public ArrayList<Notice> selectNewTop3() {
		return null;
	}

}
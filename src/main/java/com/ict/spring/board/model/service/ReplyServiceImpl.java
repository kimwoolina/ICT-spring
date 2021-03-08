package com.ict.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.spring.board.model.dao.ReplyDao;
import com.ict.spring.board.model.vo.Reply;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public int insertReply(Reply reply) {
		return replyDao.insertReply(reply);
	}

	@Override
	public int updateReply(Reply reply) {
		return replyDao.updateReply(reply);
	}

	@Override
	public int deleteReply(int rid) {
		return replyDao.deleteReply(rid);
	}

	@Override
	public Reply selectReply(int rid) {
		return replyDao.selectReply(rid);
	}

	@Override
	public ArrayList<Reply> selectList(int ref_bid) {
		return replyDao.selectList(ref_bid);
	}

}
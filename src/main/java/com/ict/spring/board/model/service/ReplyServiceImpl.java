package com.ict.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ict.spring.board.model.vo.Reply;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Override
	public int insertReply(Reply reply) {
		return 0;
	}

	@Override
	public int updateReply(Reply reply) {
		return 0;
	}

	@Override
	public int deleteReply(int rid) {
		return 0;
	}

	@Override
	public Reply selectReply(int rid) {
		return null;
	}

	@Override
	public ArrayList<Reply> selectList() {
		return null;
	}

}

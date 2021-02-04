package com.ict.spring.board.model.service;

import java.util.ArrayList;

import com.ict.spring.board.model.vo.Reply;

public interface ReplyService {
	int insertReply(Reply reply);
	int updateReply(Reply reply);
	int deleteReply(int rid);
	Reply selectReply(int rid);
	ArrayList<Reply> selectList();
}

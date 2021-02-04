package com.ict.spring.board.model.vo;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("replyDao")
public class ReplyDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertReply(Reply reply) {
		return sqlSession.insert("replyMapper.insertReply", reply);
	}

	public int updateReply(Reply reply) {
		return sqlSession.update("replyMapper.updateReply", reply);
	}

	public int deleteReply(int rid) {
		return sqlSession.delete("replyMapper.deleteReply", rid);
	}

	public Reply selectReply(int rid) {
		return sqlSession.selectOne("replyMapper.selectReply", rid);
	}

	public ArrayList<Reply> selectList(int ref_bid) {
		List<Reply> list = sqlSession.selectList("replyMapper.selectList", ref_bid);
		return (ArrayList<Reply>)list;
	}
}

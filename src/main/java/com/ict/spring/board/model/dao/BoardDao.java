package com.ict.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.spring.board.model.vo.Board;
import com.ict.spring.board.model.vo.PageInfo;
import com.ict.spring.board.model.vo.Reply;

@Repository("bDao")
public class BoardDao {
	
	@Autowired
    private SqlSessionTemplate sqlSession;

    public int getListCount() {
	return sqlSession.selectOne("boardMapper.getListCount");
    }

    public ArrayList<Board> selectList(PageInfo pi) {

	int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
	RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());

	return (ArrayList) sqlSession.selectList("boardMapper.selectList", null, rowBounds);
    }

    public int insertBoard(Board b) {
	return sqlSession.insert("boardMapper.insertBoard", b);
    }

    public int updateCount(int bId) {
	return sqlSession.update("boardMapper.updateCount", bId);
    }

    public Board selectBoard(int bId) {
	return sqlSession.selectOne("boardMapper.selectBoard", bId);
    }

    public int updateBoard(Board b) {
	return sqlSession.update("boardMapper.updateBoard", b);
    }

    public int deleteBoard(int bId) {
	return sqlSession.update("boardMapper.deleteBoard", bId);
    }

    public ArrayList<Board> selectTopList() {
	return (ArrayList) sqlSession.selectList("boardMapper.selectTopList");
    }

    public ArrayList<Reply> selectReplyList(int bId) {
	return (ArrayList) sqlSession.selectList("boardMapper.selectReplyList", bId);
    }

    public int insertReply(Reply r) {
	return sqlSession.insert("boardMapper.insertReply", r);
    }
}

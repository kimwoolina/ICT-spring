package com.ict.spring.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.spring.board.model.vo.Board;

@Repository("boardDao")
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Board> selectTop3() {
		List<Board> list = sqlSession.selectList("boardMapper.selectTop3");
		return (ArrayList<Board>)list;
	}

	public int getListCount() {
		return 0;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		return null;
	}

	public Board selectBoard(int bid) {
		return sqlSession.selectOne("boardMapper.selectBoard", bid);
	}

	public int addReadCount(int bid) {
		return sqlSession.update("boardMapper.updateBCount", bid);
	}

	public int insertBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteBoard(int bid) {
		// TODO Auto-generated method stub
		return 0;
	}

}

/*
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
public class BoardDao_test {
	
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
*/

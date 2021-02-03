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
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Board selectBoard(int bid) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addReadCount(int bid) {
		// TODO Auto-generated method stub
		return 0;
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

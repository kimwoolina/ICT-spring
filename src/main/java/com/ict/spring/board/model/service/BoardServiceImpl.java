package com.ict.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.spring.board.model.dao.BoardDao;
import com.ict.spring.board.model.vo.Board;
import com.ict.spring.board.model.vo.PageInfo;
import com.ict.spring.board.model.vo.Reply;

@Service("bSerivce")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao bDao;

	@Override
	public int getListCount() {
		return bDao.getListCount();
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		return bDao.selectList(pi);
	}

	@Override
	public int insertBoard(Board b) {
		return bDao.insertBoard(b);
	}

	@Override
	public Board selectBoard(int bId) {
		int result = bDao.updateCount(bId);
		
		if(result > 0) {
			return bDao.selectBoard(bId);
		}else {
			return null;
		}
	}

	@Override
	public Board selectUpdateBoard(int bId) {
		return bDao.selectBoard(bId);
	}

	@Override
	public int updateBoard(Board b) {
		return bDao.updateBoard(b);
	}

	@Override
	public int deleteBoard(int bId) {
		return bDao.deleteBoard(bId);
	}

	@Override
	public ArrayList<Board> selectTopList() {
		return bDao.selectTopList();
	}

	@Override
	public ArrayList<Reply> selectReplyList(int bId) {
		return bDao.selectReplyList(bId);
	}

	@Override
	public int insertReply(Reply r) {
		return bDao.insertReply(r);
	}

}

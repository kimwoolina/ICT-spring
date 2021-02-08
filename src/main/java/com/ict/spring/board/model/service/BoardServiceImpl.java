package com.ict.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.spring.board.model.dao.BoardDao;
import com.ict.spring.board.model.vo.Board;
import com.ict.spring.common.SearchAndPage;
import com.ict.spring.common.SearchDate;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
   @Autowired //의존성 어노테이션 
   private BoardDao boardDao;
   
   @Override
   public ArrayList<Board> selectTop3() {
      return boardDao.selectTop3();
   }

   @Override
   public int getListCount() {
      return boardDao.getListCount();
   }

   @Override
   public ArrayList<Board> selectList(int currentPage, int limit) {
      return boardDao.selectList(currentPage, limit);
   }

   @Override
   public Board selectBoard(int bid) {
      return boardDao.selectBoard(bid);
   }

   @Override
   public int addReadCount(int bid) {
      return boardDao.addReadCount(bid);
   }

   @Override
   public int insertBoard(Board board) {
      return boardDao.insertBoard(board);
   }

   @Override
   public int updateBoard(Board board) {
      return boardDao.updateBoard(board);
   }

   @Override
   public int deleteBoard(int bid) {
      return boardDao.deleteBoard(bid);
   }

	@Override
	public ArrayList<Board> selectSearchTitle(SearchAndPage searches) {
		return boardDao.selectSearchTitle(searches);
	}
	
	@Override
	public ArrayList<Board> selectSearchWriter(SearchAndPage searches) {
		return boardDao.selectSearchWriter(searches);
	}
	
	@Override
	public ArrayList<Board> selectSearchDate(SearchAndPage searches) {
		return boardDao.selectSearchDate(searches);
	}

	@Override
	public int getSearchTitleListCount(String keyword) {
		return boardDao.getSearchTitleListCount(keyword);
	}

	@Override
	public int getSearchWriterListCount(String keyword) {
		return boardDao.getSearchWriterListCount(keyword);
	}

	@Override
	public int getSearchDateListCount(SearchDate dates) {
		return boardDao.getSearchDateListListCount(dates);
	}

}
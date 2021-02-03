package com.ict.spring.board.model.service;

import java.util.ArrayList;

import com.ict.spring.board.model.vo.Board;

public interface BoardService {
   //조회수 많은 게시글 top3
   ArrayList<Board> selectTop3();
   int getListCount();
   //목록조회
   ArrayList<Board> selectList(int currentPage, int limit);
   //상세보기
   Board selectBoard(int bid);
   int addReadCount(int bid);
   //원글 조회
   int insertBoard(Board board);
   int updateBoard(Board board);
   int deleteBoard(int bid);
   
   
}
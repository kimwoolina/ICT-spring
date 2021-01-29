package com.ict.spring.board.model.service;

import java.util.ArrayList;

import com.ict.spring.board.model.vo.Board;
import com.ict.spring.board.model.vo.PageInfo;
import com.ict.spring.board.model.vo.Reply;

public interface BoardService {
	// 1. 게시판 전체 리스트 조회 (페이징 처리 시 필요한 두개의 메소드)
	/**
	 * 1_1.게시판 총 갯수 조회
	 * @return
	 */
	int getListCount();
	/**
	 * 1_2. 게시판 리스트 조회
	 * @param pi
	 * @return
	 */
	ArrayList<Board> selectList(PageInfo pi);
	
	/**
	 * 2. 게시판 작성
	 * @param b
	 * @return
	 */
	int insertBoard(Board b);
	
	/**
	 * 3. 게시판 상세조회
	 * @param bId
	 * @return
	 */
	Board selectBoard(int bId);
	
	/**
	 * 4. 게시판 수정할 내용 조회
	 * @param bId
	 * @return
	 */
	Board selectUpdateBoard(int bId);
	
	/**
	 * 4_1. 게시판 수정
	 * @param b
	 * @return
	 */
	int updateBoard(Board b);
	
	/**
	 * 5. 게시판 삭제
	 * @param bId
	 * @return
	 */
	int deleteBoard(int bId);
	
	/**
	 * 6. 게시판 Top5
	 * @return
	 */
	ArrayList<Board> selectTopList();
	
	/**
	 * 7. 댓글조회
	 * @param bId
	 * @return
	 */
	ArrayList<Reply> selectReplyList(int bId);
	
	/**
	 * 8. 댓글 추가
	 * @param r
	 * @return
	 */
	int insertReply(Reply r);
}

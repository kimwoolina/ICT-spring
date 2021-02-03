package com.ict.spring.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.spring.board.model.service.BoardService;
import com.ict.spring.board.model.vo.Board;

@Controller
public class BoardController {
   @Autowired
   private BoardService boardService;
   
   //ajax 로 인기 게시글 조회 처리용
      @RequestMapping(value="btop3.do", method=RequestMethod.POST)
      @ResponseBody
      public String selectTop3Method(HttpServletResponse response) throws UnsupportedEncodingException   {
         //최신 공지글 3개 조회해 옴
         ArrayList<Board> list = boardService.selectTop3(); //결과를 받아줌
         
         // 전송용 json 객체 준비
         JSONObject sendJson = new JSONObject();
         // list 옮길 json 배열 준비
         JSONArray jarr = new JSONArray();
         
         // list 를 jarr 로 옮기기(복사)
         for(Board board : list) {
            //notice 필드값 저장할 json 객체 생성
            JSONObject job = new JSONObject();
            
            job.put("bid", board.getBid());   //map이랑 같다
            job.put("btitle", URLEncoder.encode(board.getBtitle(), "utf-8"));   //인코딩 해서 제이슨 객체 안에 담는다
            job.put("bcount", board.getBcount());   
            //날짜형식의 데이터를 json객체에 담을 때 주의사항, 뷰쪽에서 꺼낼 때 에러가나서, string형으로 바꿔서 json에 담아줘야한다.
            
            // job 를 jarr 에 저장
            jarr.add(job);   
         }
         
         // 전송용 json 객체에 jarr 담음
         sendJson.put("list", jarr);
         
         return sendJson.toJSONString();   //jsonView 가 리턴됨
         
      }
      
   
   @RequestMapping("blist.do")
   public String boardList() {
      return "board/boardListView";
   }
}
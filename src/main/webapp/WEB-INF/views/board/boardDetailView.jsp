<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%-- 응답온 페이지값 추출함 --%>
<c:set var="currentPage" value="${ requestScope.page }" /> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
   hideReplyForm();  //뷰 페이지 처음 실행시에는 댓글달기 폼이 안 보이게 함
   
   //jquery ajax 로 해당 게시글에 대한 댓글 조회 요청
   //해당 게시글의 번호를 전송함
   var bid = ${ board.bid };  //el 의 값을 변수에 대입
   var loginUser = "${ sessionScope.loginUser.id }";  //로그인한 회원 아이디 변수에 대입
   $.ajax({
      url: "${ pageContext.request.contextPath }/rlist.do",
      type: "post",
      data: { ref_bid: bid },  //전송값에 변수 사용
      dataType: "json",
      success: function(data){
         console.log("success : " + data);
         
         //object ==> string
         var jsonStr = JSON.stringify(data);
         //string ==> json 
         var json = JSON.parse(jsonStr);
         
         var values = "";
         for(var i in json.list){
            //본인이 등록한 댓글일 때는 수정/삭제 가능하게 함
            if(loginUser == json.list[i].rwriter){
               values += "<tr><td>" + json.list[i].rwriter 
               + "</td><td>" +  json.list[i].r_create_date 
               + "</td></tr><tr><td colspan='2'>"
               + "<form action='rupdate.do' method='post'>" 
               + "<input type='hidden' name='rid' value='" +  json.list[i].rid  + "'>"
               + "<input type='hidden' name='bid' value='${board.bid}'>"
               + "<textarea name='rcontent'>"
               + decodeURIComponent(json.list[i].rcontent).replace(/\+/gi, " ") 
               + "</textarea><input type='submit' value='수정'></form>"
               + "<button onclick='replyDelete(" + json.list[i].rid + ");'>삭제</button></td></tr>";   
            }else{  //본인 댓글이 아닐 때
               values += "<tr><td>" + json.list[i].rwriter 
                     + "</td><td>" +  json.list[i].r_create_date 
                     + "</td></tr><tr><td colspan='2'>"
                     + decodeURIComponent(json.list[i].rcontent).replace(/\+/gi, " ") 
                     + "</td></tr>";   
            }
         } //for in
         
         $("#rlistTbl").html($("#rlistTbl").html() + values);
      },
      error: function(jqXHR, textstatus, errorthrown){
         console.log("error : " + jqXHR + ", " + textstatus + ", " 
               + errorthrown);
      }
   });  //notice top3 ajax
   
});  //jquery document ready

function replyDelete(rid){
   location.href = "${ pageContext.request.contextPath }/rdel.do?rid=" + rid + "&bid=${ board.bid}";
}

function showReplyForm(){
   $("#replyDiv").css("display", "block");
}

function hideReplyForm(){
   $("#replyDiv").css("display", "none");
}
</script>
</head>
<body>
<%-- 상대경로만 사용 가능함 --%>
<c:import url="../common/menubar.jsp" />
<hr>
<h2 align="center">${ requestScope.board.bid } 번 게시글 상세보기</h2>
<br>
<table align="center" cellpadding="10" cellspacing="0" border="1" width="500">
<tr><th>제 목</th><td>${ board.btitle }</td></tr>
<tr><th>작성자</th><td>${ board.bwriter }</td></tr>
<tr><th>내 용</th><td>${ board.bcontent }</td></tr>
<tr><th>첨부파일</th>
<td>
<c:if test="${ empty board.original_filename }">
첨부파일 없음
</c:if>
<c:if test="${ !empty board.original_filename }">
<c:url var="bfd" value="/bfdown.do">
   <c:param name="ofile" value="${ board.original_filename }" />
   <c:param name="rfile" value="${ board.rename_filename }" />
</c:url>
<a href="${ bfd }">${ board.original_filename }</a>
</c:if>
</td></tr>
<tr align="center" valign="middle"><th colspan="2">
<%-- 로그인한 상태일 때 댓글달기 사용하게 함 --%>
<c:if test="${ !empty loginUser }">
   <button onclick="showReplyForm();">댓글달기</button>
   &nbsp; &nbsp;
</c:if>
<%-- 로그인한 상태이면서, 본인 글일때만 보여지게 함 --%>
<c:if test="${ !empty loginUser and loginUser.id eq board.bwriter }">
   <c:url var="buv" value="/bupview.do">
      <c:param name="bid" value="${board.bid }" />
      <c:param name="page" value="${ currentPage }" />
   </c:url>
   <a href="${ buv }">[수정페이지로 이동]</a> &nbsp; &nbsp; 
   <c:url var="bdl" value="/bdelete.do">
      <c:param name="bid" value="${ board.bid }" />      
   </c:url>
   <a href="${ bdl }">[글삭제]</a> &nbsp; &nbsp; 
</c:if>
<c:url var="bls" value="/blist.do">
   <c:param name="page" value="${ currentPage }"/>
</c:url>
<a href="${ bls }">[목록]</a>
</th></tr>
</table>
<hr>
<%-- 댓글목록 표시 영역 --%>
<div id="rlistView" style="border: 1px dotted gray;">
<table id="rlistTbl" align="center" cellspacing="0" cellpadding="5" border="1"></table>
</div>
<hr>
<%-- 댓글달기 폼 영역 --%>
<div id="replyDiv">
<form action="rinsert.do" method="post">
<input type="hidden" name="ref_bid" value="${ board.bid }">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr><th>작성자</th>
<td><input type="text" name="rwriter" readonly value="${ sessionScope.loginUser.id }"></td></tr>
<tr><th>내 용</th><td><textarea name="rcontent" rows="5" cols="50"></textarea></td></tr>
<tr><th colspan="2">
<input type="submit" value="댓글등록"> &nbsp; 
<input type="reset" value="댓글취소" onclick="hideReplyForm(); return false;"> </th></tr>
</table>
</form>  
</div>


</body>
</html>






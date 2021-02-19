<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

<c:set var="maxPage" value="${ requestScope.maxPage }" /> 
<c:set var="startPage" value="${ requestScope.startPage }"  /> 
<c:set var="endPage" value="${ requestScope.endPage }"  /> 
<c:set var="currentPage" value="${ requestScope.currentPage }"  /> 

<%-- 글쓰기 페이지 이동 요청 url --%>
<c:url var="bwf" value="/bwmove.do" />
   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
   showDiv();
   
   $("input[name=item]").on("change", function(){ //on으로 이벤트 설정가능 "change"이벤트일때 function()을 실행해라
            showDiv(); // = radio버튼상태가 바뀔때 showDiv를 실행해라
   });       
});
function showDiv(){
if($("input[name=item]").eq(0).is(":checked")){ //첫번째가 선택 되어 있느냐, 그러면 이 함수를 실행해라~! 라는 뜻
   $("#titleDiv").css("display", "block");
   $("#writerDiv").css("display", "none");
   $("#dateDiv").css("display", "none");
}
if($("input[name=item]").eq(1).is(":checked")){ //두번째가 선택 되어 있느냐, 그러면 이 함수를 실행해라~! 라는 뜻
   $("#titleDiv").css("display", "none");
   $("#writerDiv").css("display", "block");
   $("#dateDiv").css("display", "none");
}
if($("input[name=item]").eq(2).is(":checked")){ //세번째가 선택 되어 있느냐, 그러면 이 함수를 실행해라~! 라는 뜻
   $("#titleDiv").css("display", "none");
   $("#writerDiv").css("display", "none");
   $("#dateDiv").css("display", "block");
}
}
function showWriteForm(){   
   location.href = "${ bwf }";
}
</script>
</head>
<body>
<c:import url="../common/menubar.jsp" />
<hr>
<%-- 로그인한 사용자만 글쓰기 기능 사용할 수 있게 함 --%>
<c:if test="${ !empty sessionScope.loginUser }">
<div style="align:center; text-align:center;">
   <button onclick="showWriteForm();">글쓰기</button>
</div>
</c:if>
<br>
<%-- 검색기능 2021.02.08 --%>
<center>
<div>
      <h2> 검색할 항목을 선택하시오. </h2>
      <input type="radio" name = "item" value="title" checked> 제목 &nbsp; &nbsp; &nbsp;
      <input type="radio" name = "item" value="writer" > 작성자 &nbsp; &nbsp; &nbsp;
      <input type="radio" name = "item" value="date" > 날짜
</div>
<div id="titleDiv">
      <form action="bsearchTitle.do" method="post"> <!-- 여기서부터 서블릿 때랑 약간다름. method메소드가 따로따로 가게됨 -->
            <input type = "hidden" name="page" value="1"> <!-- 페이지 요청할때는 ${ page }대신에 그냥 무조건 1페이지로 가게끔 -->
            <label> 검색할 제목을 입력하시오 : <input type="search" name="keyword"></label>
            <input type="submit" value="검색">
      </form>
</div>
<div id="writerDiv">
         <form action="bsearchWriter.do" method="post"> <!-- 여기서부터 서블릿 때랑 약간다름. method메소드가 따로따로 가게됨 -->
            <input type = "hidden" name="page" value="1">
            <label> 검색할 작성자 아이디를 입력하시오 : <input type="search" name="keyword"></label>
            <input type="submit" value="검색">
      </form>
</div>
<div id="dateDiv">
         <form action="bsearchDate.do" method="post"> <!-- 여기서부터 서블릿 때랑 약간다름. method메소드가 따로따로 가게됨 -->
            <input type = "hidden" name="page" value="1">
            <label> 검색할 날짜를 입력하시오 : <input type="date" name="begin"> ~ <input type="date" name = "end"></label>
            <input type="submit" value="검색">
      </form>
</div>
</center>
<br>
<%-- 목록 출력 --%>
<div style="align:center;padding-left:400px;">
   <c:url var="blist" value="/blist.do" >
         <c:param name="page" value="1" />
   </c:url>
   <button onclick="javascript:location.href='${ blist }';">전체 목록 보기</button>
</div>
<br>
<table align="center" border="1" width="700" cellspacing="0">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th>
<th>첨부파일</th></tr>
<c:forEach items="${ requestScope.list }" var="b">
<tr>
   <td align="center">${ b.bid }</td>
   <td>   
   <c:url var="bdt" value="/bdetail.do">
      <c:param name="page" value="${ currentPage }"/>
      <c:param name="bid" value="${ b.bid }" />
   </c:url>
   <a href="${ bdt }">${ b.btitle }</a>
   </td>
   <td align="center">${ b.bwriter }</td>
   <td align="center">${ b.b_create_date }</td>
   <td align="center">${ b.bcount }</td>
   <td align="center">
      <c:if test="${ !empty b.original_filename }">
      ◎
      </c:if>
      <c:if test="${ empty b.original_filename }">
      &nbsp;
      </c:if>
   </td>
</tr>
</c:forEach>
</table>
<br>



<%-- 현재 페이지가 1이 아니면 링크설정, 현재 1페이지이면 링크없음 --%>
<c:if test="${ empty action }">
<%-- 페이징 처리 
   [맨처음][이전] 숫자...........  [다음][맨끝]
--%>
<div style="text-align: center;">
<c:if test="${ currentPage <= 1}">
[맨처음]
</c:if>
<c:if test="${ currentPage > 1 }">
   <c:url var="bls" value="/blist.do">
      <c:param name="page" value="1" />
   </c:url>
   <a href="${ bls }">[맨처음]</a>
</c:if> &nbsp;
<%-- 이전 그룹이 있으면 링크설정, 이전 그룹 없으면 링크없음 --%>
<c:if test="${ (currentPage - 10) < startPage and (currentPage - 10) >= 1 }">
   <c:url var="bls2" value="/blist.do">
      <c:param name="page" value="${ startPage - 10 }"/>
   </c:url>
   <a href="${ bls2 }">[이전]</a>
</c:if>
<c:if test="${ !((currentPage - 10) < startPage and (currentPage - 10) >= 1) }">
[이전]
</c:if> &nbsp; 
<%-- 가운데 표시할 페이지 그룹 숫자 링크 설정 --%>
<c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
    <c:if test="${ p eq currentPage }">
      <font size="4" color="red">[${ p }]</font>
   </c:if>
   <c:if test="${ p ne currentPage }">
      <c:url var="bls3" value="/blist.do">
         <c:param name="page" value="${ p }" />
      </c:url>
      <a href="${ bls3 }">${ p }</a>
   </c:if>
</c:forEach> &nbsp;
<%-- 다음 그룹이 있으면 링크설정, 다음 그룹 없으면 링크없음 --%>
<c:if test="${ (currentPage + 10) > endPage && (currentPage + 10) < maxPage }">
   <c:url var="bls4" value="/blist.do">
      <c:param name="page" value="${ endPage + 10 }"/>
   </c:url>
   <a href="${ bls4 }">[다음그룹]</a>
</c:if>
<c:if test="${ !((currentPage + 10) > endPage && (currentPage + 10) < maxPage) }">
   [다음그룹]&nbsp;
</c:if>
<!-- 맨끝 페이지로 이동 처리 -->
<c:if test="${ currentPage >= maxPage }">
   [맨끝]&nbsp;
</c:if>   
<c:if test="${ currentPage < maxPage }">
   <c:url var="bls5" value="/blist.do">
      <c:param name="page" value="${ maxPage }"/>
   </c:url>
   <a href="${ bls5 }">[맨끝]</a>
</c:if>
</div>
</c:if>


<c:if test="${ !empty action}">
<%-- 페이징 처리 
   [맨처음][이전] 숫자...........  [다음][맨끝]
--%>
<div style="text-align: center;">
<c:if test="${ currentPage <= 1}">
[맨처음]
</c:if>
<c:if test="${ currentPage > 1 }">
   <c:url var="bsearch1" value="${ action }">
      <c:if test="${ action ne 'bsearchDate.do'}">
         <c:param name="page" value="1" />
      </c:if>
      <c:if test="${ action eq 'bsearchDate.do'}">
         <c:param name="begin" value="${ begin }" />
         <c:param name="end" value="${ end }" />
      </c:if>
   </c:url>
   <a href="${ bsearch1 }">[맨처음]</a>
</c:if> &nbsp;
<%-- 이전 그룹이 있으면 링크설정, 이전 그룹 없으면 링크없음 --%>
<c:if test="${ (currentPage - 10) < startPage and (currentPage - 10) >= 1 }">
   <c:url var="bsearch2" value="/blist.do">
      <c:param name="page" value="${ startPage - 10 }"/>
   </c:url>
   <a href="${ bsearch2 }">[이전]</a>
</c:if>
<c:if test="${ !((currentPage - 10) < startPage and (currentPage - 10) >= 1) }">
[이전]
</c:if> &nbsp; 
<%-- 가운데 표시할 페이지 그룹 숫자 링크 설정 --%>
<c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
    <c:if test="${ p eq currentPage }">
      <font size="4" color="red">[${ p }]</font>
   </c:if>
   <c:if test="${ p ne currentPage }">
      <c:url var="bsearch3" value="/blist.do">
         <c:param name="page" value="${ p }" />
      </c:url>
      <a href="${ bsearch3 }">${ p }</a>
   </c:if>
</c:forEach> &nbsp;
<%-- 다음 그룹이 있으면 링크설정, 다음 그룹 없으면 링크없음 --%>
<c:if test="${ (currentPage + 10) > endPage && (currentPage + 10) < maxPage }">
   <c:url var="bsearch4" value="/blist.do">
      <c:param name="page" value="${ endPage + 10 }"/>
   </c:url>
   <a href="${ bsearch4 }">[다음그룹]</a>
</c:if>
<c:if test="${ !((currentPage + 10) > endPage && (currentPage + 10) < maxPage) }">
   [다음그룹]&nbsp;
</c:if>
<!-- 맨끝 페이지로 이동 처리 -->
<c:if test="${ currentPage >= maxPage }">
   [맨끝]&nbsp;
</c:if>   
<c:if test="${ currentPage < maxPage }">
   <c:url var="bsearch5" value="/blist.do">
      <c:param name="page" value="${ maxPage }"/>
   </c:url>
   <a href="${ bsearch5 }">[맨끝]</a>
</c:if>
</div>
</c:if>

<hr>

</body>
</html>
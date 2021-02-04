<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 응답온 페이지값 추출함 --%>
<c:set var="currentPage" value="${ requestScope.page  }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<<%-- 상대경로만 사용 가능함 --%>
	<c:import url="../common/menubar.jsp"/>
	<hr>
	<h2 align="center">${ requestScope.board.bid }번게시글 상세보기</h2>
	<br>
	<table align="center" cellpadding="10" cellspacing="0" border="1" width="500">
		<tr>
			<th>제 목</th><td>${ board.btitle }</td>
		</tr>
		<tr>
			<th>작성자</th><td>${ board.bwriter }</td>
		</tr>
		<tr>
			<th>내 용</th><td>${ board.bcontent }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<c:if test="${ !empty board.original_filename }">
			<c:url var="bfd" value="/bfdown.do">
				<c:param name="ofile" value="${ board.original_filename }" />
				<c:param name="rfile" value="${ board.rename_filename }"/>
			</c:url>
			<a href="${ bfd }">${ board.original_filename }</a>
			</c:if>
			</td></tr>
		<tr align="center" valign="middle"><th colspan="2">
		<%-- 로그인한 상태일 때 댓글달기 사용하게 함 --%>
		<c:if test="${ !empty sessionScope.loginUser}">
			<button>[댓글달기] </button> &nbsp; &nbsp; 
		</c:if>
		<%-- 로그인한 상태이면서, 본인 글일때만 보여지게 함  --%>
		<c:if test="${ !empty loginUser and loginUser.id eq board.bwriter }">
			<c:url var="buv" value="/bupview.do">
				<c:param name="bid" value="${ board.bid }" />
				<c:param name="page" value="${ currentPage }" />
			</c:url>
			<a href="${ buv }">[수정페이지로 이동]</a> &nbsp; &nbsp; 
			<c:url var="bdl" value="/bdelete.do">
				<c:param name="bid" value="${ board.bid }" />
			</c:url>
			<a href="${ bdl }">[글삭제]</a>&nbsp; &nbsp; 
		</c:if>
		<c:url var="bls" value="/blist.do">
			<c:param name="page" value="${ currentPage }"/>
		</c:url>
		<a href="${ bls }">[목록]</a>
		</th></tr>
	</table>

</body>
</html>
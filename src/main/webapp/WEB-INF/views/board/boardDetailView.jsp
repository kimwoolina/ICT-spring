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
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	hideReplyForm(); //뷰 페이지 처음 실행 시에는  댓글달기 폼이 안보이게 함
	
	//jquery ajax로 해당 게시글에 대한 댓글 조회 요청
	//해당 게시글의 번호를 전송함
	var bid = ${ board.bid };
	$.ajax({
		url: "${ pageContext.request.contextPath }/rlist.do",
		type: "post",
		data: { ref_bid: bid },
		dataType: "json",
		success: function(data){
			console.log("success : " + data);
			
			//object ==> string
			var jsonStr = JSON.stringify(data);
			//string ==> json
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){
				values += "<tr><td>" + json.list[i].rwriter
						+ "</td><td>" + json.list[i].r_create_date
						+ "</td></tr><tr><td colspan='2'>"
						+ decoudeURIComponent(json.list[i].rcontent).replace(/\+/gi, " ")
						+ "</td></tr>";
						
			} //for in
			
			$("#new_notice").html($("rlistTbl").html() + values);
		}, error: function(jqXHR, textstatus, errorthrown){
			console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
		}
	}); //reply ajax
	
	//jquery document ready
});

function showReplyForm(){
	$("#replyDiv").css("display", "block");	
}

function hideReplyForm(){
	$("#replyDiv").css("display", "none");
}
</script>
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
			<button onclick="showReplyForm();">댓글달기 </button> &nbsp; &nbsp; 
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
<%-- 댓글목록 표시 영역 --%>
<div id="rlistView" style="border: 1px gray;" ></div>
<table id="rlistTbl" cellspacing="0" cellpadding="5" border="1"></table>
</div>
<hr>
<%-- 댓글달기 폼 영역 --%>
<div id="replyDiv">
<form action="rinsert.do" method="post">
<input type="hidden" name="ref_bid" value="${ board.bid }">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr><th>작성자</th><td><input type="text" name="rwriter" readonly value="${ sessionScope.loginUser.id }"></td></tr>
<tr><th>내 용</th><td><textarea name="rcontent" rows="5" cols="50"></textarea></td></tr>
<tr><th colspan="2">
<input type="submit" value="댓글등록"> &nbsp;
<input type="reset"value="댓글취소" onclick="hideReplyForm(); return false;">
</th></tr>
</table>
</form>
</div>

</body>
</html>
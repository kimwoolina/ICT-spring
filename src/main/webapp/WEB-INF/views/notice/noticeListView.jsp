<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	showDiv();
	
	$("input[name=item]").on("change", function(){
		showDiv();
	});
});

function showDiv(){
	if($("input[name=item]").eq(0).is(":checked")){
		$("#titleDiv").css("display", "block");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "none");
	}
	
	if($("input[name=item]").eq(1).is(":checked")){
		$("#titleDiv").css("display", "none");
		$("#writerDiv").css("display", "block");
		$("#dateDiv").css("display", "none");
	}
	
	if($("input[name=item]").eq(2).is(":checked")){
		$("#titleDiv").css("display", "none");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "block");
	}
}
</script>
</head>
<body>
<%-- 상대경로로 대상 파일의 위치를 지정한 경우 --%>
<c:import url="../common/menubar.jsp"/>
<%-- JSTL에서는 절대경로 표기법이 달라짐 : /context-root명 ==> / 로 바뀐다.
            예 : "/first/common/menubar.jsp" ==> "/common/menubar.jsp" (/만 앞에 붙이면 절대경로 처리됨) --%>
<%-- <c:import url="/WEB-INF/views/common/menubar.jsp"/> --%>
<hr>
<h1 align="center">공지사항</h1>
<br>
<c:if test="${ !empty sessionScope.loginUser and sessionScope.loginUser.id eq 'admin123' }">
	<div style="align:center; padding-left:400px">
	<c:url var="nwf" value="/nwform.do" />
	<button onclick="javascript:location.href='${nwf}';">글쓰기</button>
	</div>
</c:if>
<br>
<%-- 검색 기능 --%>
<center>
<div>
	<h2>검색할 항목을 선택하시오.</h2>
	<input type="radio" name="item" value="title" checked> 제목  &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="writer"> 작성자  &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="date"> 날짜
</div>
<div id="titleDiv">
	<form action="nsearchTitle.do" method="post">
	<label>검색할 제목을 입력하시오 : <input type="search" name="keyword"></label>
	<input type="submit" value="검색">
	</form>
</div>
<div id="writerDiv">
	<form action="nsearchWriter.do" method="post">
	<label>검색할 작성자 아이디를 입력하시오 : <input type="search" name="keyword"></label>
	<input type="submit" value="검색">
	</form>
</div>
<div id="dateDiv">
	<form action="nsearchDate.do" method="post">
	<label>검색할 날짜를 입력하시오 : 
	<input type="date" name="begin"> ~ <input type="date" name="end"></label>
	<input type="submit" value="검색">
	</form>
</div>
</center>
<br>
<%-- 목록 출력 --%>
<div style="align:center; padding-left:400px">
	<c:url var="nlist" value="/nlist.do" />
	<button onclick="javascript:location.href='${ nlist }';">전체 목록 보기</button>
</div>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="1">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>첨부파일</th><th>날짜</th></tr>
<c:forEach items="${ requestScope.list}" var="n">
<tr>
	<td>${n.nid}</td>
	<c:url value="/ndetail.do" var="und">
		<c:param name="nid" value="${n.nid }" />
	</c:url>
	<td align="center"><a href="${und}">${n.ntitle}</a></td>
	<td align="center">${n.nwriter}</td>
	<td align="center">
		<c:if test="${!empty n.file_path }">◎</c:if>
		<c:if test="${empty n.file_path }">&nbsp;</c:if>
	</td>
	<td align="center">
		<fmt:formatDate value="${n.n_create_date}" pattern="yyyy-MM-dd"/>
	</td>
</tr>
</c:forEach>

</table>



</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">${ b.bId }게시글 수정하기</h1>
	<br>
	
	<!-- 첨부파일도 수정할려고 하니 Multipart/form-data encType 지정!! 꼭!!!!!!-->
	<form action="bupdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bId" value="${ b.bId }">
		<input type="hidden" name="originalFileName" value="${ b.originalFileName }">
		<input type="hidden" name="renameFileName" value="${ b.renameFileName }">
		
		<table align="center" id="tb">
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${ b.bTitle }"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" readonly name="bWriter" value="${ b.bWriter }"></td>
			</tr>		
			<tr>
				<td>내용</td>
				<td><textarea cols="50" rows="7" name="bContent">${ b.bContent }</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="reloadFile">
					<c:if test="${ !empty b.originalFileName }">
						<br>현재 업로드한 파일 : 
						<a href="${ contextPath }/resources/buploadFiles/${ b.renameFileName }" download="${ b.originalFileName }">${ b.originalFileName }</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기"> &nbsp;
					<a href="javascript:history.go(-1);">이전페이지로</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
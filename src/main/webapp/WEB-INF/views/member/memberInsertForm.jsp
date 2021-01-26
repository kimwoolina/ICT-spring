<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<h1 align="center">회원가입</h1>
	
	<div class="outer" align="center">
		<form action="minsert.do" method="post" id="joinForm">
			<table width="500" cellspacing="5">
				<tr>
					<td width="150">* 아이디</td>
					<td>
						<input type="text" name="id" id="userId" required>
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="name" required></input></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="pwd" required></td>
				</tr>
				<tr>
					<td>* 비밀번호확인</td>
					<td><input type="password" name="pwd2" required></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<input type="radio" name="gender" value="M">남
						<input type="radio" name="gender" value="F">여
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" min="20" max="100" name="age"></td>				
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="phone"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>
						<input type="text" name="post" class="postcodify_postcode5" size="6">
						<button type="button" id="postcodify_search_button">검색</button>
					</td>
				</tr>
				<tr>
					<td>도로명 주소</td>
					<td><input type="text" name="address1" class="postcodify_address"></td>
				</tr>
				<tr>
					<td>상세 주소</td>
					<td><input type="text" name="address2" class="postcodify_extra_info"></td>
				</tr>
				
				<!-- jQuery와 Postcodify를 로딩한다. -->
				<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
				<script>
					/*  검색 단추를 누르면 팝업 레이어가 열리도록 설정한다. */
					$(function(){
						$("#postcodify_search_button").postcodifyPopUp();
					});
				</script>
				
				<tr>
					<td colspan="2" align="center">
						<button onclick='return validate();'>가입하기</button>
						&nbsp;
						<input type="reset" value="취소하기">
					</td>
				</tr>
			</table>
		</form>
		<br>
		<br>
		<a href="home.do">시작 페이지로 이동</a>
	</div>
</body>
</html>
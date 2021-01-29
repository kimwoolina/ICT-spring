<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ict.spring.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">게시글 상세보기</h1>
	<br>
	
		
	<table align="center" id="tb">
		<tr align="center" valign="middle">
			<th colspan="2">${ b.bId }번 글 상세보기</th>
		</tr>
		<tr>
			<td>제목</td>
			<td>${ b.bTitle }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${ b.bWriter }</td>
		</tr>		
		<tr>
			<td>내용</td>
			<td>${ b.bContent }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>
				<c:if test="${ !empty b.originalFileName }">
					<a href="${ contextPath }/resources/buploadFiles/${ b.renameFileName }" download="${ b.originalFileName }">${ b.originalFileName }</a>
					
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				
				<c:url var="bupview" value="bupView.do">
					<c:param name="bId" value="${ b.bId }"/>
				</c:url>
				<c:url var="bdelete" value="bdelete.do">
					<c:param name="bId" value="${ b.bId }"/>
				</c:url>
				<c:url var="blist" value="blist.do">
					<c:param name="currentPage" value="${ currentPage }"/>
				</c:url>
				
				<c:if test="${ loginUser.id eq b.bWriter }">
					<a href="${ bupview }">수정하기</a> &nbsp;
					<a href="${ bdelete }">삭제하기</a> &nbsp;
				</c:if>
				
				
				<a href="${ blist }">목록으로</a>
			</td>
		</tr>
	</table>
	
	<br>
	<br>
	
	
	<table align="center" width="500" border="1" cellspacing="0">
		<tr>
			<td><textarea cols="55" rows="3" id="rContent"></textarea></td>
			<td>
				<button id="rSubmit">등록하기</button>
			</td>
		</tr>
	</table>
	
	<table align="center" width="500" border="1" cellspacing="0" id="rtb">
		<thead>
			<tr>
				<td colspan="3"><b id="rCount"></b></td>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	
	<script>
		$(function(){
			getReplyList();
			
			setInterval(function(){
				getReplyList();	// 타 회원이 댓글들을 작성했을 수 있으니 지속적으로 댓글 불러오기
			}, 10000);
			
			
			// 댓글 등록 ajax
			$("#rSubmit").on("click", function(){
				
				var rContent = $("#rContent").val();
				var refBid = ${ b.bId }; 
				var rWriter = "<%= ((Member)session.getAttribute("loginUser")).getId()%>";
				
				$.ajax({
					url:"addReply.do",
					data:{rContent:rContent, refBid:refBid, rWriter:rWriter},
					type:"post",
					success:function(data){
						if(data == "success"){
							getReplyList(); // 등록 성공시 다시 댓글리스트 불러오기
							
							$("#rContent").val("");
						}
					}
				});
			});
		});
		
		function getReplyList(){
			var bId = ${ b.bId };
			
			$.ajax({
				url:"rList.do",
				data:{bId:bId},
				dataType:"json",
				success:function(data){
					$tableBody = $("#rtb tbody");
					$tableBody.html("");
					
					var $tr;
					var $rWriter;
					var $rContent;
					var $rCreateDate;
					
					$("#rCount").text("댓글 ("+data.length+")");
					
					if(data.length > 0){
						for(var i in data){
							$tr = $("<tr>");
							$rWriter = $("<td width='100'>").text(data[i].rWriter);
							$rContent = $("<td>").text(data[i].rContent);
							$rCreateDate = $("<td width='100'>").text(data[i].rCreateDate);
							
							$tr.append($rWriter);
							$tr.append($rContent);
							$tr.append($rCreateDate);
							$tableBody.append($tr);
						}
					}else{
						$tr = $("<tr>");
						$rContent = $("<td colspan='3'>").text("등록된 댓글이 없습니다.");
						
						$tr.append($rContent);
						$tableBody.append($tr);
					}
				}
			});
		}
	</script>
	
	
</body>
</html>



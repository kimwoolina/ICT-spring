<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 상대경로로 대상 파일의 위치를 지정한 경우 --%>
<c:import url="../common/menubar.jsp"/>
<%-- JSTL에서는 절대경로 표기법이 달라짐 : /context-root명 ==> / 로 바뀐다.
            예 : "/first/common/menubar.jsp" ==> "/common/menubar.jsp" (/만 앞에 붙이면 절대경로 처리됨) --%>
<%-- <c:import url="/views/common/menubar.jsp"/> --%>




</body>
</html>
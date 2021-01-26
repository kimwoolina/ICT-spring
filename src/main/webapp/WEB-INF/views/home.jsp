<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<c:import url="common/menubar.jsp"/>
	
	<script>
		$(function(){
			var msg = "<%= request.getAttribute("msg") %>";
			if(msg != null){
				alert(msg);
			}
		});
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body onload="navi()">
	<c:if test="${isLogin == 'fail' }">
		<script>
			function navi(){
				alert("아이디 또는 비밀번호를 확인해주세요.");
				location.href = "login.do?url=${pre_url }";
			}
		</script>
	</c:if>
	<c:if test="${isLogin == 'success' }">
		<script>
			function navi(){
				location.href = "${pre_url }";
			}
		</script>
	</c:if>
</body>
</html>
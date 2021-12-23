<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>KakaoCook</title>
<link rel="stylesheet" href="../style/login.css">
<script src="https://kit.fontawesome.com/f05b6c991f.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="container">
		<header id="main_header">
			<div id="header_logo_section">
				<a href="../main.do"><img src="../images/Kakao_logo1.png" width="200px"></a>
			</div>
		</header>
		<section id="main_section">
			<div id="sign_in_section">
				<form action="loginOK.do" name="login_form" method="post" onsubmit="return checkInfo()">
					<input type="text" name="id" value="<c:if test="${isRemember == true}">${id}</c:if>" placeholder="아이디" onfocus="this.placeholder=''" onblur="this.placeholder='아이디'">
					<input type="password" name="pw" value="" placeholder="비밀번호" onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호'">
					<input type="submit" value="로그인">
					<div id="login_cookie_box">
						<input type="checkbox" id="login_cookie" name="rememberId" value="rememberId" <c:if test="${isRemember == true}">checked</c:if>>
						<label for="login_cookie">아이디 기억하기</label>
					</div>
					<input type="hidden" name="url" value="${url }">
				</form>
				<div id="sign_up_section">
					<a href="#">아이디 찾기</a><span>|</span>
					<a href="#">비밀번호 찾기</a><span>|</span>
					<a href="member_register.do">회원가입</a>
				</div>
			</div>
		</section>
		<footer id="main_footer">
			<div id="footer_list">
				<div class="social">
					<a href="#"><i class="fab fa-kaggle"></i></a>
					<a href="#"><i class="fab fa-facebook-f"></i></a>
					<a href="#"><i class="fab fa-twitter"></i></a>
					<a href="#"><i class="fab fa-youtube"></i></a>
					<p id="copyright">© Kakao Corp. All rights reserved.</p>
				</div>
			</div>
		</footer>
	</div>
	<script>
		function checkInfo() {
			let loginform = document.login_form;
	
			if (loginform.id.value == null || loginform.id.value == "") {
				alert("아이디를 입력하세요.");
				loginform.id.focus();
				return false;
			}
			if (loginform.pw.value == null || loginform.pw.value == "") {
				alert("비밀번호를 입력하세요.");
				loginform.pw.focus();
				return false;
			}
		}
	</script>
</body>
</html>
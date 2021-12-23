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
<link rel="stylesheet" href="../style/grocery_insert.css">
<script src="https://kit.fontawesome.com/f05b6c991f.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="container">
		<header id="main_header">
			<div id="header_logo_section">
				<a href="../main.do"><img src="../images/Kakao_logo1.png" width="150px"></a>
			</div>
			<div id="header_menu_section">
				<ul>
					<li><a href="../main.do">Home</a></li>
					<li><a href="recipe_main.do">레시피</a></li>
					<li><a href="freezer_main.do">나의 냉장고</a></li>
					<c:if test="${checkedMember.granted == 'Y' }">
						<li><a href="admin_main.do">관리자</a></li>
					</c:if>
				</ul>
			</div> <!-- header_menu_section -->
			<div id="header_login_section">
				<c:if test="${checkedMember == null }">
					<a href="login.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="logout.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="page1_contents">
					<div id="grocery_insert_section">
						<div id="grocery_insert_section_header">
							<span><b>식재료</b> 수정하기</span>
						</div>
						<form action="grocery_modifyOK.do" name="insert_form" method="post" onsubmit="return checkInfo()">
							<table>
								<tr>
									<td>재료 이름</td>
									<td><input type="text" name="name" value="${gDto.name }"></td>
								</tr>
								<tr>
									<td>분류</td>
									<td>
										<select name="type">
											<option value="meat" <c:if test="${gDto.type == 'meat'}">selected</c:if>>육류</option>
											<option value="seafood" <c:if test="${gDto.type == 'seafood'}">selected</c:if>>해물류</option>
											<option value="vegetable" <c:if test="${gDto.type == 'vegetable'}">selected</c:if>>채소류</option>
											<option value="processed" <c:if test="${gDto.type == 'processed'}">selected</c:if>>가공식품류</option>
											<option value="grain" <c:if test="${gDto.type == 'grain'}">selected</c:if>>곡류</option>
											<option value="etc" <c:if test="${gDto.type == 'etc'}">selected</c:if>>기타</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>패키지량</td>
									<td>
										<input type="text" name="package_amount" value="${gDto.package_amount }">
										<select name="package_unit">
											<option value="개" <c:if test="${gDto.package_unit == '개'}">selected</c:if>>개</option>
											<option value="박스" <c:if test="${gDto.package_unit == '박스'}">selected</c:if>>박스</option>
											<option value="mg" <c:if test="${gDto.package_unit == 'mg'}">selected</c:if>>mg</option>
											<option value="g" <c:if test="${gDto.package_unit == 'g'}">selected</c:if>>g</option>
											<option value="kg" <c:if test="${gDto.package_unit == 'kg'}">selected</c:if>>kg</option>
											<option value="ml" <c:if test="${gDto.package_unit == 'ml'}">selected</c:if>>ml</option>
											<option value="L" <c:if test="${gDto.package_unit == 'L'}">selected</c:if>>L</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>총수량</td>
									<td><input type="text" name="total_amount" value="${gDto.total_amount }"></td>
								</tr>
								<tr>
									<td>유통기한</td>
									<td><input type="date" name="shelf_life" value="${gDto.shelf_life }"></td>
								</tr>
								<tr>
									<td>보관방법</td>
									<td><textarea name="storage_method" style="resize:none">${gDto.storage_method }</textarea></td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="button_section">
											<input type="submit" value="수정" id="submit_button">
											<input type="button" value="취소" id="cancel_button" onclick="window.location='freezer_main.do'">
										</div>
									</td>
								</tr>
							</table>
							<input type="hidden" name="no" value="${gDto.no }">
						</form>
					</div>	<!-- grocery_insert_section -->
				</div>	<!-- page1_contents -->
			</article>
		</section>
		<footer id="main_footer">
			<div id="footer_list">
				<div class="footer_list_contents">
					<p>이용약관</p>
					<ul>
						<li><a href="#">contents 1</a></li>
						<li><a href="#">contents 2</a></li>
						<li><a href="#">contents 3</a></li>
					</ul>
				</div>
				<div class="footer_list_contents">
					<p>위치기반서비스이용약관</p>
					<ul>
						<li><a href="#">contents 1</a></li>
						<li><a href="#">contents 2</a></li>
						<li><a href="#">contents 3</a></li>
					</ul>
				</div>
				<div class="footer_list_contents">
					<p>개인정보처리방침</p>
					<ul>
						<li><a href="#">contents 1</a></li>
						<li><a href="#">contents 2</a></li>
						<li><a href="#">contents 3</a></li>
					</ul>
				</div>
				<div></div> <!-- flex 3등분 용도 -->
				<div class="social">
					<a href="#"><i class="fab fa-kaggle"></i></a>
					<a href="#"><i class="fab fa-facebook-f"></i></a>
					<a href="#"><i class="fab fa-twitter"></i></a>
					<a href="#"><i class="fab fa-youtube"></i></a>
					<p id="copyright">© Kakao Corp. All rights reserved.</p>
				</div>
				<div></div> <!-- flex 3등분 용도 -->
			</div><!-- footer_list -->
		</footer>
		<a href="#container" id="top_button"><i class="fas fa-arrow-circle-up"></i></a>
	</div>
	<script>
		function checkInfo() {		
			let form = document.insert_form;
			
			if(!form.name.value){
		        alert("재료 이름을 입력해주세요.");
		        return false;
		    }
		    if(!form.type.value){
		        alert("종류를 선택해주세요.");
		        return false;
		    }
		    if(!form.amount.value){
		        alert("수량을 입력해주세요.");
		        return false;
		    }
		    if(!form.shelf_life.value){
		        alert("유통기한을 입력해주세요.");
		        return false;
		    }
		    if(isNaN(form.amount.value)){
		    	alert("숫자만 입력해주세요.");
				return false;
			}
		}
	</script>
	<script>
		$('form input').keydown(function(e) {
			if (e.keyCode == 13) {
				return false;
			}
		});
	</script>
</body>
</html>
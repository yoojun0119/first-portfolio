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
<link rel="stylesheet" href="../style/member_modify.css">
<script src="https://kit.fontawesome.com/f05b6c991f.js" crossorigin="anonymous"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../script/address.js"></script>
</head>
<body>
	<div id="container">
		<header id="main_header">
			<div id="header_logo_section">
				<a href="../main.do"><img src="../images/Kakao_logo1.png" width="200px"></a>
			</div>
		</header>
		<section id="main_section">
			<div id="sign_up_section">
				<form action="member_modifyOK.do" method="post" name="modify_form" onsubmit="return checkModInfo()">
					<table>
						<tr><td><label for="id">&nbsp;아이디</label></td></tr>
						<tr>
							<td>
								<input type="text" name="id" id="id" value="${checkedMember.id }" readonly>
							</td>
						</tr>
						<tr><td><label for="pw">&nbsp;비빌번호</label></td></tr>
						<tr>
							<td><input type="password" name="pw" id="pw"></td>
						</tr>
						<tr><td><label for="pwChk">&nbsp;비밀번호 확인</label><span id="checkPw"></span></td></tr>
						<tr>
							<td><input type="password" name="pwChk" id="pwChk"></td>
						</tr>
						<tr><td><label for="name">&nbsp;이름</label></td></tr>
						<tr>
							<td><input type="text" name="name" id="name" value="${checkedMember.name }"></td>
						</tr>
						<tr><td>&nbsp;생년월일</td></tr>
						<tr>
							<td>
								<select name="birthYear">
									<c:forEach var="i" begin="0" end="${2021-1921 }">
									    <c:set var="year" value="${2021-i }" />
									    <option value="${year }" <c:if test="${checkedMember.birthYear == year }">selected</c:if>>${year }</option>
									</c:forEach>
								</select>
								<select name="birthMonth">
									<c:forEach var="i" begin="1" end="12">
										<option value="${i }" <c:if test="${checkedMember.birthMonth == i }">selected</c:if>>${i }</option>
									</c:forEach>
								</select>
								<select name="birthDate">
									<c:forEach var="i" begin="1" end="31">
										<option value="${i }" <c:if test="${checkedMember.birthDate == i }">selected</c:if>>${i }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr><td>&nbsp;성별구분</td></tr>
						<tr>
							<td>
								<select id="sex" name="sex">
				                	<option disabled selected>성별</option>
				                	<option value="male" <c:if test="${checkedMember.sex == 'male' }">selected</c:if>>남자</option>
				                	<option value="female" <c:if test="${checkedMember.sex == 'female' }">selected</c:if>>여자</option>
				                </select>
							</td>
						</tr>
						<tr><td>&nbsp;주소</td></tr>
						<tr>
							<td>
								<div id="address">
									<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
									<input type="text" id="sample4_postcode" placeholder="우편번호" name="postcode" value="${checkedMember.postcode }" readonly><br>
									<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="roadAddress" value="${checkedMember.roadAddress }">
									<input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="jibunAddress" value="${checkedMember.jibunAddress }">
									<span id="guide" style="color: #999; display: none"></span>
									<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detailAddress" value="${checkedMember.detailAddress }">
									<input type="text" id="sample4_extraAddress" placeholder="참고항목" name="extraAddress" value="${checkedMember.extraAddress }">
								</div>
							</td>
						</tr>
						<tr><td><label for="phoneNumber">&nbsp;전화번호</label></td></tr>
						<tr>
							<td>
								<input type="text" name="phoneNumber" id="phoneNumber" value="${checkedMember.phoneNumber }" placeholder="-를 제외하고 입력해주세요." onfocus="this.placeholder=''" onblur="this.placeholder='-를 제외하고 입력해주세요.'">
								<span id="checkNum"></span>
							</td>
						</tr>
						<tr><td><label for="email">&nbsp;이메일</label></td></tr>
						<tr>
							<td><input type="email" name="email" id="email" value="${checkedMember.email }"></td>
						</tr>

						<tr>
							<td colspan="2">
								<input type="submit" value="정보수정" id="submit_button">
								<input type="button" value="취소" id="cancel_button" onclick="window.location='../main.do'">
							</td>
						</tr>
					</table>
				</form>
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
		function checkModInfo() {		
			let form = document.modify_form;
			
		    if(!form.pw.value){
		        alert("비밀번호를 입력해주세요.");
		        return false;
		    }
		    if(form.pw.value != form.pwChk.value){
		        alert("비밀번호가 서로 일치하지 않습니다.");
		        document.getElementById("checkPw").innerHTML = "비밀번호가 서로 일치하지 않습니다.";
				document.getElementById("checkPw").style.color = "red";
				document.getElementById("pwChk").style.border = "1px solid red";
		        return false;
		    } else {
				document.getElementById("checkPw").innerHTML = "";
				document.getElementById("pwChk").style.border = "1px solid lightgray";
			}
		    if(!form.name.value){
		        alert("이름을 입력해주세요.");
		        return false;
		    }
		    if(!form.birthYear.value && !form.birthMonth.value && !form.birthDate.value){
		        alert("생년월일을 입력해주세요.");
		        return false;
		    }
		    if(!form.sex.value){
		        alert("성별을 입력해주세요.");
		        return false;
		    }
		    if(!form.postcode.value){
		        alert("우편번호를 입력해주세요.");
		        return false;
		    }
		    if(!form.roadAddress.value){
		        alert("도로명주소를 입력해주세요.");
		        return false;
		    }
		    if(!form.jibunAddress.value){
		        alert("지번주소를 입력해주세요.");
		        return false;
		    }
		    if(!form.detailAddress.value){
		        alert("상세주소를 입력해주세요.");
		        return false;
		    }
		    if(!form.phoneNumber.value){
		        alert("전화번호를 입력해주세요.");
		        return false;
		    }
		    if(isNaN(form.phoneNumber.value)){
		    	alert("전화번호를 확인해주세요.");
				document.getElementById("checkNum").innerHTML = "전화번호를 확인해주세요.";
				document.getElementById("checkNum").style.color = "red";
				document.getElementById("phoneNumber").style.border = "1px solid red";
				return false;
			} else {
				document.getElementById("checkNum").innerHTML = "";
				document.getElementById("phoneNumber").style.border = "1px solid lightgray";
			}
		    if(!form.email.value){
		        alert("이메일을 입력해주세요.");
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
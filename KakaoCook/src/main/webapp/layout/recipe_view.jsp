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
<link rel="stylesheet" href="../style/recipe_view.css">
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
					<a href="login.do?url=recipe_view.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="logout.do?url=recipe_view.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="page1_contents">
					<div id="recipe_insert_section">
						<button id="backwardBtn" onclick="window.location='recipe_main.do'"><i class="fas fa-angle-double-left"></i> 전체목록</button>
						<form action="recipe_modify.do" method="post">
							<table>
								<tr>
									<td colspan="2" class="dif_td" style="border:none;">
										<div id="recipe_insert_section_header">
											<span><b>"</b> ${rDto.title } <b>"</b></span>
										</div>
										<c:if test="${checkedMember != null }">
											<div id="recipe_insert_section_bookmark">
												<div id="grade_point_section">
														<c:choose>
															<c:when test="${assessGradeRecipe_no != null }">
																<c:forEach var="i" begin="1" end="${assessGrade_num }">
																	<img id="star${i}" src="../images/checked_star_btn.png">
																</c:forEach>
																<c:forEach var="j" begin="${assessGrade_num+1 }" end="5">
																	<img id="star${j}" src="../images/unchecked_star_btn.png">
																</c:forEach>
															</c:when>
															<c:otherwise>
																<img id="star1" onmouseover="showStars(1)" onmouseout="removeStars(1)" onclick="selectGrade(1, ${rDto.no}, '${checkedMember.id}')" src="../images/unchecked_star_btn.png">
																<img id="star2" onmouseover="showStars(2)" onmouseout="removeStars(2)" onclick="selectGrade(2, ${rDto.no}, '${checkedMember.id}')" src="../images/unchecked_star_btn.png">
																<img id="star3" onmouseover="showStars(3)" onmouseout="removeStars(3)" onclick="selectGrade(3, ${rDto.no}, '${checkedMember.id}')" src="../images/unchecked_star_btn.png">
																<img id="star4" onmouseover="showStars(4)" onmouseout="removeStars(4)" onclick="selectGrade(4, ${rDto.no}, '${checkedMember.id}')" src="../images/unchecked_star_btn.png">
																<img id="star5" onmouseover="showStars(5)" onmouseout="removeStars(5)" onclick="selectGrade(5, ${rDto.no}, '${checkedMember.id}')" src="../images/unchecked_star_btn.png">
															</c:otherwise>
														</c:choose>
												</div>	<!-- grade_point_section -->
												<div id="bookmark_section">
													<c:choose>
														<c:when test="${bookmarked_flag}">
															<a href="recipe_bookmarkDel.do?id=${checkedMember.id}&no=${rDto.no}&url=recipe_main.do"><img src="../images/checked_heart_btn.png"></a>
														</c:when>
														<c:otherwise>
															<a href="recipe_bookmark.do?id=${checkedMember.id}&no=${rDto.no}" onmouseover="showHeart()" onmouseout="removeHeart()">
																<img src="../images/unchecked_heart_btn.png" id="heartBtn">
															</a>
														</c:otherwise>
													</c:choose>
												</div>	<!-- bookmark_section -->
											</div>	<!-- recipe_insert_section_bookmark -->
										</c:if>
									</td>
								</tr>
								<tr>
									<td>요리 이름</td>
									<td>${rDto.name }</td>
								</tr>
								<tr>
									<td>분류</td>
									<td>
										<select name="recipe_type">
											<option <c:if test="${rDto.recipe_type == 'kor_foods'}">selected</c:if> <c:if test="${rDto.recipe_type != 'kor_foods'}">disabled</c:if>>한식</option>
											<option <c:if test="${rDto.recipe_type == 'cha_foods'}">selected</c:if> <c:if test="${rDto.recipe_type != 'cha_foods'}">disabled</c:if>>중식</option>
											<option <c:if test="${rDto.recipe_type == 'jap_foods'}">selected</c:if> <c:if test="${rDto.recipe_type != 'jap_foods'}">disabled</c:if>>일식</option>
											<option <c:if test="${rDto.recipe_type == 'etc_foods'}">selected</c:if> <c:if test="${rDto.recipe_type != 'etc_foods'}">disabled</c:if>>양식</option>
										</select>
										<select name="food_type">
											<option <c:if test="${rDto.food_type == 'main_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'main_dish'}">disabled</c:if>>메인요리</option>
											<option <c:if test="${rDto.food_type == 'soup_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'soup_dish'}">disabled</c:if>>국물</option>
											<option <c:if test="${rDto.food_type == 'noodle_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'noodle_dish'}">disabled</c:if>>면</option>
											<option <c:if test="${rDto.food_type == 'side_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'side_dish'}">disabled</c:if>>반찬</option>
											<option <c:if test="${rDto.food_type == 'salad_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'salad_dish'}">disabled</c:if>>샐러드</option>
											<option <c:if test="${rDto.food_type == 'dessert_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'dessert_dish'}">disabled</c:if>>디저트</option>
											<option <c:if test="${rDto.food_type == 'etc_food_dish'}">selected</c:if> <c:if test="${rDto.food_type != 'etc_food_dish'}">disabled</c:if>>기타</option>
										</select>
										<select name="ingredient_type">
											<option <c:if test="${rDto.ingredient_type == 'meat_dish'}">selected</c:if> <c:if test="${rDto.ingredient_type != 'meat_dish'}">disabled</c:if>>육류</option>
											<option <c:if test="${rDto.ingredient_type == 'seafood_dish'}">selected</c:if> <c:if test="${rDto.ingredient_type != 'seafood_dish'}">disabled</c:if>>해물류</option>
											<option <c:if test="${rDto.ingredient_type == 'vegetable_dish'}">selected</c:if> <c:if test="${rDto.ingredient_type != 'vegetable_dish'}">disabled</c:if>>채소류</option>
											<option <c:if test="${rDto.ingredient_type == 'processed_dish'}">selected</c:if> <c:if test="${rDto.ingredient_type != 'processed_dish'}">disabled</c:if>>가공식품류</option>
											<option <c:if test="${rDto.ingredient_type == 'grain_dish'}">selected</c:if> <c:if test="${rDto.ingredient_type != 'grain_dish'}">disabled</c:if>>곡류</option>
											<option <c:if test="${rDto.ingredient_type == 'etc_ingredient_dish'}">selected</c:if> <c:if test="${rDto.ingredient_type != 'etc_ingredient_dish'}">disabled</c:if>>기타</option>
										</select>
										<select name="method_type">
											<option <c:if test="${rDto.method_type == 'roast_dish'}">selected</c:if> <c:if test="${rDto.method_type != 'roast_dish'}">disabled</c:if>>볶음</option>
											<option <c:if test="${rDto.method_type == 'grill_dish'}">selected</c:if> <c:if test="${rDto.method_type != 'grill_dish'}">disabled</c:if>>구이</option>
											<option <c:if test="${rDto.method_type == 'boil_dish'}">selected</c:if> <c:if test="${rDto.method_type != 'boil_dish'}">disabled</c:if>>끓이기</option>
											<option <c:if test="${rDto.method_type == 'boil_down_dish'}">selected</c:if> <c:if test="${rDto.method_type != 'boil_down_dish'}">disabled</c:if>>조림</option>
											<option <c:if test="${rDto.method_type == 'steam_dish'}">selected</c:if> <c:if test="${rDto.method_type != 'steam_dish'}">disabled</c:if>>찜</option>
											<option <c:if test="${rDto.method_type == 'etc_method_dish'}">selected</c:if> <c:if test="${rDto.method_type != 'etc_method_dish'}">disabled</c:if>>기타</option>
										</select>
									</td>
								</tr>
								<tr><td class="line1"><hr></td><td class="line2"><hr></td></tr>
								<tr>
									<td class="dif_td"><span>음식 사진</span></td>
									<td>
										<div class="preview thumbnail_wrap"><img src="${rDto.recipe_thumbnail_image }"></div>
									</td>
								</tr>
								<tr><td class="line1"><hr></td><td class="line2"><hr></td></tr>
								<tr>
									<td class="dif_td"><span>재료</span></td>
									<td id="ingrdient_input_section">
										<input type="button" id="getGroBtn" value="재료 구매하기" onclick="window.location='freezer_main.do'">
										<ul>
											<c:forEach var="i" begin="0" end="${numOfIngredient-1 }">
												<li>
													<div class="ingredient_wrap">
														<label for="ingredient_name${i+1 }">재료이름${i+1 }</label><input type="text" id="ingredient_name${i+1 }" value='<c:forEach var="list1" items="${ingredient_name_list[i] }">${list1 }</c:forEach>' readonly>
														<label for="ingredient_amount${i+1 }">재료량${i+1 }</label><input type="text" id="ingredient_amount${i+1 }" value='<c:forEach var="list2" items="${ingredient_amount_list[i] }">${list2 }</c:forEach>' readonly>
													</div>
												</li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								<tr><td class="line1"><hr></td><td class="line2"><hr></td></tr>
								<tr>
									<td class="dif_td"><span>레시피 순서</span></td>
									<td>
										<ul>
											<c:forEach var="i" begin="0" end="${numOfRecipeStep-1 }">
												<li>
													<div class="dif_td">
														<p>${i+1 }</p>
														<div class="preview"><img src="<c:forEach var="list2" items="${imgPath_list[i] }">${list2 }</c:forEach>"></div>
														<textarea name="recipe_description" style="resize:none" readonly><c:forEach var="list1" items="${recipe_description_list[i] }">${list1 }</c:forEach></textarea>
													</div>
												</li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="button_section">
											<c:if test="${checkedMember.id == rDto.id }">
												<input type="submit" value="수정" id="submit_button">
												<input type="button" value="삭제" id="delete_button" onclick="window.location='recipe_deleteOK.do?no=${rDto.no }'">		
											</c:if>
											<input type="button" value="목록" id="cancel_button" onclick="window.history.back(1)">
										</div>
									</td>
								</tr>
							</table>
							<input type="hidden" name="no" value="${rDto.no }">
						</form>
					</div>
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
		function showStars(numOfStars){
			let star;
			let starImg;
			
			for(let i=1;i<=numOfStars;i++){
				star = 'star'+i;
				starImg = document.getElementById(star);
				starImg.src = "../images/checked_star_btn.png";
			}
		}
		function removeStars(numOfStars){
			let star;
			let starImg;
			
			for(let i=1;i<=numOfStars;i++){
				star = 'star'+i;
				starImg = document.getElementById(star);
				starImg.src = "../images/unchecked_star_btn.png";
			}
		}
		function showHeart(){
			let star;
			let starImg;
			
			starImg = document.getElementById('heartBtn');
			starImg.src = "../images/checked_heart_btn.png";
		}
		function removeHeart(){
			let star;
			let starImg;
			
			starImg = document.getElementById('heartBtn');
			starImg.src = "../images/unchecked_heart_btn.png";
		}
		function selectGrade(gradeNum, no, id){
			let isConfirmed = confirm('별점 '+ gradeNum +'점을 주시겠습니까?');
			
			if(isConfirmed){
				window.location="recipe_gradeOK.do?gradeNum="+gradeNum+"&no="+no+"&id="+id;
			} else {
				return;
			}
		}
	</script>
	<script>
		function useRecipe(){
			let isConfirmed = confirm("냉장고 재료를 소진하시겠습니까? 부족한 재료는 모두 소진합니다.");
			
			if(isConfirmed){
		        return true;
		    } else {
		    	return false;
		    }
		}
	</script>
</body>
</html>
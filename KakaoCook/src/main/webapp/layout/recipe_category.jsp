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
<link rel="stylesheet" href="../style/recipe_main.css">
<script src="https://kit.fontawesome.com/f05b6c991f.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="container">
		<header id="main_header">
			<div id="header_logo_section">
				<a href="../main.do"><img src="../images/Kakao_logo1.png"
					width="150px"></a>
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
			</div>	<!-- header_menu_section -->
			<div id="header_login_section">
				<c:if test="${checkedMember == null }">
					<a href="login.do?url=recipe_category.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="logout.do?url=recipe_category.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="page1_contents">
					<div id="search_wrap">
						<form action="recipe_search.do" method="post" id="search_form">
							<input type="text" id="search_bar" name="search_contents" placeholder="레시피 검색" onfocus="this.placeholder=''" onblur="this.placeholder='레시피 검색'">
							<a href="#" id="search_btn" onclick="return searchBtn()">
								<i class="fas fa-search"></i>
							</a>
						</form>
					</div>
					<form action="recipe_category.do" method="post">
						<table>
							<tr>
								<td>대분류</td>
								<td>
									<input type="radio" name="recipe_type" value="all_foods" id="all_foods" <c:if test="${recipe_type == 'foods'}">checked</c:if> onclick="submit()"><label for="all_foods">전체</label>
									<input type="radio" name="recipe_type" value="kor_foods" id="kor_foods" <c:if test="${recipe_type == 'kor_foods'}">checked</c:if> onclick="submit()"><label for="kor_foods">한식</label>
									<input type="radio" name="recipe_type" value="cha_foods" id="cha_foods" <c:if test="${recipe_type == 'cha_foods'}">checked</c:if> onclick="submit()"><label for="cha_foods">중식</label>
									<input type="radio" name="recipe_type" value="jap_foods" id="jap_foods" <c:if test="${recipe_type == 'jap_foods'}">checked</c:if> onclick="submit()"><label for="jap_foods">일식</label>
									<input type="radio" name="recipe_type" value="etc_foods" id="etc_foods" <c:if test="${recipe_type == 'etc_foods'}">checked</c:if> onclick="submit()"><label for="etc_foods">양식</label>
								</td>
							</tr>
							<tr>
								<td>종류별</td>
								<td>
									<input type="radio" name="food_type" value="all_food_dish" id="all_food_dish" <c:if test="${food_type == 'dish'}">checked</c:if> onclick="submit()"><label for="all_food_dish">전체</label>
									<input type="radio" name="food_type" value="main_dish" id="main_dish" <c:if test="${food_type == 'main_dish'}">checked</c:if> onclick="submit()"><label for="main_dish">메인요리</label>
									<input type="radio" name="food_type" value="soup_dish" id="soup_dish" <c:if test="${food_type == 'soup_dish'}">checked</c:if> onclick="submit()"><label for="soup_dish">국물</label>
									<input type="radio" name="food_type" value="noodle_dish" id="noodle_dish" <c:if test="${food_type == 'noodle_dish'}">checked</c:if> onclick="submit()"><label for="noodle_dish">면</label>
									<input type="radio" name="food_type" value="side_dish" id="side_dish" <c:if test="${food_type == 'side_dish'}">checked</c:if> onclick="submit()"><label for="side_dish">반찬</label>
									<input type="radio" name="food_type" value="salad_dish" id="salad_dish" <c:if test="${food_type == 'salad_dish'}">checked</c:if> onclick="submit()"><label for="salad_dish">샐러드</label>
									<input type="radio" name="food_type" value="dessert_dish" id="dessert_dish" <c:if test="${food_type == 'dessert_dish'}">checked</c:if> onclick="submit()"><label for="dessert_dish">디저트</label>
									<input type="radio" name="food_type" value="etc_food_dish" id="etc_food_dish" <c:if test="${food_type == 'etc_food_dish'}">checked</c:if> onclick="submit()"><label for="etc_food_dish">기타</label>
								</td>
							</tr>
							<tr>
								<td>재료별</td>
								<td>
									<input type="radio" name="ingredient_type" value="all_ingredient_dish" id="all_ingredient_dish" <c:if test="${ingredient_type == 'dish'}">checked</c:if> onclick="submit()"><label for="all_ingredient_dish">전체</label>
									<input type="radio" name="ingredient_type" value="meat_dish" id="meat_dish" <c:if test="${ingredient_type == 'meat_dish'}">checked</c:if> onclick="submit()"><label for="meat_dish">육류</label>
									<input type="radio" name="ingredient_type" value="seafood_dish" id="seafood_dish" <c:if test="${ingredient_type == 'seafood_dish'}">checked</c:if> onclick="submit()"><label for="seafood_dish">해물류</label>
									<input type="radio" name="ingredient_type" value="vegetable_dish" id="vegetable_dish" <c:if test="${ingredient_type == 'vegetable_dish'}">checked</c:if> onclick="submit()"><label for="vegetable_dish">채소류</label>
									<input type="radio" name="ingredient_type" value="processed_dish" id="processed_dish" <c:if test="${ingredient_type == 'processed_dish'}">checked</c:if> onclick="submit()"><label for="processed_dish">가공식품류</label>
									<input type="radio" name="ingredient_type" value="grain_dish" id="grain_dish" <c:if test="${ingredient_type == 'grain_dish'}">checked</c:if> onclick="submit()"><label for="grain_dish">곡류</label>
									<input type="radio" name="ingredient_type" value="etc_ingredient_dish" id="etc_ingredient_dish" <c:if test="${ingredient_type == 'etc_ingredient_dish'}">checked</c:if> onclick="submit()"><label for="etc_ingredient_dish">기타</label>
								</td>
							</tr>
							<tr>
								<td>방법별</td>
								<td>
									<input type="radio" name="method_type" value="all_method_dish" id="all_method_dish" <c:if test="${method_type == 'dish'}">checked</c:if> onclick="submit()"><label for="all_method_dish">전체</label>
									<input type="radio" name="method_type" value="roast_dish" id="roast_dish" <c:if test="${method_type == 'roast_dish'}">checked</c:if> onclick="submit()"><label for="roast_dish">볶음</label>
									<input type="radio" name="method_type" value="grill_dish" id="grill_dish" <c:if test="${method_type == 'grill_dish'}">checked</c:if> onclick="submit()"><label for="grill_dish">구이</label>
									<input type="radio" name="method_type" value="boil_dish" id="boil_dish" <c:if test="${method_type == 'boil_dish'}">checked</c:if> onclick="submit()"><label for="boil_dish">끓이기</label>
									<input type="radio" name="method_type" value="boil_down_dish" id="boil_down_dish" <c:if test="${method_type == 'boil_down_dish'}">checked</c:if> onclick="submit()"><label for="boil_down_dish">조림</label>
									<input type="radio" name="method_type" value="steam_dish" id="steam_dish" <c:if test="${method_type == 'steam_dish'}">checked</c:if> onclick="submit()"><label for="steam_dish">찜</label>
									<input type="radio" name="method_type" value="etc_method_dish" id="etc_method_dish" <c:if test="${method_type == 'etc_method_dish'}">checked</c:if> onclick="submit()"><label for="etc_method_dish">기타</label>
								</td>
							</tr>
						</table>
					</form>
				</div>	<!-- page1_contents -->
				<div id="page2_contents">
					<div id="recipe_header">
						<span>
							<b>
								<c:if test="${recipe_type == 'foods' && food_type == 'dish' && ingredient_type == 'dish' && method_type == 'dish'}">전체</c:if>
								<c:if test="${recipe_type == 'kor_foods'}">한식 </c:if>
								<c:if test="${recipe_type == 'cha_foods'}">중식 </c:if>
								<c:if test="${recipe_type == 'jap_foods'}">일식 </c:if>
								<c:if test="${recipe_type == 'etc_foods'}">양식 </c:if>
								
								<c:if test="${food_type == 'main_dish'}">메인요리 </c:if>
								<c:if test="${food_type == 'soup_dish'}">국물 </c:if>
								<c:if test="${food_type == 'noodle_dish'}">면 </c:if>
								<c:if test="${food_type == 'side_dish'}">반찬 </c:if>
								<c:if test="${food_type == 'salad_dish'}">샐러드 </c:if>
								<c:if test="${food_type == 'dessert_dish'}">디저트 </c:if>

								<c:if test="${ingredient_type == 'meat_dish'}">육류 </c:if>
								<c:if test="${ingredient_type == 'seafood_dish'}">해물류 </c:if>
								<c:if test="${ingredient_type == 'vegetable_dish'}">채소류 </c:if>
								<c:if test="${ingredient_type == 'processed_dish'}">가공식품류 </c:if>
								<c:if test="${ingredient_type == 'grain_dish'}">곡류 </c:if>

								<c:if test="${method_type == 'roast_dish'}">볶음 </c:if>
								<c:if test="${method_type == 'grill_dish'}">구이 </c:if>
								<c:if test="${method_type == 'boil_dish'}">끓이기 </c:if>
								<c:if test="${method_type == 'boil_down_dish'}">조림 </c:if>
								<c:if test="${method_type == 'steam_dish'}">찜 </c:if>
								
								<c:if test="${food_type == 'etc_food_dish' || ingredient_type == 'etc_ingredient_dish' || method_type == 'etc_method_dish'}">기타 </c:if>
							</b>레시피
						</span>
						<input type="button" value="전체목록" onclick="window.location='recipe_main.do'">
						<c:if test="${checkedMember.granted == 'Y' }">
							<input type="button" value="레시피 등록" onclick="window.location='recipe_insert.do'">
						</c:if>
					</div>
					<div id="recipe_list">
						<c:if test="${nonList != null}">
							<div id="nonList" style="height: 300px; text-align: center; line-height: 300px;">해당 조건을 만족하는 레시피가 없습니다.</div>
						</c:if>
						<ul>
							<c:forEach var="rDto" items="${list }">
								<li class="">
									<div class="recipe_image_section">
										<a href="recipe_view.do?no=${rDto.no }&id=${checkedMember.id }" class=""><img src="${rDto.recipe_thumbnail_image }"></a>
									</div>
									<div class="recipe_caption">
										<div class="recipe_caption_title">${rDto.title }</div>
										<div class="recipe_caption_info">
											<c:forEach var="j" begin="1" end="5">
												<span class="recipe_caption_info_star">
													<img src="https://recipe1.ezmember.co.kr/img/mobile/icon_star2_on.png">
												</span>
											</c:forEach>
											<span class="recipe_caption_info_rCnt">조회수 ${rDto.rCnt }</span>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div id="paging_section">
						<c:set var="curPage" value="${param.curPage }"/>
						<c:if test="${curPage == null }">
							<c:set var="curPage" value="0"/>
						</c:if>
						<ul>
							<c:forEach var="i" begin="0" end="${totalPage }">
								<li>
									<c:if test="${curPage == i }"><span>${i+1 }</span></c:if>
									<c:if test="${curPage != i }"><a href="recipe_main.do?curPage=${i }">${i+1 }</a></c:if>
								</li>
							</c:forEach>
						</ul>
					</div>	<!-- paging_section -->
				</div>	<!-- page2_contents -->
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
				<div></div>	<!-- flex 3등분 용도 -->
				<div class="social">
					<a href="#"><i class="fab fa-kaggle"></i></a>
					<a href="#"><i class="fab fa-facebook-f"></i></a>
					<a href="#"><i class="fab fa-twitter"></i></a>
					<a href="#"><i class="fab fa-youtube"></i></a>
					<p id="copyright">© Kakao Corp. All rights reserved.</p>
				</div>
				<div></div>	<!-- flex 3등분 용도 -->
			</div>	<!-- footer_list -->
		</footer>
		<a href="#container" id="top_button"><i class="fas fa-arrow-circle-up"></i></a>
	</div>
	<script>
		function searchBtn(){
			if(document.getElementById("search_bar").value==''){
				alert("한 글자 이상입력해주세요.");
				return false;
			}
			document.getElementById('search_form').submit();
		}
	</script>
</body>
</html>
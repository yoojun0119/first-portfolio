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
<link rel="stylesheet" href="style/main.css">
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">	<!-- xeicon css cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	<!-- jquery cnd -->
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>	<!-- bxslider cnd -->
<script src="https://kit.fontawesome.com/f05b6c991f.js" crossorigin="anonymous"></script>	<!-- fontawesome cnd -->
<script src="script/main.js"></script>
</head>
<body>
	<div id="container">
		<header id="main_header">
			<div id="header_logo_section">
				<a href="main.do"><img src="images/Kakao_logo1.png" width="150px"></a>
			</div>
			<div id="header_menu_section">
				<ul>
					<li><a href="main.do">Home</a></li>
					<li><a href="layout/recipe_main.do">레시피</a></li>
					<li><a href="layout/freezer_main.do">나의 냉장고</a></li>
					<c:if test="${checkedMember.granted == 'Y' }">
						<li><a href="layout/admin_main.do">관리자</a></li>
					</c:if>
				</ul>
			</div>	<!-- header_menu_section -->
			<div id="header_login_section">
				<c:if test="${checkedMember == null }">
					<a href="layout/login.do?url=../main.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="layout/member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="layout/logout.do?url=main.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="layout/member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="page1_contents">
					<div class="slider">
						<div class="slider_contetns">
							<img class="slide_img" src="https://cdn.shopify.com/s/files/1/2825/6246/files/mobile-image_1400x.jpg?v=1603379010">
							<div class="wrap_contents">
								<p>카카오쿡만의 다양한 레시피를 확인하세요.</p>
								<a href="layout/recipe_main.do"><button>레시피 보러가기</button></a>
							</div>
						</div>	<!-- slider_contetns -->
						<div class="slider_contetns">
							<img class="slide_img" src="https://images-na.ssl-images-amazon.com/images/I/61P5TdY6aLL._SL1000_.jpg">
							<div class="text_background"></div>
							<div class="wrap_contents">
								<p>나만의 냉장고를 만들어 편하게 관리하세요.</p>
								<a href="layout/freezer_main.do"><button>냉장고 관리하기</button></a>
							</div>
						</div>	<!-- slider_contetns -->
						<div class="slider_contetns">
							<img class="slide_img" src="images/recipe_top_view.jpg">
							<div class="wrap_contents">
								<p>자신만의 레시피를 공유해보세요.</p>
								<a href="#"><button>레시피 공유하기</button></a>
							</div>
						</div>	<!-- slider_contetns -->
					</div>
				</div>	<!-- page1_contents -->
				<div id="page2_contents">
					<h3>카카오쿡으로 시작하는 편리한 식생활</h3>
					<div id="page2_wrap">
						<div class="page2_feature">
							<div class="feature_image_section">
								<img src="https://static.vecteezy.com/system/resources/thumbnails/002/621/029/small/chef-recipe-book-kitchen-utensil-line-style-icon-free-vector.jpg">
							</div>
							<div class="feature_description">
								<h4>카카오쿡 레시피</h4>
								<p>카카오쿡이 엄선한 레시피로<br>맛있고 건강한 식습관을 누리세요.</p>
							</div>
						</div>
						<div class="page2_feature"">
							<div class="feature_image_section">
								<img src="images/feature2.jpg">
							</div>
							<div class="feature_description">
								<h4>레시피 추천</h4>
								<p>냉장고에 있는 재료로 당장 만들 수 있는 <br>레시피를 추천해드릴게요.</p>
							</div>
						</div>
						<div class="page2_feature"">
							<div class="feature_image_section">
								<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtwrlQXbISfOo0FxrgBc9Gs78F-ExkIu-89A&usqp=CAU">
							</div>
							<div class="feature_description">
								<h4>냉장고 관리</h4>
								<p>관리하기 힘들던 냉장고.<br>카카오쿡이 유통기한을 알려드릴게요.</p>
							</div>
						</div>	<!-- page2_feature -->
					</div>	<!-- page2_wrap -->
				</div>	<!-- page2_contents -->
			</article>
			<article class="page">
				<div id="page3_contents">
					<div id="top_view_recipe">
						<h3><span>BEST10</span> 레시피</h3>
						<ul>
							<c:forEach var="rDto" items="${top_list }">
								<li class="">
									<p class="ranking_num">
										<b>순위</b>
									</p>
									<div class="recipe_image_section">
										<a href="layout/recipe_view.do?no=${rDto.no }"><img src="${rDto.recipe_thumbnail_image }"></a>
									</div>
									<div class="recipe_caption">
										<div class="recipe_caption_title">${rDto.title }</div>
										<div class="recipe_caption_info">
											<c:choose>
												<c:when test="${(rDto.gradeAverage == 0)||(rDto.gradeAverage == null)}">
													<c:forEach var="j" begin="1" end="5">
														<span class="recipe_caption_info_star">
															<img src="images/unchecked_star_btn.png">
														</span>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="j" begin="1" end="${rDto.gradeAverage }">
														<span class="recipe_caption_info_star">
															<img src="images/checked_star_btn.png">
														</span>
													</c:forEach>
												</c:otherwise>
											</c:choose>
											<span class="recipe_caption_info_rCnt">조회수 ${rDto.rCnt }</span>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>	<!-- top_view_recipe -->
				</div>	<!-- page3_contents -->
				<div id="page4_contents">
					<div id="random_recipe">
						<h3>이런 레시피는 어떠세요?</h3>
						<div class="slider2">
							<c:forEach var="rDto" items="${ran_list }">
								<div class="slider2_content">
									<div class="recipe_image_section">
										<a href="layout/recipe_view.do?no=${rDto.no }"><img src="${rDto.recipe_thumbnail_image }"></a>
									</div>
									<div class="recipe_caption">
										<div class="recipe_caption_title">${rDto.title }</div>
										<div class="recipe_caption_info">
											<c:choose>
												<c:when test="${(rDto.gradeAverage == 0)||(rDto.gradeAverage == null)}">
													<c:forEach var="j" begin="1" end="5">
														<span class="recipe_caption_info_star">
															<img src="images/unchecked_star_btn.png">
														</span>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="j" begin="1" end="${rDto.gradeAverage }">
														<span class="recipe_caption_info_star">
															<img src="images/checked_star_btn.png">
														</span>
													</c:forEach>
												</c:otherwise>
											</c:choose>
											<span class="recipe_caption_info_rCnt">조회수 ${rDto.rCnt }</span>
										</div>
									</div>
								</div>	<!-- slider2_content -->
							</c:forEach>
						</div>	<!-- slider2 -->
						<i class="xi-angle-left-thin"></i>
						<i class="xi-angle-right-thin"></i>
					</div>	<!-- random_recipe -->
				</div>	<!-- page4_contents -->
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
</body>
</html>
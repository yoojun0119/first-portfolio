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
<link rel="stylesheet" href="../style/admin_main.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	<!-- jquery cnd -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-circle-progress/1.2.2/circle-progress.min.js"></script>

<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
<script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>

<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>

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
					<a href="login.do?url=admin_main.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="logout.do?url=admin_main.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<h1>종합관리</h1>
				<div id="member_wrap">
					<div id="admin_manage_section">
						<div id="admin_list">
							<div id="admin_list_header">
								<h3>관리자</h3>
							</div>
							<div id="admin_list_table">
								<table border="1">
									<tr>
										<th>아이디</th>
										<th>이름</th>
										<th>관리자 권한</th>
									</tr>
									<c:forEach var="mDto" items="${m_admin_list }">
										<tr>
											<td>${mDto.id }</td>
											<td>${mDto.name }</td>
											<td>
												<c:if test="${mDto.superGranted == 'N' }">
													<span>관리자</span><input type="button" class="grantedBtn" value="권한해제" onclick="grantChk('${mDto.id}', 'N')">
												</c:if>
												<c:if test="${mDto.superGranted == 'Y' }">최고관리자</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>	<!-- admin_manage_section -->
					<div id="user_manage_section">
						<div id="user_list">
							<div id="user_list_header">
								<div id="user_text" class="each_contents_text">
									<h3>유저</h3>
								</div>	<!-- grocery_text -->
								<div id="user_function">
									<c:if test="${m_listAll }">
										<form action="admin_main.do" method="post">
											<input type="button" class="showAllBtn" value="전체보기" onclick="window.location='admin_main.do'">
										</form>
									</c:if>
									<div id="search_wrap">
										<form action="admin_main.do" method="post" id="search_form">
											<input type="text" id="search_bar" name="m_search_contents" placeholder="유저 검색" onfocus="this.placeholder=''" onblur="this.placeholder='유저 검색'">
											<a href="#" id="search_btn" onclick="return searchBtn()">
												<i class="fas fa-search"></i>
											</a>
										</form>
									</div>
									<form action="admin_main.do" method="post">
										<select id="m_sort" name="m_sortWay" onchange="submit()">
											<option value="nameSort" <c:if test="${m_sortWay == 'nameSort' }">selected</c:if>>이름순</option>
											<option value="IDSort" <c:if test="${m_sortWay == 'IDSort' }">selected</c:if>>아이디순</option>
										</select>
									</form>
								</div>	<!-- grocery_function -->
							</div>	<!-- user_list_header -->
							<div id="user_list_table">
								<table border="1">
									<tr>
										<th>이름</th>
										<th>아이디</th>
										<th>성별</th>
										<th>생년월일</th>
										<th></th>
									</tr>
									<c:forEach var="mDto" items="${m_user_list }">
										<tr>
											<td>${mDto.name }</td>
											<td>${mDto.id }</td>
											<td>
												<c:if test="${mDto.sex == 'male'}">남</c:if>
												<c:if test="${mDto.sex == 'female'}">여</c:if>
											</td>
											<td>${mDto.birthYear }-${mDto.birthMonth }-${mDto.birthDate }</td>
											<td>
												<input type="button" class="grantedBtn" value="권한부여" onclick="grantChk('${mDto.id}', 'Y')">
												<input type="button" class="grantedBtn" value="삭제" onclick="delChk('${mDto.id}')">
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="paging" id="member_paging_section">
								<c:set var="m_curPage" value="${param.m_curPage }"/>
								<c:if test="${m_curPage == null }">
									<c:set var="m_curPage" value="0"/>
								</c:if>
								<ul>
									<c:forEach var="i" begin="0" end="${m_totalPage }">
										<li>
											<c:if test="${m_curPage == i }"><span id="m_paging">${i+1 }</span></c:if>
											<c:if test="${m_curPage != i }"><a href="admin_main.do?m_curPage=${i }">${i+1 }</a></c:if>
										</li>
									</c:forEach>
								</ul>
							</div>	<!-- member_paging_section -->
						</div>
					</div>	<!-- user_manage_section -->
					<div id="member_info_section">
						<div id="member_info_chart1">
							<div class="member_chart">
								<h1>남성</h1>
								<div class="sexChart maleSexChart" data-percent="${rateOfMale }"><span>${rateOfMale}</span></div>
							</div>
							<div class="member_chart">
								<h1>여성</h1>
								<div class="sexChart femaleSexChart" data-percent="${rateOfFemale }"><span>${rateOfFemale}</span></div>
							</div>
						</div>
						<div id="member_info_chart2">
							<div class="member_chart">
								<div id="ageChart"></div>
							</div>
						</div>
					</div>	<!-- member_info_section -->
				</div>	<!-- member_wrap -->
				<div id="recipe_wrap">
					<div id="recipe_list">
						<div id="recipe_header">
							<div id="recipe_text" class="each_contents_text">
								<h3><span>레시피</span> 목록</h3>
								<button onclick="window.loation='recipe_insert.do'" id="recipe_insertBtn" class="insertBtn"><i class="fas fa-plus-circle"></i>&nbsp;추가</button>
							</div>	<!-- recipe_text -->
							<div id="recipe_function">
								<c:if test="${r_listAll }">
									<form action="admin_main.do" method="post">
										<input type="button" class="showAllBtn" value="전체보기" onclick="window.location='admin_main.do'">
									</form>
								</c:if>
								<div id="search_wrap">
									<form action="admin_main.do" method="post" id="search_form">
										<input type="text" id="search_bar" name="r_search_contents" placeholder="레시피 검색" onfocus="this.placeholder=''" onblur="this.placeholder='레시피 검색'">
										<a href="#" id="search_btn" onclick="return searchBtn()">
											<i class="fas fa-search"></i>
										</a>
									</form>
								</div>
								<form action="admin_main.do" method="post">
									<select id="r_sort" name="r_sortWay" onchange="submit()">
										<option value="latestSort" <c:if test="${r_sortWay == 'latestSort' }">selected</c:if>>최신순</option>
										<option value="rCntSort" <c:if test="${r_sortWay == 'rCntSort' }">selected</c:if>>조회순</option>
										<option value="bookmarkCntSort" <c:if test="${r_sortWay == 'bookmarkCntSort' }">selected</c:if>>인기순</option>
										<option value="alphabeticalSort" <c:if test="${r_sortWay == 'alphabeticalSort' }">selected</c:if>>가나다순</option>
									</select>
								</form>
							</div>	<!-- recipe_function -->
						</div>	<!-- recipe_header -->
						<table border="1">
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>제목</th>
								<th>대분류</th>
								<th>요리종류</th>
								<th>재료타입</th>
								<th>조리방법</th>
								<th>조회수</th>
								<th>관심등록수</th>
								<th></th>
							</tr>
							<c:forEach var="rDto" items="${r_list }">
								<tr class="normal_tr">
									<td>${rDto.no }</td>
									<td>${rDto.name }</td>
									<td><a href="recipe_view.do?no=${rDto.no }&id=${checkedMember.id}">${rDto.title }</a></td>
									<td>
										<c:if test="${rDto.recipe_type == 'kor_foods'}">한식</c:if>
										<c:if test="${rDto.recipe_type == 'cha_foods'}">중식</c:if>
										<c:if test="${rDto.recipe_type == 'jap_foods'}">일식</c:if>
										<c:if test="${rDto.recipe_type == 'etc_foods'}">양식</c:if>
									</td>
									<td>
										<c:if test="${rDto.food_type == 'main_dish'}">메인</c:if>
										<c:if test="${rDto.food_type == 'soup_dish'}">국물</c:if>
										<c:if test="${rDto.food_type == 'noodle_dish'}">면</c:if>
										<c:if test="${rDto.food_type == 'side_dish'}">반찬</c:if>
										<c:if test="${rDto.food_type == 'salad_dish'}">샐러드</c:if>
										<c:if test="${rDto.food_type == 'dessert_dish'}">디저트</c:if>
										<c:if test="${rDto.food_type == 'etc_food_dish'}">기타</c:if>
									</td>
									<td>
										<c:if test="${rDto.ingredient_type == 'meat_dish'}">육류</c:if>
										<c:if test="${rDto.ingredient_type == 'seafood_dish'}">해물류</c:if>
										<c:if test="${rDto.ingredient_type == 'vegetable_dish'}">채소류</c:if>
										<c:if test="${rDto.ingredient_type == 'processed_dish'}">가공식품류</c:if>
										<c:if test="${rDto.ingredient_type == 'grain_dish'}">곡류</c:if>
										<c:if test="${rDto.ingredient_type == 'etc_ingredient_dish'}">기타</c:if>
									</td>
									<td>
										<c:if test="${rDto.method_type == 'roast_dish'}">볶음</c:if>
										<c:if test="${rDto.method_type == 'grill_dish'}">구이</c:if>
										<c:if test="${rDto.method_type == 'boil_dish'}">끓이기</c:if>
										<c:if test="${rDto.method_type == 'boil_down_dish'}">조림</c:if>
										<c:if test="${rDto.method_type == 'steam_dish'}">찜</c:if>
										<c:if test="${rDto.method_type == 'etc_method_dish'}">기타</c:if>
									</td>
									<td>${rDto.rCnt }</td>
									<td>${rDto.bookmarkCnt }</td>
									<td>
										<input type="button" value="수정" onclick="">
										<input type="button" value="삭제" onclick="">
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="paging" id="recipe_paging_section">
							<c:set var="r_curPage" value="${param.r_curPage }"/>
							<c:if test="${r_curPage == null }">
								<c:set var="r_curPage" value="0"/>
							</c:if>
							<ul>
								<c:forEach var="i" begin="0" end="${r_totalPage }">
									<li>
										<c:if test="${r_curPage == i }"><span id="r_paging">${i+1 }</span></c:if>
										<c:if test="${r_curPage != i }"><a href="admin_main.do?r_curPage=${i }">${i+1 }</a></c:if>
									</li>
								</c:forEach>
							</ul>
						</div>	<!-- recipe_paging_section -->
					</div>	<!-- recipe_list -->
					<div id="recipe_info">
						<div id="recipe_chart"></div>
						<div id="recipe_chart_info">
							<span>조회 대비 관심수 백분율 : <b>(관심수 / 조회수) x 100</b></span>
						</div>
					</div>	<!-- recipe_info -->
				</div>	<!-- recipe_wrap -->
				<div id="grocery_wrap">
					<div id="grocery_list">
						<div id="grocery_header">
							<div id="grocery_text" class="each_contents_text">
								<h3><span>재료</span> 목록</h3>
								<button onclick="window.loation='grocery_insert.do'" id="recipe_insertBtn" class="insertBtn"><i class="fas fa-plus-circle"></i>&nbsp;추가</button>
							</div>	<!-- grocery_text -->
							<div id="grocery_function">
								<c:if test="${g_listAll }">
									<form action="admin_main.do" method="post">
										<input type="button" class="showAllBtn" value="전체보기" onclick="window.location='admin_main.do'">
									</form>
								</c:if>
								<div id="search_wrap">
									<form action="admin_main.do" method="post" id="search_form">
										<input type="text" id="search_bar" name="g_search_contents" placeholder="재료 검색" onfocus="this.placeholder=''" onblur="this.placeholder='레시피 검색'">
										<a href="#" id="search_btn" onclick="return searchBtn()">
											<i class="fas fa-search"></i>
										</a>
									</form>
								</div>
								<form action="admin_main.do" method="post">
									<select id="g_sort" name="g_sortWay" onchange="submit()">
										<option value="latestSort" <c:if test="${g_sortWay == 'latestSort' }">selected</c:if>>최신순</option>
										<option value="insertCnt" <c:if test="${g_sortWay == 'insertCnt' }">selected</c:if>>인기순</option>
										<option value="shelflifeSort" <c:if test="${g_sortWay == 'shelflifeSort' }">selected</c:if>>유통기한순</option>
										<option value="alphabeticalSort" <c:if test="${g_sortWay == 'alphabeticalSort' }">selected</c:if>>가나다순</option>
									</select>
								</form>
							</div>	<!-- grocery_function -->
						</div>	<!-- grocery_header -->
						<table border="1">
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>코드</th>
								<th>타입</th>
								<th>패키지</th>
								<th>수량</th>
								<th>구매수</th>
								<th>유통기한</th>
								<th></th>
							</tr>
							<c:forEach var="gDto" items="${g_list }">
								<c:choose>
									<c:when test="${(gDto.rest_of_shelf_life < 0)&&(gDto.total_amount == 0)}">
										<tr class="both_out_text">
									</c:when>
									<c:when test="${gDto.rest_of_shelf_life < 0}">
										<tr class="out_of_shelf_life_text">
									</c:when>
									<c:when test="${gDto.total_amount == 0}">
										<tr class="sold_out_text">
									</c:when>
									<c:otherwise>
										<tr class="normal_tr">
									</c:otherwise>
								</c:choose>
									<td>${gDto.no }</td>
									<td>${gDto.name }</td>
									<td>${gDto.code }</td>
									<td>
										<c:if test="${gDto.type == 'meat'}">육류</c:if>
										<c:if test="${gDto.type == 'seafood'}">해물류</c:if>
										<c:if test="${gDto.type == 'vegetable'}">채소류</c:if>
										<c:if test="${gDto.type == 'processed'}">가공식품류</c:if>
										<c:if test="${gDto.type == 'grain'}">곡류</c:if>
										<c:if test="${gDto.type == 'etc'}">기타</c:if>
									</td>
									<td>${gDto.package_amount } ${gDto.package_unit }</td>
									<td>${gDto.total_amount }</td>
									<td>${gDto.insertCnt }</td>
									<td>${gDto.shelf_life }</td>
									<td>
										<input type="button" value="수정" onclick="">
										<input type="button" value="삭제" onclick="">
									</td>
							</c:forEach>
						</table>
						<div class="paging" id="grocery_paging_section">
							<c:set var="g_curPage" value="${param.g_curPage }"/>
							<c:if test="${g_curPage == null }">
								<c:set var="g_curPage" value="0"/>
							</c:if>
							<ul>
								<c:forEach var="i" begin="0" end="${g_totalPage }">
									<li>
										<c:if test="${g_curPage == i }"><span id="g_paging">${i+1 }</span></c:if>
										<c:if test="${g_curPage != i }"><a href="admin_main.do?g_curPage=${i }">${i+1 }</a></c:if>
									</li>
								</c:forEach>
							</ul>
						</div>	<!-- grocery_paging_section -->
					</div>	<!-- grocery_list -->
					<div id="grocery_info">
						<div id="grocery_info_soldout_section">
							<h3>품절 상품</h3>
							<div id="soldout_table">
								<table border="1">
									<tr>
										<th>번호</th>
										<th>이름</th>
										<th>코드</th>
										<th>타입</th>
										<th>패키지</th>
										<th>수량</th>
										<th>유통기한</th>
										<th></th>
									</tr>
									<c:forEach var="gDto" items="${g_soldout_list }">
										<tr>
											<td>${gDto.no }</td>
											<td>${gDto.name }</td>
											<td>${gDto.code }</td>
											<td>
												<c:if test="${gDto.type == 'meat'}">육류</c:if>
												<c:if test="${gDto.type == 'seafood'}">해물류</c:if>
												<c:if test="${gDto.type == 'vegetable'}">채소류</c:if>
												<c:if test="${gDto.type == 'processed'}">가공식품류</c:if>
												<c:if test="${gDto.type == 'grain'}">곡류</c:if>
												<c:if test="${gDto.type == 'etc'}">기타</c:if>
											</td>
											<td>${gDto.package_amount } ${gDto.package_unit }</td>
											<td>${gDto.total_amount }</td>
											<td>${gDto.shelf_life }</td>
											<td>
												<input type="button" value="수정" onclick="">
												<input type="button" value="삭제" onclick="">
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div id="grocery_info_shelf_life_section">
							<h3>유통기한 지난 상품</h3>
							<div id="shelfout_table">
								<table border="1">
									<tr>
										<th>번호</th>
										<th>이름</th>
										<th>코드</th>
										<th>타입</th>
										<th>패키지</th>
										<th>수량</th>
										<th>유통기한</th>
										<th></th>
									</tr>
									<c:forEach var="gDto" items="${g_out_of_shelf_life_list }">
										<tr>
											<td>${gDto.no }</td>
											<td>${gDto.name }</td>
											<td>${gDto.code }</td>
											<td>
												<c:if test="${gDto.type == 'meat'}">육류</c:if>
												<c:if test="${gDto.type == 'seafood'}">해물류</c:if>
												<c:if test="${gDto.type == 'vegetable'}">채소류</c:if>
												<c:if test="${gDto.type == 'processed'}">가공식품류</c:if>
												<c:if test="${gDto.type == 'grain'}">곡류</c:if>
												<c:if test="${gDto.type == 'etc'}">기타</c:if>
											</td>
											<td>${gDto.package_amount } ${gDto.package_unit }</td>
											<td>${gDto.total_amount }</td>
											<td>${gDto.shelf_life }</td>
											<td>
												<input type="button" value="수정" onclick="">
												<input type="button" value="삭제" onclick="">
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>	<!-- grocery_info -->
				</div>	<!-- grocery_wrap -->
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
		function grantChk(id, granted){
			let isConfirmed = confirm('해당 회원의 권한을 변경 하시겠습니까?');
			
			if(isConfirmed){
				window.location="admin_grantedOK.do?id="+id+"&granted="+granted;
			} else {
				return;
			}
		}
		function delChk(id){
			let isConfirmed = confirm('해당 회원을 삭제하시겠습니까?');
			
			if(isConfirmed){
				window.location="member_deleteOK.do?id="+id;
			} else {
				return;
			}
		}
	</script>
	<script>
		$('.maleSexChart').circleProgress({
			size: 150,
			startAngle: -Math.PI/2, //시작지점 (기본값 Math.PI)
			value: ${rateOfMale}, //그래프에 표시될 값 
			animation: true, //그래프가 그려지는 애니메이션 동작 여부
			reverse: false, //그래프가 반대방향으로 그려질지
			thickness: 20, //그래프두께
			lineCap: 'round', //그래프 선 모양
			fill: {gradient: ['#285A82', '#66BAFB']}, //그래프 선 색
			emptyFill: "rgba(0,0,0,0.3)", //그래프 빈칸색 기본값 rgba(0,0,0,0.1)
		}).on('circle-animation-progress', function(event, progress) {
		    $(this).find('span').html(Math.round(${rateOfMale * 100} * progress) + '<small>%</small>');
		});
		$('.femaleSexChart').circleProgress({
			size: 150,
			startAngle: -Math.PI/2,
			value: ${rateOfFemale},
			animation: true,
			reverse: false,
			thickness: 20,
			lineCap: 'round',
			fill: {gradient: ['#ff1e41', '#ff9f8e']},
			emptyFill: "rgba(0,0,0,0.3)",
		}).on('circle-animation-progress', function(event, progress) {
		    $(this).find('span').html(Math.round(${rateOfFemale * 100} * progress) + '<small>%</small>');
		});
	</script>
	<script>
		function searchBtn(){
			if(document.getElementById("search_bar").value==''){
				alert("한 글자 이상입력해주세요.");
				return false;
			}
			document.getElementById('search_form').submit();
		}
	</script>
	<script type="text/javascript">
		FusionCharts.ready(function(){
			var chartObj = new FusionCharts({
		    type: 'doughnut2d',
		    renderAt: 'ageChart',
		    width: '500',
		    height: '340',
		    dataFormat: 'json',
		    dataSource: {
		        "chart": {
		        	"numberPrefix": "$",
		            "bgColor": "#ffffff",
		            "startingAngle": "100",
		            "showLegend": "0",
		            "defaultCenterLabel": "나이 분포",
		            "centerLabel": "$label",
		            "centerLabelBold": "2",
		            "showTooltip": "0",
		            "decimals": "2",
		            "theme": "fusion"
		        },
		        "data": [{
		            "label": "50대 이상",
		            "value": "${rateOf99th}"
		        }, {
		            "label": "40대",
		            "value": "${rateOf40th}"
		        }, {
		            "label": "30대",
		            "value": "${rateOf30th}"
		        }, {
		            "label": "20대",
		            "value": "${rateOf20th}"
		        }, {
		            "label": "10대",
		            "value": "${rateOf10th}"
		        }]
		    }
		}
		);
			chartObj.render();
		});
	</script>
	<script type="text/javascript">
		window.onload = function() {
		var options = {
			animationEnabled: true,
			zoomEnabled: true,
			theme: "light2",
			title: {
				text: "메인재료 및 조리방법에 따른 레시피 지표",
				fontSize: 20,
				fontColor: "#60AEA0",
				margin: 40
			},
			axisX: {
				title: "조회수",
				titleFontWeight: "bold",
				titleFontSize: 14,
				titleFontColor: "#60AEA0"
			},
			axisY: {
				title: "관심등록 수",
				titleFontWeight: "bold",
				titleFontSize: 14,
				titleFontColor: "#60AEA0"
			},
			data: [{
				type: "bubble",
				fillOpacity: .8,
				toolTipContent: "<b>{name}</b><br/>조회: {x} <br/> 관심등록 : {y}<br/> 백분율: {z}",
				dataPoints: [
					<c:forEach var="rDto" items="${chart_list}">
						{
							x: ${rDto.rCnt},
							y: ${rDto.bookmarkCnt},
							z: Math.round((${rDto.bookmarkCnt / rDto.rCnt} * 100)*100) / 100,
							/* z: Math.round(( ${rDto.rCnt + rDto.bookmarkCnt} / (100 - (${(rDto.bookmarkCnt / rDto.rCnt) * 100})) ) * 100) / 100, */
							name: "${rDto.ingredient_type} & ${rDto.method_type}"
						},
					</c:forEach>
				]
			}]
		};
		$("#recipe_chart").CanvasJSChart(options);
		
		}
	</script>
</body>
</html>
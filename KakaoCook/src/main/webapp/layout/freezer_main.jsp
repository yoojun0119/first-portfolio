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
<link rel="stylesheet" href="../style/freezer_main.css">
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">	<!-- xeicon css cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>	<!-- bxslider cnd -->
<script src="https://kit.fontawesome.com/f05b6c991f.js" crossorigin="anonymous"></script>
<script src="../script/freezer_main.js"></script>
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
					<a href="login.do?url=freezer_main.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="logout.do?url=freezer_main.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="freezer_wrap">
					<div id="freezer_header">
						<h3>나만의 냉장고</h3>
					</div>
					<c:if test="${checkedMember != null }">
						<div id="freezer_info_section">
							<div id="shelf_life_section">
								<ul>
									<c:forEach var="fDto" items="${shelf_life_list }">
										<c:if test="${fDto.rest_of_shelf_life <= 30}">
											<li class="">
												<c:choose>
													<c:when test="${fDto.rest_of_shelf_life < 0}">
														<p><b>${fDto.name } </b>의 <b class="life_end">유통기한이 지났습니다.</b></p>
														<a href="#" onclick="shelfDelChk('${checkedMember.id}', '${fDto.no}')" class="shelfDelBtn">&nbsp;&nbsp;<i class="fas fa-times"></i></a>
													</c:when>
													<c:when test="${fDto.rest_of_shelf_life == 0}">
														<p><b>${fDto.name } </b>의 유통기한이 <b class="life_today">오늘</b>까지입니다.</p>
													</c:when>
													<c:when test="${fDto.rest_of_shelf_life <= 7}">
														<p><b>${fDto.name } </b>의 유통기한이 <b class="life_warning">${fDto.rest_of_shelf_life }일</b> 남았습니다.</p>
													</c:when>
													<c:when test="${7 < fDto.rest_of_shelf_life}">
														<p><b>${fDto.name } </b>의 유통기한이 <b class="life_alarm">${fDto.rest_of_shelf_life }일</b> 남았습니다.</p>
													</c:when>
												</c:choose>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>	<!-- shelf_life_section -->
							<div id="bookmarked_recipe_wrap">
								<h3>관심있는 레시피</h3>
								<div id="bookmarked_recipe_section">
									<ul>
										<c:forEach var="bookmark" items="${bookmarked_list }">
											<li>
												<div class="bookmark_image_section">
													<a href="recipe_view.do?no=${bookmark.no }&id=${checkedMember.id}">
														<img src="${bookmark.recipe_thumbnail_image }">
													</a>
												</div>
												<div class="bookmark_recipe_caption">
													<div class="bookmark_recipe_name">${bookmark.name }</div>
												</div>
												<a href="recipe_bookmarkDel.do?id=${checkedMember.id }&no=${bookmark.no }&url=freezer_main.do" class="bookmark_delBtn"><i class="fas fa-minus-circle"></i></a>
											</li>
										</c:forEach>
									</ul>
								</div>	<!-- bookmarked_recipe_section -->
							</div>	<!-- bookmarked_recipe_wrap -->
						</div>	<!-- freezer_info_section -->
					</c:if>
					<c:if test="${checkedMember == null }">
						<div id="freezer_info_nonlogin">
							<a href="login.do?url=freezer_main.do"><input type="button" value="로그인"></a>
						</div>
					</c:if>
					<div id="freezer_list_section">
						<c:if test="${checkedMember != null }">
							<div id="freezer_list_header">
								<form action="freezer_main.do" name="freezer_form" method="post" id="freezer_form">
									<input type="radio" name="freezer_type" value="all" class="all" id="all" <c:if test="${freezer_type == 'all' }">checked</c:if> onclick="submit()">
									<label for="all">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/all.png">
											<p>전체</p>
										</div>
									</label>
									<input type="radio" name="freezer_type" value="meat" class="each" id="meat" <c:if test="${freezer_type == 'meat' }">checked</c:if> onclick="submit()">
									<label for="meat">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/meat.png">
											<p>육류</p>
										</div>
									</label>
									<input type="radio" name="freezer_type" value="seafood" class="each" id="seafood" <c:if test="${freezer_type == 'seafood' }">checked</c:if> onclick="submit()">
									<label for="seafood">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/seafood.png">
											<p>해물류</p>
										</div>
									</label>
									<input type="radio" name="freezer_type" value="vegetable" class="each" id="vegetable" <c:if test="${freezer_type == 'vegetable' }">checked</c:if> onclick="submit()">
									<label for="vegetable">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/vegetable.png">
											<p>채소류</p>
										</div>
									</label>
									<input type="radio" name="freezer_type" value="processed" class="each" id="processed" <c:if test="${freezer_type == 'processed' }">checked</c:if> onclick="submit()">
									<label for="processed">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/processed.png">
											<p>가공식품류</p>
										</div>
									</label>
									<input type="radio" name="freezer_type" value="grain" class="each" id="grain" <c:if test="${freezer_type == 'grain' }">checked</c:if> onclick="submit()">
									<label for="grain">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/grain.png">
											<p>곡류</p>
										</div>
									</label>
									<input type="radio" name="freezer_type" value="etc" class="each" id="etc" <c:if test="${freezer_type == 'etc' }">checked</c:if> onclick="submit()">
									<label for="etc">
										<div class="freezer_form_label_contents">
											<img src="../images/ingredient/etc.png">
											<p>기타</p>
										</div>
									</label>
								</form>
							</div>	<!-- freezer_list_header -->
							<div id="freezer_list_table">
								<table border="1" id="freezer_table">
									<tr id="f_list_table_header">
										<th>이름</th>
										<th>수량</th>
										<th>구매일</th>
										<th>유통기한</th>
										<th>
											<form action="freezer_main.do" method="post" id="freezer_sort">
												<select name="f_sortWay" onchange="submit()">
													<option value="latestSort" <c:if test="${f_sortWay == 'latestSort' }">selected</c:if>>최신순</option>
													<option value="lifeSort" <c:if test="${f_sortWay == 'lifeSort' }">selected</c:if>>유통기한순</option>
													<option value="alphabeticalSort" <c:if test="${f_sortWay == 'alphabeticalSort' }">selected</c:if>>가나다순</option>
												</select>
											</form>
										</th>
									</tr>
									<c:forEach var="fDto" items="${f_list }">
										<tr id="freezer_list"${fDto.no }>
											<td>${fDto.name }</td>
											<td>
												<form action="freezer_modifyOK.do" method="post">
													<input type="number" onchange="submit()" name="package_amount" value="${fDto.package_amount}" min="0" max="${gDto.total_amount}"> ${fDto.package_unit }
													<input type="hidden" name="id" value="${checkedMember.id}">
													<input type="hidden" name="no" value="${fDto.no}">
												</form>
											</td>
											<td>${fDto.purchase_date }</td>
											<td>${fDto.shelf_life }</td>
											<td>
												<div class="btn_wrap">
													<input type="button" id="recipeBtn" name="recipeBtn" value="관련 레시피" onclick="window.location='recipe_search.do?search_contents=${fDto.name }'">
													<input type="button" id="searchBtn" name="searchBtn" value="재료 검색" onclick="window.location='grocery_search.do?search_contents=${fDto.name }'">
													<a href="#" onclick="delChk('${checkedMember.id}', '${fDto.no}')" class="freezerDelBtn">&nbsp;<i class="fas fa-times"></i></a>
												</div>
												<div id="storage_method">
													${fDto.storage_method }
												</div>
											</td>
										</tr>
									</c:forEach>
									<c:forEach var="i" begin="1" end="${12 - numOfFreezerList }">
										<tr><td colspan="5"></td></tr>
									</c:forEach>
									<tfoot>
										<tr id="f_list_table_footer"><td colspan="5"></td></tr>
									</tfoot>
								</table>
							</div>	<!-- freezer_list_table -->
							<div id="freezer_paging_section">
								<c:set var="f_curPage" value="${param.f_curPage }"/>
								<c:if test="${f_curPage == null }">
									<c:set var="f_curPage" value="0"/>
								</c:if>
								<ul>
									<c:forEach var="i" begin="0" end="${f_totalPage }">
										<li>
											<c:if test="${f_curPage == i }"><span>${i+1 }</span></c:if>
											<c:if test="${f_curPage != i }"><a href="freezer_main.do?f_curPage=${i }">${i+1 }</a></c:if>
										</li>
									</c:forEach>
								</ul>
							</div>	<!-- freezer_paging_section -->
						</c:if>
						<c:if test="${checkedMember == null }">
							<div id="freezer_list_nonlogin">
								<a href="login.do?url=freezer_main.do"><input type="button" value="로그인"></a>
							</div>
						</c:if>
					</div>	<!-- freezer_list_section -->
					<div id="freezer_recipe">
						<c:if test="${checkedMember != null }">
							<h3><b>냉장고 재료</b>로 만들 수 있는 레시피</h3>
							<div class="slider">
								<c:forEach var="rDto" items="${possible_list }">
									<div class="slider2_content">
										<div class="recipe_image_section">
											<a href="recipe_view.do?no=${rDto.no }" class=""><img src="../${rDto.recipe_thumbnail_image }"></a>
										</div>
										<div class="recipe_caption">
											<div class="recipe_caption_title">${rDto.title }</div>
											<div class="recipe_caption_info">
												<c:choose>
													<c:when test="${(rDto.gradeAverage == 0)||(rDto.gradeAverage == null)}">
														<c:forEach var="j" begin="1" end="5">
															<span class="recipe_caption_info_star">
																<img src="../images/unchecked_star_btn.png">
															</span>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:forEach var="j" begin="1" end="${rDto.gradeAverage }">
															<span class="recipe_caption_info_star">
																<img src="../images/checked_star_btn.png">
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
						</c:if>
						<c:if test="${checkedMember == null }">
							<h3>이런 레시피는 어떠세요?</h3>
							<div class="slider">
								<c:forEach var="rDto" items="${ran_list }">
									<div class="slider2_content">
										<div class="recipe_image_section">
											<a href="recipe_view.do?no=${rDto.no }&id=${checkedMember.id }" class=""><img src="../${rDto.recipe_thumbnail_image }"></a>
										</div>
										<div class="recipe_caption">
											<div class="recipe_caption_title">${rDto.title }</div>
											<div class="recipe_caption_info">
												<c:choose>
													<c:when test="${(rDto.gradeAverage == 0)||(rDto.gradeAverage == null)}">
														<c:forEach var="j" begin="1" end="5">
															<span class="recipe_caption_info_star">
																<img src="../images/unchecked_star_btn.png">
															</span>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:forEach var="j" begin="1" end="${rDto.gradeAverage }">
															<span class="recipe_caption_info_star">
																<img src="../images/checked_star_btn.png">
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
						</c:if>
					</div>	<!-- freezer_recipe -->
				</div>	<!-- freezer_wrap -->
			</article>
			<article class="page">
				<div id="grocery_main">
					<div id="grocery_header">
						<div id="search_wrap">
							<form action="grocery_search.do" method="post" id="search_form">
								<input type="text" id="search_bar" name="search_contents" placeholder="식재료 검색" onfocus="this.placeholder=''" onblur="this.placeholder='식재료 검색'">
								<a href="javascript:void(0)" id="search_btn" onclick="return searchBtn()">
									<i class="fas fa-search"></i>
								</a>
							</form>
						</div>	<!-- search_wrap -->
						<form action="freezer_main.do" name="grocery_form" method="post" id="type_form">
							<input type="radio" name="grocery_type" value="all" class="all" id="all2" <c:if test="${grocery_type == 'all' }">checked</c:if> onclick="submit()">
							<label for="all2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/all.png"></div>
									<div class="grocery_form_label_contents_title"><p>전체</p></div>
								</div>
							</label>
							<input type="radio" name="grocery_type" value="meat" class="each" id="meat2" <c:if test="${grocery_type == 'meat' }">checked</c:if> onclick="submit()">
							<label for="meat2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/meat.png"></div>
									<div class="grocery_form_label_contents_title"><p>육류</p></div>
								</div>
							</label>
							<input type="radio" name="grocery_type" value="seafood" class="each" id="seafood2" <c:if test="${grocery_type == 'seafood' }">checked</c:if> onclick="submit()">
							<label for="seafood2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/seafood.png"></div>
									<div class="grocery_form_label_contents_title"><p>해물류</p></div>
								</div>
							</label>
							<input type="radio" name="grocery_type" value="vegetable" class="each" id="vegetable2" <c:if test="${grocery_type == 'vegetable' }">checked</c:if> onclick="submit()">
							<label for="vegetable2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/vegetable.png"></div>
									<div class="grocery_form_label_contents_title"><p>채소류</p></div>
								</div>
							</label>
							<input type="radio" name="grocery_type" value="processed" class="each" id="processed2" <c:if test="${grocery_type == 'processed' }">checked</c:if> onclick="submit()">
							<label for="processed2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/processed.png"></div>
									<div class="grocery_form_label_contents_title"><p>가공식품류</p></div>
								</div>
							</label>
							<input type="radio" name="grocery_type" value="grain" class="each" id="grain2" <c:if test="${grocery_type == 'grain' }">checked</c:if> onclick="submit()">
							<label for="grain2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/grain.png"></div>
									<div class="grocery_form_label_contents_title"><p>곡류</p></div>
								</div>
							</label>
							<input type="radio" name="grocery_type" value="etc" class="each" id="etc2" <c:if test="${grocery_type == 'etc' }">checked</c:if> onclick="submit()">
							<label for="etc2">
								<div class="grocery_form_label_contents">
									<div class="grocery_form_label_contents_img"><img src="../images/ingredient/etc.png"></div>
									<div class="grocery_form_label_contents_title"><p>기타</p></div>
								</div>
							</label>
						</form>
					</div>	<!-- grocery_header -->
					<div id="grocery_wrap">
						<c:set var="groceryType" value="${param.groceryType }"/>
						<c:if test="${groceryType == null }">
							<c:set var="groceryType" value="all"/>
						</c:if>
						<div id="grocery_list">
							<div id="grocery_list_header">
								<c:if test="${checkedMember.granted == 'Y' }">
									<button onclick="window.location='grocery_insert.do'"><i class="fas fa-plus-circle"></i>&nbsp;식재료 등록</button>
								</c:if>
								<form action="freezer_main.do" method="post">
									<select name="g_sortWay" onchange="submit()">
										<option value="latestSort" <c:if test="${g_sortWay == 'latestSort' }">selected</c:if>>최신순</option>
										<option value="alphabeticalSort" <c:if test="${g_sortWay == 'alphabeticalSort' }">selected</c:if>>가나다순</option>
									</select>
								</form>
								<div id="select_box">
									<button onclick="checkedIntoCart()">선택항목 담기</button>
								</div>
							</div>
							<ul>
								<c:forEach var="gDto" items="${g_list }">
									<li class="">
										<c:if test="${(gDto.rest_of_shelf_life > 0) && (gDto.total_amount > 0) }">
											<input type="checkbox" name="checkedbox" class="select_checkbox" value="${gDto.no }">
											<div class="grocery_image_section">
												<img src="../images/ingredient/${gDto.type }.png">
												<div class="grocery_hover_info">
													<h3 class="hover_name"><b>${gDto.name }</b></h3>
													<p class="hover_package_amount"><b>패키지 :</b> ${gDto.package_amount } ${gDto.package_unit }</p>
													<p class="hover_total_amount"><b>남은 수량 :</b> ${gDto.total_amount }</p>
													<p class="hover_shelf_life"><b>유통기한 :</b> ${gDto.shelf_life }</p>
													<p class="hover_storage_method"><b>보관방법 :</b> <br>${gDto.storage_method }</p>
												</div>
											</div>
											<div class="grocery_caption">
												<div class="grocery_caption_name"><b>${gDto.name }</b> <span class="grocery_caption_info_package_amount">${gDto.package_amount }${gDto.package_unit }</span></div>
												<div class="grocery_caption_info">
												</div>
												<div class="grocery_add">
													<input type="button" id="add_btn${gDto.no }" name="add_btn" value="장바구니 담기" onclick="intoCart('${gDto.code}', '${gDto.name}', '${gDto.package_amount}', '${gDto.package_unit}', '${gDto.total_amount}', '${gDto.no}')">
													<input type="number" id="numOfPurchase${gDto.no }" name="numOfPurchase" value="1" min="1" max="${gDto.total_amount}">
												</div>
												<c:if test="${checkedMember.granted == 'Y' }">
													<div class="grocery_manage">
														<button onclick="window.location='grocery_modify.do?code=${gDto.code}'">수정하기</button>
														<button onclick="deleteOK('${gDto.code}')">삭제하기</button>
													</div>
												</c:if>
											</div>
										</c:if>
										<c:if test="${(gDto.rest_of_shelf_life <= 0) || (gDto.total_amount <= 0) }">
											<div class="grocery_banned_image_section">
												<img src="../images/ingredient/${gDto.type }.png">
												<c:choose>
													<c:when test="${(gDto.rest_of_shelf_life <= 0)&&(gDto.total_amount <= 0) }">
														<img src="../images/ingredient/banned.png">
													</c:when>
													<c:when test="${gDto.rest_of_shelf_life <= 0 }">
														<img src="../images/ingredient/banned.png">
													</c:when>
													<c:when test="${gDto.total_amount <= 0 }">
														<img src="../images/ingredient/soldout.png">
													</c:when>
												</c:choose>
											</div>
											<div class="grocery_caption">
												<div class="grocery_caption_name"><b>${gDto.name }</b> <span class="grocery_caption_info_package_amount">${gDto.package_amount }${gDto.package_unit }</span></div>
												<c:if test="${checkedMember.granted == 'Y' }">
													<div class="grocery_manage">
														<button onclick="window.location='grocery_modify.do?code=${gDto.code}'">수정하기</button>
														<button onclick="deleteOK('${gDto.code}')">삭제하기</button>
													</div>
												</c:if>
											</div>
										</c:if>
									</li>
								</c:forEach>
							</ul>
							<c:if test="${nonList != null}">
								<div id="nonList">존재하는 재료가 없습니다.</div>
							</c:if>
							<div id="paging_section">
								<c:set var="g_curPage" value="${param.g_curPage }"/>
								<c:if test="${g_curPage == null }">
									<c:set var="g_curPage" value="0"/>
								</c:if>
								<ul>
									<c:forEach var="i" begin="0" end="${g_totalPage }">
										<li>
											<c:if test="${g_curPage == i }"><span>${i+1 }</span></c:if>
											<c:if test="${g_curPage != i }"><a href="freezer_main.do?g_curPage=${i }">${i+1 }</a></c:if>
										</li>
									</c:forEach>
								</ul>
							</div>	<!-- paging_section -->
						</div>	<!-- grocery_list -->
						<div id="grocery_cart">
							<div id="grocery_cart_header">
								<h3>장바구니</h3>
							</div>
							<div id="grocery_cart_main">
								<form action="freezer_insertOK.do" method="post" id="cart_form" name="cart_form" onsubmit="return checkID()">
									<ul id="cart_content"></ul>
									<input type="hidden" name="id" id="checkedMember" value="${checkedMember.id }">
								</form>
							</div>
							<div id="grocery_cart_footer">
								<input type="submit" form="cart_form" value="냉장고에 넣기">
							</div>
						</div>	<!-- grocery_cart -->
					</div>	<!-- grocery_wrap -->
				</div>	<!-- grocery_main -->
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
		let cnt = 0;
		function intoCart(code, name, amount, unit, total, no){
			let text = '';
			let cart_content = document.getElementById("cart_content");
			let numOfPurchase = document.getElementById("numOfPurchase"+no).value;
			
			if(code != null){
				cnt++;
				text+= '<li id="cart'+cnt+'">';
				text+= '<b>'+name+'</b>&nbsp';
				text+= '<span>'+amount+'</span>';
				text+= '<span>'+unit+'</span>';
				text+= '<input type="number" name="numOfPurchase" value="'+numOfPurchase+'" min="1" max="'+total+'">';
				text+= '<input type="hidden" name="code" value="'+code+'">';
				text+= '<a href="javascript:deleteBtn('+cnt+')" class="deleteBtn">&nbsp&nbsp<i class="fas fa-times"></i></a>';
				text+= '</li>';
			}
			$(cart_content).append(text);
		}
		function deleteBtn(cnt){
			$("#cart_content [id=cart"+cnt+"]").remove();
			cnt--;
		}
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
	<script>
		function deleteOK(target){
			let isConfirmed = confirm('정말 삭제하시겠습니까?');
			
			if(isConfirmed){
				window.location="grocery_deleteOK.do?code="+target;
			}
		}
	</script>
	<script>
		function checkedIntoCart(){
			let selectedList = document.querySelectorAll('input[name=checkedbox]:checked');
			
			if(selectedList.length == 0){
				alert("선택된 항목이 없습니다.");
				return;
			} else {
				for(let i=0; i<selectedList.length; i++){
					let no = selectedList[i].value;
					$("#add_btn"+no).click();
				}
			}
		}
	</script>
	<script>
		function checkID(){
			let checkedMember = document.getElementById("checkedMember").value;
			if((checkedMember == null)||(checkedMember == "")){
				alert("로그인 해주세요.");
				return false;
			}
		}
		function shelfDelChk(id, no){
			let isConfirmed = confirm('냉장고에서 해당 재료를 삭제하시겠습니까?');
			
			if(isConfirmed){
				window.location="freezer_deleteOK.do?id="+id+"&no="+no;
			} else {
				return;
			}
		}
		function delChk(id, no){
			let isConfirmed = confirm('정말 삭제하시겠습니까?');
			
			if(isConfirmed){
				window.location="freezer_deleteOK.do?id="+id+"&no="+no;
			} else {
				return;
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
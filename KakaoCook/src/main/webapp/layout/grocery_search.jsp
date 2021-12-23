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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
					<a href="login.do?url=grocery_search.do"><input type="button" value="로그인" style="cursor: pointer;"></a>
					<a href="member_register.do"><input type="button" value="회원가입" style="cursor: pointer;"></a>
				</c:if>
				<c:if test="${checkedMember != null }">
					<div>${checkedMember.name }&nbsp;님</div>
					<a href="logout.do?url=grocery_search.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="grocery_main">
					<div id="grocery_header">
						<div id="search_wrap">
							<form action="grocery_search.do" method="post" id="search_form">
								<input type="text" id="search_bar" name="search_contents" placeholder="식재료 검색" onfocus="this.placeholder=''" onblur="this.placeholder='식재료 검색'">
								<a href="#" id="search_btn" onclick="return searchBtn()">
									<i class="fas fa-search"></i>
								</a>
							</form>
						</div>	<!-- search_wrap -->
						<div id="search_result">
							<p><b>${search_contents }</b>에 대한 검색 결과입니다.</p>
							<button onclick="window.location='freezer_main.do'">전체목록</button>
						</div>
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
							</div>
							<ul>
								<c:forEach var="gDto" items="${g_list }">
									<li class="">
										<c:if test="${(gDto.rest_of_shelf_life > 0) && (gDto.total_amount > 0) }">
											<input type="checkbox" name="checkedbox" class="select_checkbox" value="${gDto.no }">
											<div class="grocery_image_section">
												<img src="../images/ingredient/${gDto.type }.png">
												<div class="grocery_hover_info">
													<h3 class="hover_name">${gDto.name }</h3>
													<p class="hover_package_amount">패키지 : ${gDto.package_amount } ${gDto.package_unit }</p>
													<p class="hover_total_amount">남은 수량 : ${gDto.total_amount }</p>
													<p class="hover_shelf_life">유통기한 : ${gDto.shelf_life }</p>
													<p class="hover_storage_method">보관방법 : ${gDto.storage_method }</p>
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
												<c:if test="${gDto.rest_of_shelf_life <= 0 }">
													<img src="../images/ingredient/banned.png">
												</c:if>
												<c:if test="${gDto.total_amount <= 0 }">
													<img src="../images/ingredient/soldout.png">
												</c:if>
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
								<form action="freezer_insertOK.do" method="post" id="cart_form" name="cart_form">
									<ul id="cart_content"></ul>
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
		function intoCart(code, name, amount, unit, total){
			let text = '';
			let cart_content = document.getElementById("cart_content");
			let numOfPurchase = document.getElementById("numOfPurchase").value;
			
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
		function deleteOK(target){
			let isConfirmed = confirm('정말 삭제하시겠습니까?');
			
			if(isConfirmed){
				window.location="grocery_deleteOK.do?code="+target;
			}
		}
	</script>
</body>
</html>
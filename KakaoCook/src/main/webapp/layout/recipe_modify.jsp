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
<link rel="stylesheet" href="../style/recipe_insert.css">
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
					<li><a href="#">나의 냉장고</a></li>
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
					<a href="logout.do?url=recipe_main.do"><input type="button" value="로그아웃" style="cursor: pointer;"></a>
					<a href="member_modify.do"><input type="button" value="정보수정" style="cursor: pointer;"></a>
				</c:if>
			</div>
		</header>
		<section id="main_section">
			<article class="page">
				<div id="page1_contents">
					<div id="recipe_insert_section">
						<div id="recipe_insert_section_header">
							<span><b>레시피</b> 수정하기</span>
						</div>
						<form action="recipe_modifyOK.do" method="post" enctype="multipart/form-data">
							<table>
								<tr>
									<td>요리 이름</td>
									<td><input type="text" name="name" value="${rDto.name }"></td>
								</tr>
								<tr>
									<td>제목</td>
									<td><input type="text" name="title" value="${rDto.title }"></td>
								</tr>
								<tr>
									<td>분류</td>
									<td>
										<select name="recipe_type">
											<option value="kor_foods" <c:if test="${rDto.recipe_type == 'kor_foods'}">selected</c:if>>한식</option>
											<option value="cha_foods" <c:if test="${rDto.recipe_type == 'cha_foods'}">selected</c:if>>중식</option>
											<option value="jap_foods" <c:if test="${rDto.recipe_type == 'jap_foods'}">selected</c:if>>일식</option>
											<option value="etc_foods" <c:if test="${rDto.recipe_type == 'etc_foods'}">selected</c:if>>양식</option>
										</select>
										<select name="food_type">
											<option value="main_dish" <c:if test="${rDto.food_type == 'main_dish'}">selected</c:if>>메인요리</option>
											<option value="soup_dish" <c:if test="${rDto.food_type == 'soup_dish'}">selected</c:if>>국물</option>
											<option value="noodle_dish" <c:if test="${rDto.food_type == 'noodle_dish'}">selected</c:if>>면</option>
											<option value="side_dish" <c:if test="${rDto.food_type == 'side_dish'}">selected</c:if>>반찬</option>
											<option value="salad_dish" <c:if test="${rDto.food_type == 'salad_dish'}">selected</c:if>>샐러드</option>
											<option value="dessert_dish" <c:if test="${rDto.food_type == 'dessert_dish'}">selected</c:if>>디저트</option>
											<option value="etc_food_dish" <c:if test="${rDto.food_type == 'etc_food_dish'}">selected</c:if>>기타</option>
										</select>
										<select name="ingredient_type">
											<option value="meat_dish" <c:if test="${rDto.ingredient_type == 'meat_dish'}">selected</c:if>>육류</option>
											<option value="seafood_dish" <c:if test="${rDto.ingredient_type == 'seafood_dish'}">selected</c:if>>해물류</option>
											<option value="vegetable_dish" <c:if test="${rDto.ingredient_type == 'vegetable_dish'}">selected</c:if>>채소류</option>
											<option value="processed_dish" <c:if test="${rDto.ingredient_type == 'processed_dish'}">selected</c:if>>가공식품류</option>
											<option value="grain_dish" <c:if test="${rDto.ingredient_type == 'grain_dish'}">selected</c:if>>곡류</option>
											<option value="etc_ingredient_dish" <c:if test="${rDto.ingredient_type == 'etc_ingredient_dish'}">selected</c:if>>기타</option>
										</select>
										<select name="method_type">
											<option value="roast_dish" <c:if test="${rDto.method_type == 'roast_dish'}">selected</c:if>>볶음</option>
											<option value="grill_dish" <c:if test="${rDto.method_type == 'grill_dish'}">selected</c:if>>구이</option>
											<option value="boil_dish" <c:if test="${rDto.method_type == 'boil_dish'}">selected</c:if>>끓이기</option>
											<option value="boil_down_dish" <c:if test="${rDto.method_type == 'boil_down_dish'}">selected</c:if>>조림</option>
											<option value="steam_dish" <c:if test="${rDto.method_type == 'steam_dish'}">selected</c:if>>찜</option>
											<option value="etc_method_dish" <c:if test="${rDto.method_type == 'etc_method_dish'}">selected</c:if>>기타</option>
										</select>
									</td>
								</tr>
								<tr><td><hr></td><td class="line"><hr></td></tr>
								<tr>
									<td class="dif_td"><span>음식 사진</span></td>
									<td>
										<label for="input_thumbnail"><div class="preview thumbnail_wrap"><img src="${rDto.recipe_thumbnail_image }"></div></label>
										<input type="file" class="inputFile" id="input_thumbnail" name="recipe_thumbnail_image" value="${rDto.recipe_thumbnail_image }" accept="image/jpg, image/jpeg,image/gif,image/png">
									</td>
								</tr>
								<tr><td><hr></td><td class="line"><hr></td></tr>
								<tr>
									<td class="dif_td"><span>재료</span></td>
									<td id="ingrdient_input_section">
										<ul id="ingredientStep">
											<span>최대 10개까지 등록 가능합니다.</span>
											<c:forEach var="i" begin="0" end="${numOfIngredient-1 }">
												<li id="iStep${i+1 }">
													<div class="ingredient_wrap">
														<label for="ingredient_name">재료이름${i+1 }</label><input type="text" name="ingredient_name" value='<c:forEach var="list1" items="${ingredient_name_list[i] }">${list1 }</c:forEach>'>
														<label for="ingredient_amount">재료량${i+1 }</label><input type="text" name="ingredient_amount" value='<c:forEach var="list2" items="${ingredient_amount_list[i] }">${list2 }</c:forEach>'>
														<c:if test="${i != 0 }">
															<a href="javascript:IngredientdeleteBtn(${i+1 })"><i class="fas fa-minus-circle"></i></a>
														</c:if>
													</div>
												</li>
											</c:forEach>
										</ul>
										<div class="add_button"><a href="#" id="addIngredientBtn"><i class="fas fa-plus-circle"></i>&nbsp;재료 추가하기</a></div>
									</td>
								</tr>
								<tr><td><hr></td><td class="line"><hr></td></tr>
								<tr>
									<td class="dif_td"><span>레시피 작성</span></td>
									<td>
										<ul id="recipeStep">
											<span>최대 10개까지 등록 가능합니다.</span>
											<c:forEach var="i" begin="0" end="${numOfRecipeStep-1 }">
												<li id="rStep${i+1 }">
													<div class="dif_td">
														<p>${i+1 }</p>
														<textarea name="recipe_description" maxlength="200" style="resize:none"><c:forEach var="list1" items="${recipe_description_list[i] }">${list1 }</c:forEach></textarea>
														<label for="recipe_image${i+1 }"><div class="preview"><img class="inputImg" src="<c:forEach var="list2" items="${imgPath_list[i] }">${list2 }</c:forEach>"></div></label>
														<input type="file" class="inputFile" id="recipe_image${i+1 }" name="recipe_image${i+1 }" value="<c:forEach var="list2" items="${imgPath_list[i] }">${list2 }</c:forEach>" accept="image/jpg, image/jpeg,image/gif,image/png">
														<c:if test="${i != 0 }">
															<a href="javascript:RecipedeleteBtn(${i+1 })"><i class="fas fa-minus-circle"></i></a>
														</c:if>
													</div>
												</li>
											</c:forEach>
										</ul>
										<div class="add_button"><a href="#" id="addRecipeBtn"><i class="fas fa-plus-circle"></i>&nbsp;추가하기</a></div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="button_section">
											<input type="submit" value="확인" id="submit_button">
											<input type="button" value="취소" id="cancel_button" onclick="window.location='recipe_main.do'">
										</div>
									</td>
								</tr>
							</table>
							<input type="hidden" name="no" value="${rDto.no }">
							<input type="hidden" name="oldThumb" value="${rDto.recipe_thumbnail_image }">
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
	    let ingredientStep = document.getElementById("ingredientStep");
	    let ingredientArr = [];
	    let cnt2 = ${numOfIngredient-1 };
		
	    $("#addIngredientBtn").click(function(){
		    let text = '';
	    	cnt2++;
	      
   			if(cnt2 < 10) {
				text+= '<li id="iStep'+(cnt2+1)+'">';
				text+= '<div class="ingredient_wrap">';
				text+= '<label for="ingredient_name'+ (cnt2+1) +'">재료이름'+ (cnt2+1) +'</label><input type="text" name="ingredient_name" id="ingredient_name'+ (cnt2+1) +'">';
				text+= '<label for="ingredient_amount'+ (cnt2+1) +'">재료량'+ (cnt2+1) +'</label><input type="text" name="ingredient_amount" id="ingredient_amount'+ (cnt2+1) +'">';
				text+= '<a href="javascript:IngredientdeleteBtn('+ (cnt2+1) +')"><i class="fas fa-minus-circle"></i></a>';
				text+= '</div>';
				text+= '</li>';
   			} else {
   				alert("최대 10개까지 등록 가능합니다.");
   			}
			$(ingredientStep).append(text);
	    });
 	</script>
	<script>
	    let step = document.getElementById("recipeStep");
	    let recipeStepArr = [];
	    let cnt = ${numOfRecipeStep-1 };
	    
		$("#addRecipeBtn").click(function(){
		    let content = '';
		    cnt++;
		    
		    if(cnt < 10){
			    content+= '<li id="rStep'+(cnt+1)+'">';
			    content+= '<div class="dif_td">';
			    content+= '<p>'+ (cnt+1) +'</p>';
			    content+= '<textarea name="recipe_description" maxlength="100" style="resize:none"></textarea>';
			    content+= '<label for="recipe_image'+ (cnt+1) +'"><div class="preview pre_margin"><img class="inputImg" src="../images/add.png"></div></label>';
			    content+= '<input type="file" class="inputFile" id="recipe_image'+ (cnt+1) +'" name="recipe_image'+ (cnt+1) +'" accept="image/jpg, image/jpeg,image/gif,image/png">';
			    content+= '<a href="javascript:RecipedeleteBtn('+ (cnt+1) +')"><i class="fas fa-minus-circle"></i></a>';
			    content+= '</div>';
			    content+= '</li>';
		    } else {
		   		alert("최대 10개까지 등록 가능합니다.");
		    }
		    $(step).append(content);
		});
 	</script>
 	<script>
		function IngredientdeleteBtn(step){
			$("#ingredientStep [id=iStep" + step + "]").remove();
		}
		function RecipedeleteBtn(step){
			$("#recipeStep [id=rStep" + step + "]").remove();
		}
 	</script>
	<script>
		$(document).on("click", ".inputFile", function(){
			let input = document.getElementsByClassName('inputFile');
			let preview = document.getElementsByClassName('preview');
			
			for(let i=0; i<input.length; i++) {
				input[i].addEventListener('change', function(e){
					let file = e.target.files;
					if(file.length) {
						let reader = new FileReader();
						reader.readAsDataURL(file[0]);
						reader.onload = function(){
							let imgSrc = reader.result;
							preview[i].innerHTML = '<img src="' + imgSrc + '">';
						}
					} else {
						preview[i].innerHTML = '<img src="../images/add.png">';
					}
				})
			}
		});
	</script>
	<script>
		function checkInfo() {		
			let form = document.insert_form;
			
			if(!form.name.value){
		        alert("요리 이름을 입력해주세요.");
		        return false;
		    }
		    if(!form.title.value){
		        alert("레시피 제목을 입력해주세요.");
		        return false;
		    }
		    if(!form.recipe_type.value){
		        alert("음식 분류를 선택해주세요.");
		        return false;
		    }
		    if(!form.food_type.value){
		        alert("요리 종류를 선택해주세요.");
		        return false;
		    }
		    if(!form.ingredient_type.value){
		        alert("재료 타입을 선택해주세요.");
		        return false;
		    }
		    if(!form.method_type.value){
		        alert("요리 방법을 선택해주세요.");
		        return false;
		    }
		    if(!form.recipe_thumbnail_image.value){
		        alert("대표 사진을 등록해주세요.");
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
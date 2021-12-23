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
<link rel="stylesheet" href="../style/idcheckForm.css">
<title>KakaoCook</title>
</head>
<body onload="getValue()">
	<div id="wrap">
		<div id="input_section">
			<h3>아이디 중복체크</h3>
			<form action="idCheckOK.do" name="checkForm" method="post" onsubmit="return IDCheck(this)">
				<input type="text" id="inputID" name="inputID" maxlength="15" autofocus required>
				<input type="submit" value="중복확인">
			</form>
		</div>
		<div id="description">
			<div id="description_check_msg">
				<c:if test="${checkedID != null }">
					<br>
					<span id="msg1">사용 가능한 아이디입니다.</span>
					<input type="button" value="적용" onclick="apply('${checkedID }')"><br>
				</c:if>
				<c:if test="${usedID != null }">
					<br>
					<span id="msg2">존재하는 아이디입니다.</span>
					<input type="button" value="창닫기" onclick="window.close()"><br>
				</c:if>
			</div>
			<br>
			<p>•&nbsp;아이디는 5자 이상, 15자 이하이어야 합니다.</p>
			<p>•&nbsp;아이디는 특수문자(#*~^)를 포함할 수 없습니다.</p>
		</div>
		<script>
			function apply(checkedID){
				opener.document.sign_up_form.id.value = checkedID;
				opener.document.sign_up_form.idDuplication.value = "idCheck";
				window.close();
			}
		</script>
		<script>
			function getValue(){
				document.getElementById("inputID").value = opener.document.sign_up_form.id.value;
			}
			function IDCheck(f){
				const userID = f.inputID.value;
			    let checkFlag = true;
			    
			    opener.document.sign_up_form.id.value = f.inputID.value;
			    
			    for(let i=0;i<userID.length;i++) {
			        let temp = userID.charCodeAt(i);
			        if((65<=temp && temp<=90 || 97<=temp && temp<=122 || 48<=temp && temp<=57) && userID.length>=5 && userID.length<=15 ) {
			            console.log(temp);
			        } else {
			        	checkFlag = false;
			            break;
			        }
			    }
			    if(checkFlag == false) {
			        alert('사용하실 수 없는 아이디입니다.');
			    }
			    return checkFlag;
			}
		</script>
	</div>
</body>
</html>
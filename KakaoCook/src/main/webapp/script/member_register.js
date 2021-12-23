function checkInfo() {
	let pw = document.forms["sign_up_form"]["pw"].value;
	let pwChk = document.forms["sign_up_form"]["pwChk"].value;
	let phoneNumber = document.forms["sign_up_form"]["phoneNumber"].value;
	
	if (pw != pwChk) {
		document.getElementById("checkPw").innerHTML = "비밀번호가 일치하지 않습니다.";
		document.getElementById("checkPw").style.color = "red";
		document.getElementById("pwChk").style.border = "1px solid red";
		return false;
	} else {
		document.getElementById("checkPw").innerHTML = "";
		document.getElementById("pwChk").style.border = "1px solid lightgray";
	}

	if (isNaN(phoneNumber)) {
		document.getElementById("checkNum").innerHTML = "전화번호를 확인해주세요.";
		document.getElementById("checkNum").style.color = "red";
		document.getElementById("phoneNumber").style.border = "1px solid red";
		return false;
	} else {
		document.getElementById("checkNum").innerHTML = "";
		document.getElementById("phoneNumber").style.border = "1px solid lightgray";
	}
	
	let form = document.sign_up_form;
	
	if (form.idDuplication.value != "idCheck") {
		alert("아이디 중복체크를 해주세요.");
		return false;
	}
}


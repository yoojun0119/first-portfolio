function apply(inputID){
	opener.document.sign_up_form.id.value = inputID;
	opener.document.sign_up_form.idDuplication.value = "idCheck";
	window.close();
}
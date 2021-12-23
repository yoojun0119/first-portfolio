let input = document.querySelectorAll('.inputFile');
let preview = document.querySelectorAll('.preview');

for (let i = 0; i < input.length; i++) {
	input[i].addEventListener('change', function(e) {
		let file = e.target.files;
		if (file.length) {
			let reader = new FileReader();
			reader.readAsDataURL(file[0]);
			reader.onload = function() {
				let imgSrc = reader.result;
				preview[i].innerHTML = '<img src="' + imgSrc + '">';
			}
		} else {
			preview[i].innerHTML = '<img src="../images/add.png">';
		}
	})
}

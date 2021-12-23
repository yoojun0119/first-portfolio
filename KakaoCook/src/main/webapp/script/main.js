$(document).ready(function(){
	$('.slider').bxSlider({
		auto: true,
		speed: 800,
		pause: 3000,
		controls: false,
		pager: true,
		autoHover: true,
		slideWidth: 1000,
		touchEnabled: (navigator.maxTouchPoints > 0)
	});
});
$(document).ready(function(){
	$('.slider2').bxSlider({
		controls: true,
		randomStart: true,
		moveSlides: 3,
		minSlides: 1,
		maxSlides: 3,
		slideWidth: 300,
		slideMargin: 20,
		pager: false,
		touchEnabled: (navigator.maxTouchPoints > 0)
	});
});
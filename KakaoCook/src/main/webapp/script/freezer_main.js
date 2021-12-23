$(document).ready(function(){
	$('.slider').bxSlider({
		controls: true,
		randomStart: true,
		moveSlides: 5,
		minSlides: 1,
		maxSlides: 5,
		slideWidth: 150,
		slideMargin: 20,
		pager: false,
		touchEnabled: (navigator.maxTouchPoints > 0)
	});
});
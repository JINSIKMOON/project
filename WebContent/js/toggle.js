jQuery(document).ready(function($){

	$('.wpcmsdev-toggle').each(function(index, element){
		$(this).find('.toggle-content');
	});

	$('.wpcmsdev-toggle .toggle-title a').click(function(event){
		event.preventDefault();

		$(this).toggleClass('active').parent().siblings('.toggle-content').slideToggle('fast');

	});
});

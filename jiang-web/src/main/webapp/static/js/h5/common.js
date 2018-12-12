jQuery(function(){
	jQuery("*.link").on("tap click", function(){
		var href = jQuery(this).attr("href");
		document.location.href = href;
		return false;
	});
});
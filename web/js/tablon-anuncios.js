var background = ["#EFEFEF","#FFF"];
var border = ["","2px solid #D4D4D4"]

$(document).ready(function(){
	$(".asuntoAnuncioLink" ).on("click", function() {
		
		var css = $(this).parent().siblings(".detalleAnuncio:visible").length > 0 ? 0 : 1;
		
		$(this).parents(".anuncioTablon").css({"background-color":background[css],"border":border[css]});
		$(this).parent().siblings(".detalleAnuncio").slideToggle();
	});
});
$(function(){
	$('[rel="tooltip"]').tooltip();

	$('.js-valorCss').maskMoney({decimal: ',' ,thousands: '.', allowZero:false});

	$('.js-statusReceber').on('click', function(event){
		event.preventDefault();
			
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type:'PUT'
			
		}); 
		response.done(function(e){
			var idDispositivo = botaoReceber.data('id');
			$('[data.role=' + idDispositivo +']').html('<span class="label label-success">'+ e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e){
			//console.log(e);
			alert('Erro no recebimento!');
		});
	});
});
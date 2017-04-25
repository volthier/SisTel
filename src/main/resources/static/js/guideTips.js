$(function(){
	$('[rel="tooltip"]').tooltip();
<<<<<<< HEAD
	$('.js-valorCss').maskMoney({decimal: "," , thousands: "." , allowZero:true});
=======
	$('.js-valorCss').maskMoney({decimal: ',' ,thousands: '.', allowZero:false});
>>>>>>> bbe8904dc6588e274c9abbdf067e2380b3efddae
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
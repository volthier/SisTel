function abrirDocumentacaoModal(id){
	
	$.ajax({
		url: "/alocacoes/lista-alocacoes/" + id,
		success: function(data){
			$("#infoDocumentosModalHolder").html(data);
			$("#infoDocumentosModal").modal("show");
		}
	});
}

// BOTÃO RADIO DISPOSITIVOS PATRIMONIO/COMODATO
$(function() {
	  $types = $('.syncTypes');
	  $contacts = $('#contacts');
	  $groups = $('#groups');
	  $types.change(function() {
	    $this = $(this).attr("data-value");
	    if ($this == "types") {
	      $contacts.attr('pattern','[0-9]{0,9}');
	      if ($contacts.val() == "Comodato"){
	      $contacts.attr('value','');
	      }
	      $contacts.removeAttr('hidden','');
	    } else if ($this == "groups") {
	      $contacts.attr('value','Comodato');
	      $contacts.attr('hidden','hidden');
	      $contacts.removeAttr('pattern');
	      
	    }
	  });
	});


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

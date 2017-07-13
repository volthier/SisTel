/*Script de exclusao(Botão)*/
$('#confirmacaoExclusaoModal').on('shown.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
		
	var id = button.data('excludid');
	var item = button.data('exclude');
	var flag = button.data('flag')
	
	var modal = $(this);
	
	var form = modal.find('form');
	var action = form.data('url-consulta');
	
	if(flag == 1){
		action ='/dispositivos';
	if(!action.endsWith('/')){
		action +='/' ;
	}
	form.attr('action', action + id); 
	
	modal.find('.modal-body span').html('Confirma a exclusão do registro<strong> '+ item +' </strong>?' );

	}
	else if(flag == 2){
		action ='/usuarios';
		if(!action.endsWith('/')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão do registro<strong> '+ item +' </strong>?' );
				
	}
	
	else if(flag == 3){
		action ='/chips';
		if(!action.endsWith('')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão do registro<strong> '+ item +' </strong>?' );
				
	}
	else if(flag == 4){
		action ='/linhas';
		if(!action.endsWith('/')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão do registro<strong> '+ item +' </strong>?' );
				
	}
	else if(flag == 5){
		action ='/alocacoes/disponibilizar';
		if(!action.endsWith('/')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão do registro de vinculo de serviço telecom a <strong> '+ item +' </strong>?' );
				
	}
	else if(flag == 6){
		action ='/categorias';
		if(!action.endsWith('/')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão da categoria de serviço: <strong> '+ item +' </strong>?' );
				
	}
	else if(flag == 7){
		action ='/limites-atesto';
		if(!action.endsWith('/')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão do limite descrito à: <strong> '+ item +' </strong>?' );
				
	}
	
	});


/*Script do Menu (Anchor)*/
$('.menu-anchor').on('click touchstart', function(e){
	$('html').toggleClass('menu-active');
  	e.preventDefault();
});
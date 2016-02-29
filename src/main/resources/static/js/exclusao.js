$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var id = button.data('excludid');
	var item = button.data('exclude');
	var flag = button.data('flag')
	
	var modal = $(this);
	
	var form = modal.find('form');
	var action = form.data('url-inicio');
	
	if(flag == 1){
		action ='/dispositivos';
	if(!action.endsWith('/')){
		action +='/' ;
	}
	form.attr('action', action + id); 
	
	modal.find('.modal-body span').html('Confirma a exclusão do registro<strong> '+ item +' </strong>?' );

	}
	else if(flag == 2){
		action ='/pessoas';
		if(!action.endsWith('/')){
			action +='/' ;
		}
		form.attr('action', action + id); 
		
		modal.find('.modal-body span').html('Confirma a exclusão do registro<strong> '+ item +' </strong>?' );
				
	}
	
	
	
	});
$('#confirmacaoExclusaoModal').on('shown.bs.modal', function(event){
	
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
	
	else if(flag == 3){
		action ='/chips';
		if(!action.endsWith('/')){
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
	
	});

$(function () {
    $('#nome').autocomplete({
        serviceUrl: '/inicio/autocomplete',
        paramName: "searchfield",
        params: {"id": "persons"},
        minChars: 1,
        maxHeight: 'none',
        transformResult: function (response) {
            // overwrite, maybe needed for reading additional information in response
            return $.parseJSON(response);
        }
    })
});

//Menu Anchor
$('.menu-anchor').on('click touchstart', function(e){
	$('html').toggleClass('menu-active');
  	e.preventDefault();
});
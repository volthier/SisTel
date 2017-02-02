/*Script caledario input*/
/*$('.input-group.date').datepicker({
    format: "dd/mm/yyyy HH:mm:ss",
    todayBtn: true,
    clearBtn: true,
    language: "pt-BR",
    daysOfWeekDisabled: "0",
    daysOfWeekHighlighted: "0",
    autoclose: true,
    todayHighlight: true
});*/

/* TESTE CALENDARIO*/
  
	  $('#datetimepicker').datetimepicker({
		  format: 'yyyy/mm/dd hh:ii',
	    
	    todayBtn: true,
	    clearBtn: true,
	    language: "pt-BR",
	    daysOfWeekDisabled: "0",
	    daysOfWeekHighlighted: "0",
	    autoclose: true,
	    todayHighlight: true
	  });

/*Script de exclusao(Botão)*/
$('#confirmacaoExclusaoModal').on('shown.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
		
	var id = button.data('excludid');
	var item = button.data('exclude');
	var flag = button.data('flag')
	
	var modal = $(this);
	alert(id);
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


/*Script do Menu (Anchor)*/
$('.menu-anchor').on('click touchstart', function(e){
	$('html').toggleClass('menu-active');
  	e.preventDefault();
});
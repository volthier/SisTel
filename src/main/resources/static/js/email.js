/*Script de envio de email aprovação do processo*/
$('#confirmacaoEmailModal').on(
		'shown.bs.modal',
		function(event) {

			var button = $(event.relatedTarget);
			var id = button.data('emailid');
			var item = button.data('processo');
			var modal = $(this);

			var form = modal.find('form');
			var action = form.data('url-pendencia');

			action = '/email/';

			form.attr('action', action + id);
			
			modal.find('.modal-body span').html(
					'Gerar processo nº ' + item
							+ ' e enviar e-mail? </strong>?');
			
			var redirect_url = "/pendencia"
			$.ajax({ 
			    url: "/email",    
			    type:"POST", 
			    contentType: "application/json; charset=utf-8",
			    data: {id}, //Stringified Json Object
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			    cache: false,    //This will force requested pages not to be cached by the browser          
			    processData:false, //To avoid making query String instead of JSON
			    success: function(resposeJsonObject){
			        // Success Message Handler
			    	alert('WORK WORK');
			    	Console.log('ZugZug');
			    	console.log('zagzag');
			    }
			});
		});
			
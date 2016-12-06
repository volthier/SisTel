$(document).ready(function() {
	
//Requisição Json do tabela Chips	
	$.ajax({
		type: 'GET',
		headers: {
			Accept : "application/json; charset=utf-8",
			"Content-Type": "application/json; charset=utf-8"
		},
	
		url : '//localhost:8080/relatorio/chips' ,
		success : function(result){
			google.charts.load('current',{'packages' : ['corechart']
			});
			google.charts.setOnLoadCallback(function(){
				drawChart(result);
			});
		}
	});
	
//Função de desenho das informações requisitadas para objetos Js.
function drawChart(result){
		
		var data = new google.visualization.DataTable();
		
		data.addColumn('string','numeroSerieChip');
		data.addColumn('number','idChip');
		
		var dataArray = [];
		$.each(result, function(i,obj){
			dataArray.push([obj.numeroSerieChip, obj.idChip]);
		});
		
		data.addRows(dataArray);
		
		var piechart_options = {
				title : 'Pie Chart: How Much Products Sold By Last Night',
				width: 400,
				height: 300
		};
		
		var piechart = new google.visualization.PieChart(document
				.getElementById('piechart_div'));
		piechart.draw(data, piechart_options);
		
		var barchart_options = {
				title : 'Bar Chart: How Much Products Sold By Last Night',
				width: 400,
				height: 300,
				legend: 'none'
		};
		
		var barchart = new google.visualization.BarChart(document
				.getElementById('barchart_div'));
		barchart.draw(data, barchart_options);	
	}
} /*+

function(){
	//Requisição Json do tabela Chips	
	$.ajax({
		type: 'GET',
		headers: {
			Accept : "application/json; charset=utf-8",
			"Content-Type": "application/json; charset=utf-8"
		},
	
		url : '//localhost:8080/relatorio/usuarios' ,
		success : function(result){
			google.charts.load('current',{'packages' : ['corechart']
			});
			google.charts.setOnLoadCallback(function(){
				drawChart(result);
			});
		}
	});
	
	//Função de desenho das informações requisitadas para objetos Js.
	function drawChart(result){
			
			var data = new google.visualization.DataTable();
			
			data.addColumn('string','lotacaoUsuario');
			data.addColumn('number','idChip');
			
			var dataArray = [];
			$.each(result, function(i,obj){
				dataArray.push([obj.lotacaoUsuario, obj.idChip]);
			});

			var dataArray = [];
			$.each(result, function(i,obj){
				dataArray.push([obj.numeroSerieChip, obj.idChip]);
			});
			
			data.addRows(dataArray);
			
			var piechart_options = {
					title : 'Pie Chart: How Much Products Sold By Last Night',
					width: 400,
					height: 300
			};
			
			var piechart = new google.visualization.PieChart(document
					.getElementById('piechart_div'));
			piechart.draw(data, piechart_options);
			
			var barchart_options = {
					title : 'Bar Chart: How Much Products Sold By Last Night',
					width: 400,
					height: 300,
					legend: 'none'
			};
			
			var barchart = new google.visualization.BarChart(document
					.getElementById('barchart_div'));
			barchart.draw(data, barchart_options);	
		}
	}
	};*/


);
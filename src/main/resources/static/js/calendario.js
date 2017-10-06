$('#datetimepicker').datetimepicker({
    sideBySide: true,
	format : 'DD/M/YYYY HH:mm:ss',
	locale : 'pt-br',
	daysOfWeekDisabled : ['0'],
	focusOnShow : true
});

$('#datetimepicker2').datetimepicker({
    sideBySide: true,
	format : 'DD/M/YYYY HH:mm:ss',
	locale : 'pt-br',
	daysOfWeekDisabled : ['0'],
	focusOnShow : true
});

function dataBlock(devolucao) {
	var a = moment.duration(1,'d');
	var minD = moment(devolucao);
	minD.add(a).days();
	
	$('#datetimepicker3').datetimepicker({
        sideBySide: true,
		minDate: minD,
		format : 'DD/M/YYYY HH:mm:ss',
		locale : 'pt-br',
		daysOfWeekDisabled : ['0'],
		focusOnShow : true
	});
	
};
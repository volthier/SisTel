//JS HOME
(function(jQuery) {

	// start: Chart =============
	if(typeof Chart != 'undefined'){
		
	
		Chart.defaults.global.pointHitDetectionRadius = 1;
		Chart.defaults.global.customTooltips = function(tooltip) {

			var tooltipEl = $('#chartjs-tooltip');

			if (!tooltip) {
				tooltipEl.css({
					opacity : 0
				});
				return;
			}

			tooltipEl.removeClass('above below');
			tooltipEl.addClass(tooltip.yAlign);

			var innerHtml = '';
			if (undefined !== tooltip.labels && tooltip.labels.length) {
				for (var i = tooltip.labels.length - 1; i >= 0; i--) {
					innerHtml += [
							'<div class="chartjs-tooltip-section">',
							'   <span class="chartjs-tooltip-key" style="background-color:'
									+ tooltip.legendColors[i].fill + '"></span>',
							'   <span class="chartjs-tooltip-value">'
									+ tooltip.labels[i] + '</span>', '</div>' ]
							.join('');
				}
				tooltipEl.html(innerHtml);
			}

			tooltipEl.css({
				opacity : 1,
				left : tooltip.chart.canvas.offsetLeft + tooltip.x + 'px',
				top : tooltip.chart.canvas.offsetTop + tooltip.y + 'px',
				fontFamily : tooltip.fontFamily,
				fontSize : tooltip.fontSize,
				fontStyle : tooltip.fontStyle
			});
		};
	
	}
	
	if(typeof mask != 'undefined'){
		$('.sp_celphones').mask(SPMaskBehavior, spOptions);
	}
	

/*	VISUALIZAR*/
	$(".mask_visualizar").ready(function() {
		var semmascara = $(".mask_input_off").val();
		$(".mask_input_on").val(semmascara);
	});

/*ARMAZENA*/	
	$(document).ready(function() {
		$(".mask_input_on").change(function() {
			var exp = /[^\w]/g;
			var commascara = $(".mask_input_on").val();
			var semmascara = commascara.replace(exp, "");
			$(".mask_input_off").val(semmascara);

		});
	});

/*APLICA A MÁSCARA NO CADASTRO*/	
	var SPMaskBehavior = function (val) {
		return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		},
		spOptions = {
		onKeyPress: function(val, e, field, options) {
		field.mask(SPMaskBehavior.apply({}, arguments), options);
		}
		};

		$('.mask_celphones').mask(SPMaskBehavior, spOptions);
	
	$('.numTable').focusout(function() {
		var phone, element;
		element = $(this);
		element.unmask();
		phone = element.text().replace(/\D/g, '');
		if (phone.length > 10) {
			element.mask("(99) 99999-9999");
			console.log(phone.length);
		} else {
			console.log(phone.length + 'else');
			element.mask("(99) 9999-9999");
		}
	}).trigger('focusout');

	$('.numInput').focusout(function() {
		var phone, element;
		element = $(this);
		element.unmask();
		phone = element.val().replace(/\D/g, '');
		if (phone.length > 10) {
			element.mask("(99) 99999-9999");
			console.log(phone.length);
		} else {
			console.log(phone.length + 'else');
			element.mask("(99) 9999-9999");
		}
	}).trigger('focusout');

	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100);
	};
	var lineChartData = {
		labels : [ "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago",
				"Set", "Out", "Nov", "Dez" ],
		datasets : [ {
			label : "My First dataset",
			fillColor : "rgba(21,186,103,0.4)",
			strokeColor : "rgba(220,220,220,1)",
			pointColor : "rgba(66,69,67,0.3)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			// ----------GRÁFICO VERDE------------
			data : [ 18, 9, 5, 7, 4.5, 4, 5, 7, 8, 9, 8, 9 ]
		}, {
			label : "My Second dataset",
			fillColor : "rgba(21,113,186,0.5)",
			strokeColor : "rgba(151,187,205,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#fff",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(151,187,205,1)",
			// ----------GRÁFICO AZUL------------
			data : [ 4, 7, 5, 18, 4.5, 4, 5, 4.5, 6, 5.6, 7.5, 7 ]
		} ]
	};

	window.onload = function() {
		if (hasClass = "#telaInicio") {
			
			
			if($(".line-chart").length){
				var ctx2 = $(".line-chart")[0].getContext("2d");
				window.myLine = new Chart(ctx2).Line(lineChartData, {
					responsive : true,
					showTooltips : true,
					multiTooltipTemplate : "<%= value %>",
					maintainAspectRatio : false
				});
				
			}
			
		}
	};

	// end: Chart =============

	$(document).on('click', '.browse', function(){
		  var file = $(this).parent().parent().parent().find('.file');
		  file.trigger('click');
		});
		$(document).on('change', '.file', function(){
		  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
		});
	
	$.fn.ripple = function() {
		$(this)
				.click(
						function(e) {
							
							var $rippler = $(this);
							$rippler.find(".ink").remove();

							$ink = $("<span class='ink'></span>");

							if ($rippler.children("a").first()) {
								$rippler.children("a").first().append($ink);
								console.log("found");
							} else {
								$rippler.append($ink);
							}

							$ink.removeClass("animate");
							if (!$ink.height() && !$ink.width()) {
								var d = Math.max($rippler.outerWidth(),
										$rippler.outerHeight());
								$ink.css({
									height : d,
									width : d
								});
							}

							var x = e.pageX - $rippler.offset().left
									- $ink.width() / 2;
							var y = e.pageY - $rippler.offset().top
									- $ink.height() / 2;
							$ink.css({
								top : y + 'px',
								left : x + 'px'
							}).addClass("animate");
						});
		
		
		
	};

	this.hide = function() {
		$(".tree").hide();
		$(".sub-tree").hide();
	};

	// CONFIG MENUS
	this.treeMenu = function() {

		$('.tree-toggle').click(
				function(e) {
					/*e.preventDefault();*/
					var $this = $(this).parent().children('ul.tree');
					$(".tree").not($this).slideUp(600);
					$this.toggle(700);

					$(".tree").not($this).parent("li").find(
							".tree-toggle .right-arrow").removeClass(
							"fa-angle-down").addClass("fa-angle-right");
					$this.parent("li").find(".tree-toggle .right-arrow")
							.toggleClass("fa-angle-right fa-angle-down");
				});

		$('.sub-tree-toggle').click(
				function(e) {
					/*e.preventDefault();*/
					var $this = $(this).parent().children('ul.sub-tree');
					$(".sub-tree").not($this).slideUp(600);
					$this.toggle(700);

					$(".sub-tree").not($this).parent("li").find(
							".sub-tree-toggle .right-arrow").removeClass(
							"fa-angle-down").addClass("fa-angle-right");
					$this.parent("li").find(".sub-tree-toggle .right-arrow")
							.toggleClass("fa-angle-right fa-angle-down");
				});
	};

	this.leftMenu = function() {

		$('.opener-left-menu').on('click', function() {
			$(".line-chart").width("100%");
			$(".mejs-video").height("auto").width("100%");
			if ($('#right-menu').is(":visible")) {
				$('#right-menu').animate({
					'width' : '0px'
				}, 'slow', function() {
					$('#right-menu').hide();
				});
			}
			if ($('#left-menu .sub-left-menu').is(':visible')) {
				$('#content').animate({
					'padding-left' : '0px'
				}, 'slow');
				$('#left-menu .sub-left-menu').animate({
					'width' : '0px'
				}, 'slow', function() {
					$('.overlay').show();
					$('.opener-left-menu').removeClass('is-open');
					$('.opener-left-menu').addClass('is-closed');
					$('#left-menu .sub-left-menu').hide();
				});

			} else {
				$('#left-menu .sub-left-menu').show();
				$('#left-menu .sub-left-menu').animate({
					'width' : '230px'
				}, 'slow');
				$('#content').animate({
					'padding-left' : '230px',
					'padding-right' : '0px'
				}, 'slow');
				$('.overlay').hide();
				$('.opener-left-menu').removeClass('is-closed');
				$('.opener-left-menu').addClass('is-open');
			}
		});
	};

	this.userList = function() {

		$(".user-list ul").niceScroll({
			touchbehavior : true,
			cursorcolor : "#FF00FF",
			cursoropacitymax : 0.6,
			cursorwidth : 24,
			usetransition : true,
			hwacceleration : true,
			autohidemode : "hidden"
		});

	};

	this.rightMenu = function() {
		$('.opener-right-menu').on('click', function() {
			userList();
			$(".user").niceScroll();
			$(".user ul li").on('click', function() {
				$(".user-list ul").getNiceScroll().remove();
				$(".user").hide();
				$(".chatbox").show(1000);
				userList();
			});

			$(".close-chat").on("click", function() {
				$(".user").show();
				$(".chatbox").hide(1000);
			});

			$(".line-chart").width("100%");

			if ($('#left-menu .sub-left-menu').is(':visible')) {
				$('#left-menu .sub-left-menu').animate({
					'width' : '0px'
				}, 'slow', function() {
					$('#left-menu .sub-left-menu').hide();
					$('.overlay').show();
					$('.opener-left-menu').removeClass('is-open');
					$('.opener-left-menu').addClass('is-closed');
				});

				$('#content').animate({
					'padding-left' : '0px'
				}, 'slow');
			}

			if ($('#right-menu').is(':visible')) {
				$('#right-menu').animate({
					'width' : '0px'
				}, 'slow', function() {
					$('#right-menu').hide();
				});
				$('#content').animate({
					'padding-right' : '0px'
				}, 'slow');
			} else {
				$('#right-menu').show();
				$('#right-menu').animate({
					'width' : '230px'
				}, 'slow');
				$('#content').animate({
					'padding-right' : '230px'
				}, 'slow');
			}
		});
	};
	
	
	$('.carousel-thumb').on(
			'slid.bs.carousel',
			function() {
				if ($(this).find($(".item")).is(".active")) {
					var Current = $(this).find($(".item.active")).attr(
							"data-slide");
					$(".carousel-thumb-img li img").removeClass(
							"animated rubberBand");
					$(".carousel-thumb-img li").removeClass("active");

					$($(".carousel-thumb-img").children()[Current]).addClass(
							"active");
					$($(".carousel-thumb-img li").children()[Current])
							.addClass("animated rubberBand");
				}
			});

	$(".carousel-thumb-img li").on("click", function() {
		$(".carousel-thumb-img li img").removeClass("animated rubberBand");
		$(".carousel-thumb-img li").removeClass("active");
		$(this).addClass("active");
	});

	$("#mimin-mobile-menu-opener")
			.on(
					"click",
					function(e) {

						$("#mimin-mobile").toggleClass("reverse");
						var rippler = $("#mimin-mobile");
						if (!rippler.hasClass("reverse")) {
							if (rippler.find(".ink").length == 0) {
								rippler.append("<div class='ink'></div>");
							}
							var ink = rippler.find(".ink");
							ink.removeClass("animate");
							if (!ink.height() && !ink.width()) {
								var d = Math.max(rippler.outerWidth(), rippler
										.outerHeight());
								ink.css({
									height : d,
									width : d
								});

							}
							var x = e.pageX - rippler.offset().left
									- ink.width() / 2;
							var y = e.pageY - rippler.offset().top
									- ink.height() / 2;
							ink.css({
								top : y + 'px',
								left : x + 'px',
							}).addClass("animate");

							rippler.css({
								'z-index' : 9999
							});
							rippler.animate({
								backgroundColor : "#0676a7",
								width : '100%'
							}, 750);

							$("#mimin-mobile .ink")
									.on(
											"animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
											function(e) {
												$(".sub-mimin-mobile-menu-list")
														.show();
												$(
														"#mimin-mobile-menu-opener span")
														.removeClass("fa-bars")
														.addClass("fa-close")
														.css({
															"font-size" : "2em"
														});
											});
						} else {

							if (rippler.find(".ink").length == 0) {
								rippler.append("<div class='ink'></div>");
							}
							var ink = rippler.find(".ink");
							ink.removeClass("animate");
							if (!ink.height() && !ink.width()) {
								var d = Math.max(rippler.outerWidth(), rippler
										.outerHeight());
								ink.css({
									height : d,
									width : d
								});

							}
							var x = e.pageX - rippler.offset().left
									- ink.width() / 2;
							var y = e.pageY - rippler.offset().top
									- ink.height() / 2;
							ink.css({
								top : y + 'px',
								left : x + 'px',
							}).addClass("animate");
							rippler.animate({
								backgroundColor : "transparent",
								'z-index' : '-1'
							}, 750);

							$("#mimin-mobile .ink")
									.on(
											"animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
											function(e) {
												$(
														"#mimin-mobile-menu-opener span")
														.removeClass("fa-close")
														.addClass("fa-bars")
														.css({
															"font-size" : "1em"
														});
												$(".sub-mimin-mobile-menu-list")
														.hide();
											});
						}
					});

	$(".form-text")
			.on(
					"click",
					function() {
						$(this).before("<div class='ripple-form'></div>");
						$(".ripple-form")
								.on(
										"animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
										function(e) {
											// do something here
											$(this).remove();
										});
					});

	$('.mail-wrapper').find('.mail-left').css('height',
			$('.mail-wrapper').innerHeight());
	$("#left-menu ul li a").ripple();
	$(".ripple div").ripple();
	
	if(typeof niceScroll != 'undefined'){
		$("#left-menu .sub-left-menu").niceScroll();
		$(".sub-mimin-mobile-menu-list").niceScroll({
			touchbehavior : true,
			cursorcolor : "#FF00FF",
			cursoropacitymax : 0.6,
			cursorwidth : 24,
			usetransition : true,
			hwacceleration : true,
			autohidemode : "hidden"
		});
	}
	
	
	
	

	$("body").tooltip({
		selector : '[data-toggle=tooltip]'
	});
	leftMenu();
	rightMenu();
	treeMenu();
	hide();

})(jQuery);


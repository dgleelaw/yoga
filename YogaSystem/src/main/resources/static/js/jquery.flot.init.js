//edited from eon Dashboard
! function($) {
	"use strict";

	var FlotChart = function() {
		this.$body = $("body")
		this.$realData = []
	};


	FlotChart.prototype.createPlotGraph = function(selector, data1, data2, labels, colors, borderColor, bgColor) {
		//shows tooltip
		function showTooltip(x, y, contents) {
			$('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css({
				position : 'absolute',
				top : y + 5,
				left : x + 5
			}).appendTo("body").fadeIn(200);
		}


		$.plot($(selector), [{
			data : data1,
			label : labels[0],
			color : colors[0]
		}, {
			data : data2,
			label : labels[1],
			color : colors[1]
		}], {
			series : {
				lines : {
					show : true,
					fill : true,
					lineWidth : 2,
					fillColor : {
						colors : [{
							opacity : 0.4
						}, {
							opacity : 0.4
						}]
					}
				},
				points : {
					show : false
				},
				shadowSize : 0
			},

			grid : {
				hoverable : true,
				clickable : true,
				borderColor : borderColor,
				tickColor : "#f9f9f9",
				borderWidth : 1,
				labelMargin : 10,
				backgroundColor : bgColor
			},
			legend : {
				position : "ne",
				margin : [0, -24],
				noColumns : 0,
				labelBoxBorderColor : null,
				labelFormatter : function(label, series) {
					// just add some space to labes
					return '' + label + '&nbsp;&nbsp;';
				},
				width : 30,
				height : 2
			},
			yaxis : {
				tickColor : '#f5f5f5',
				font : {
					color : '#bdbdbd'
				}
			},
			xaxis : {
				tickColor : '#f5f5f5',
				font : {
					color : '#bdbdbd'
				}
			},
			tooltip : true,
			tooltipOpts : {
				content : '%s: Value of %x is %y',
				shifts : {
					x : -60,
					y : 25
				},
				defaultTheme : false
			}
		});
	},
	//end plot graph

	//creates Pie Chart
	FlotChart.prototype.createPieGraph = function(selector, labels, datas, colors) {
		var data = [{
			label : labels[0],
			data : datas[0]
		}, {
			label : labels[1],
			data : datas[1]
		}, {
			label : labels[2],
			data : datas[2]
		}];
		var options = {
			series : {
				pie : {
					show : true
				}
			},
			legend : {
				show : true
			},
			grid : {
				hoverable : true,
				clickable : true
			},
			colors : colors,
			tooltip : true,
			tooltipOpts : {
				content : "%s, %p.0%"
			}
		};

		$.plot($(selector), data, options);
	},

	//returns some random data
	FlotChart.prototype.randomData = function() {
		var totalPoints = 300;
		if (this.$realData.length > 0)
			this.$realData = this.$realData.slice(1);

		// Do a random walk
		while (this.$realData.length < totalPoints) {

			var prev = this.$realData.length > 0 ? this.$realData[this.$realData.length - 1] : 50,
			    y = prev + Math.random() * 10 - 5;

			if (y < 0) {
				y = 0;
			} else if (y > 100) {
				y = 100;
			}

			this.$realData.push(y);
		}

		// Zip the generated y values with the x values
		var res = [];
		for (var i = 0; i < this.$realData.length; ++i) {
			res.push([i, this.$realData[i]])
		}

		return res;
	}, FlotChart.prototype.createRealTimeGraph = function(selector, data, colors) {
		var plot = $.plot(selector, [data], {
			colors : colors,
			series : {
				grow : {
					active : false
				}, //disable auto grow
				shadowSize : 0, // drawing is faster without shadows
				lines : {
					show : true,
					fill : false,
					lineWidth : 2,
					steps : false
				}
			},
			grid : {
				show : true,
				aboveData : false,
				color : '#dcdcdc',
				labelMargin : 15,
				axisMargin : 0,
				borderWidth : 0,
				borderColor : null,
				minBorderMargin : 5,
				clickable : true,
				hoverable : true,
				autoHighlight : false,
				mouseActiveRadius : 20
			},
			tooltip : true, //activate tooltip
			tooltipOpts : {
				content : "Value is : %y.0" + "%",
				shifts : {
					x : -30,
					y : -50
				}
			},
			yaxis : {
				min : 0,
				max : 100,
				tickColor : '#f5f5f5',
				color : 'rgba(0,0,0,0.1)'
			},
			xaxis : {
				show : false,
				tickColor : '#f5f5f5'
			}
		});

		return plot;
	},
	//creates Donut Chart
	FlotChart.prototype.createDonutGraph = function(selector, labels, datas, colors) {
		var data = [{
			label : labels[0],
			data : datas[0]
		}, {
			label : labels[1],
			data : datas[1]
		}, {
			label : labels[2],
			data : datas[2]
		}, {
			label : labels[3],
			data : datas[3]
		}];
		var options = {
			series : {
				pie : {
					show : true,
					innerRadius : 0.7
				}
			},
			legend : {
				show : true,
				labelFormatter : function(label, series) {
					return '<div style="font-size:14px;">&nbsp;' + label + '</div>'
				},
				labelBoxBorderColor : null,
				margin : 50,
				width : 20,
				padding : 1
			},
			grid : {
				hoverable : true,
				clickable : true
			},
			colors : colors,
			tooltip : true,
			tooltipOpts : {
				content : "%s, %p.0%"
			}
		};

		$.plot($(selector), data, options);
	},
	//creates Combine Chart
	FlotChart.prototype.createCombineGraph = function(selector, ticks, labels, datas) {

		var data = [{
			label : labels[0],
			data : datas[0],
			lines : {
				show : true,
				fill : true
			},
			points : {
				show : true
			}
		}, {
			label : labels[1],
			data : datas[1],
			lines : {
				show : true
			},
			points : {
				show : true
			}
		}, {
			label : labels[2],
			data : datas[2],
			bars : {
				show : true
			}
		}];
		var options = {
			series : {
				shadowSize : 0
			},
			grid : {
				hoverable : true,
				clickable : true,
				tickColor : "#f9f9f9",
				borderWidth : 1,
				borderColor : "#eeeeee"
			},
			colors : ['#e33a84', '#6438c5', "#56f19a"],
			tooltip : true,
			tooltipOpts : {
				defaultTheme : false
			},
			legend : {
				position : "ne",
				margin : [0, -24],
				noColumns : 0,
				labelBoxBorderColor : null,
				labelFormatter : function(label, series) {
					// just add some space to labes
					return '' + label + '&nbsp;&nbsp;';
				},
				width : 30,
				height : 2
			},
			yaxis : {
				tickColor : '#f5f5f5',
				font : {
					color : '#bdbdbd'
				}
			},
			xaxis : {
				ticks: ticks,
				tickColor : '#f5f5f5',
				font : {
					color : '#bdbdbd'
				}
			}
		};

		$.plot($(selector), data, options);
	},

	//initializing various charts and components
	FlotChart.prototype.init = function() {
		//plot graph data
		var uploads = [[0, 3], [1, 9], [2, 4], [3, 8], [4, 5], [5, 9], [6, 6],[7, 8], [8, 7], [9, 12], [10, 9]];
		var downloads = [[0,2], [1, 10], [2, 3], [3, 9], [4, 4], [5, 8], [6, 5],[7, 7], [8, 6], [9, 10], [10, 3]];
		var plabels = ["uploads", "downloads"];
		var pcolors = ['#56f19a', '#ffdb4a'];
		var borderColor = '#f5f5f5';
		var bgColor = '#fff';
		this.createPlotGraph("#website-stats", uploads, downloads, plabels, pcolors, borderColor, bgColor);

		//Pie graph data
		var pielabels = ["Data 1", "Data 2", "Data 3"];
		var datas = [15, 20, 10];
		var colors = ["#ff66b3","#02a8b5", "#fb6d8b"];
		this.createPieGraph("#pie-chart #pie-chart-container", pielabels, datas, colors);

		//real time data representation
		var plot = this.createRealTimeGraph('#flotRealTime', this.randomData(), ['#fb5020']);
		plot.draw();
		var $this = this;
		function updatePlot() {
			plot.setData([$this.randomData()]);
			// Since the axes don't change, we don't need to call plot.setupGrid()
			plot.draw();
			setTimeout(updatePlot, $('html').hasClass('mobile-device') ? 1000 : 1000);
		}

		updatePlot();

		//Donut pie graph data
		var donutlabels = ["Order 1", "Order 2", "Order 3", "Order4"];
		var donutdatas = [25, 30, 20, 40, 60];
		var donutcolors = ['#fb5020', "#57a94f", '#f21f5d', "#ac3ba9"];
		this.createDonutGraph("#donut-chart #donut-chart-container", donutlabels, donutdatas, donutcolors);

		//Combine graph data
		var datamen = [[0, 200], [1, 220], [2, 437], [3, 561], [4, 257], [5, 195], [6, 600], [7, 750], [8, 220], [9, 300], [10, 452], [11, 514], [12, 664], [13, 149], [14, 258], [15, 382], [16, 479], [17, 129], [18, 18], [19, 270], [20, 430], [21, 645], [22, 258], [23, 174]];
		var datawomens = [[0, 511], [1, 430], [2, 347], [3, 271], [4, 167], [5, 605], [6, 510], [7, 460], [8, 330], [9, 210], [10, 162], [11, 724], [12, 674], [13, 550], [14, 468], [15, 392], [16, 289], [17, 139], [18, 608], [19, 500], [20, 400], [21, 305], [22, 268], [23, 284]];
		var datachilderns = [[23, 627], [22, 528], [21, 410], [20, 392], [19, 272], [18, 163], [17, 750], [16, 692], [15, 512], [14, 446], [13, 352], [12, 249], [11, 120], [10, 602], [9, 525], [8, 410], [7, 315], [6, 289], [5, 165], [4, 677], [3, 500], [2, 400], [1, 300], [0, 100]];
		var ticks = [[0, "1994"], [1, ""], [2, "1996"], [3, ""], [4, "1998"], [5, ""], [6, "2002"], [7, ""], [8, "2004"], [9, ""], [10, "2006"], [11, ""], [12, "2008"], [13, ""], [14, "2010"], [15, ""], [16, "2012"], [17, ""], [18, "2014"], [19, ""], [20, "2016"], [21, ""], [22, "2018"], [23, ""]];
		var combinelabels = ["Mens", "Womens", "Childrens"];
		var combinedatas = [datamen, datawomens, datachilderns];

		this.createCombineGraph("#combine-chart #combine-chart-container", ticks, combinelabels, combinedatas);
	},

	//init flotchart
	$.FlotChart = new FlotChart, $.FlotChart.Constructor =
	FlotChart

}(window.jQuery),

//initializing flotchart
function($) {
	"use strict";
	$.FlotChart.init()
}(window.jQuery);

$(document).ready(function() {



	//------------- Ordered bars chart -------------//
	$(function () {
		//some data
		var d1 = [];
		for (var i = 0; i <= 10; i += 1)
			d1.push([i, parseInt(Math.random() * 30)]);

		var d2 = [];
		for (var i = 0; i <= 10; i += 1)
			d2.push([i, parseInt(Math.random() * 30)]);

		var d3 = [];
		for (var i = 0; i <= 10; i += 1)
			d3.push([i, parseInt(Math.random() * 30)]);

		var ds = new Array();

		ds.push({
			label: "Apple",
			data: d1,
			bars: {
				order: 3
			}
		});
		ds.push({
			label: "Orange",
			data: d2,
			bars: {
				order: 2
			}
		});
		ds.push({
			label: "Banana",
			data: d3,
			bars: {
				order: 1
			}
		});
		ds.push({
			label: "Grape",
			data: d3,
			bars: {
				order: 4
			}
		});
		var stack = 0,
			bars = false,
			lines = false,
			steps = false;

		var options = {
			bars: {
				show: true,
				barWidth: 0.2,
				fill: 1
			},
			grid: {
				show: true,
				aboveData: false,
				labelMargin: 5,
				axisMargin: 0,
				borderWidth: 1,
				minBorderMargin: 5,
				clickable: true,
				hoverable: true,
				autoHighlight: false,
				mouseActiveRadius: 20,
				borderColor: '#f5f5f5'
			},
			series: {
				stack: stack
			},
			legend: {
				position: "ne",
				margin: [0, -24],
				noColumns: 0,
				labelBoxBorderColor: null,
				labelFormatter: function (label, series) {
					// just add some space to labes
					return '' + label + '&nbsp;&nbsp;';
				},
				width: 30,
				height: 2
			},
			yaxis: {
				tickColor: '#f5f5f5',
				font: {
					color: '#bdbdbd'
				}
			},
			xaxis: {
				tickColor: '#f5f5f5',
				font: {
					color: '#bdbdbd'
				}
			},
			colors: ['#e33a84', "#4f94f4", '#ac3ba9',  "#6438c5"],
			tooltip: true, //activate tooltip
			tooltipOpts: {
				content: "%s : %y.0",
				shifts: {
					x: -30,
					y: -50
				}
			}
		};

		$.plot($("#ordered-bars-chart"), ds, options);
	});
});

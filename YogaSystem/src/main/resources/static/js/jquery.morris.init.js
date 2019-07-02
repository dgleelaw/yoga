


!function($) {
    "use strict";

    var MorrisCharts = function() {};

    //creates line chart
    MorrisCharts.prototype.createLineChart = function(element, data, xkey, ykeys, labels, opacity, Pfillcolor, Pstockcolor, lineColors) {
        Morris.Line({
          element: element,
          data: data,
          xkey: xkey,
          ykeys: ykeys,
          labels: labels,
          fillOpacity: opacity,
          pointFillColors: Pfillcolor,
          pointStrokeColors: Pstockcolor,
          behaveLikeLine: true,
          gridLineColor: '#eef0f2',
          hideHover: 'auto',
          lineWidth: '3px',
          pointSize: 0,
          preUnits: ' ',
          resize: true, //defaulted to true
          lineColors: lineColors
        });
    },
    //creates area chart
    MorrisCharts.prototype.createAreaChart = function(element, pointSize, lineWidth, data, xkey, ykeys, labels, lineColors) {
        Morris.Area({
            element: element,
            pointSize: 0,
            lineWidth: 0,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            hideHover: 'auto',
            resize: true,
            gridLineColor: '#eef0f2',
            lineColors: lineColors
        });
    },
    //creates area chart with dotted
    MorrisCharts.prototype.createAreaChartDotted = function(element, pointSize, lineWidth, data, xkey, ykeys, labels, Pfillcolor, Pstockcolor, lineColors) {
        Morris.Area({
            element: element,
            pointSize: 3,
            lineWidth: 1,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            hideHover: 'auto',
            pointFillColors: Pfillcolor,
            pointStrokeColors: Pstockcolor,
            resize: true,
            gridLineColor: '#eef0f2',
            lineColors: lineColors
        });
    },
    //creates Bar chart
    MorrisCharts.prototype.createBarChart  = function(element, data, xkey, ykeys, labels, lineColors) {
        Morris.Bar({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            hideHover: 'auto',
            resize: true, //defaulted to true
            gridLineColor: '#eeeeee',
            barSizeRatio: 0.4,
            barColors: lineColors
        });
    },
    //creates Stacked chart
    MorrisCharts.prototype.createStackedChart  = function(element, data, xkey, ykeys, labels, lineColors) {
        Morris.Bar({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            stacked: true,
            labels: labels,
            hideHover: 'auto',
            resize: true, //defaulted to true
            gridLineColor: '#eeeeee',
            barColors: lineColors
        });
    },
    //creates Donut chart
    MorrisCharts.prototype.createDonutChart = function(element, data, colors) {
        Morris.Donut({
            element: element,
            data: data,
            resize: true, //defaulted to true
            colors: colors
        });
    },
    MorrisCharts.prototype.init = function() {

        //create line chart
        var $data  = [
            { y: '2011', a: 60, b: 10 },
            { y: '2012', a: 55, b: 60 },
            { y: '2013', a: 40, b: 90 },
            { y: '2014', a: 60, b: 70 },
            { y: '2015', a: 55, b: 20 },
            { y: '2016', a: 60, b: 50 },
            { y: '2017', a: 65, b: 70 },
            { y: '2018', a: 90, b: 80 }
          ];
        this.createLineChart('morris-line-example', $data, 'y', ['a', 'b'], ['Sales A', 'Sales B'],['0.1'],['#ffffff'],['#999999'], ['#f21f5d', '#57a94f']);

        //creating area chart
        var $areaData = [
            { y: '2012', a: 20, b: 30 },
            { y: '2013', a: 85,  b: 75 },
            { y: '2014', a: 60,  b: 50 },
            { y: '2015', a: 85,  b: 75 },
            { y: '2016', a: 60,  b: 50 },
            { y: '2017', a: 85,  b: 95 },
            { y: '2018', a: 100, b: 70 }
        ];
        this.createAreaChart('morris-area-example', 0, 0, $areaData, 'y', ['a', 'b'], ['Sales 1', 'Sales 2'], ['#e33a84', "#fb5020"]);

        //creating area chart with dotted
        var $areaDotData = [
            { y: '2011', a: 20, b: 30 },
            { y: '2012', a: 85,  b: 75 },
            { y: '2013', a: 60,  b: 50 },
            { y: '2014', a: 85,  b: 75 },
            { y: '2016', a: 60,  b: 50 },
            { y: '2017', a: 85,  b: 75 },
            { y: '2018', a: 100, b: 80 }
        ];
        this.createAreaChartDotted('morris-area-with-dotted', 0, 0, $areaDotData, 'y', ['a', 'b'], ['Mens', 'Womens'],['#ffffff'],['#999999'], ['#f21f5d', "#57a94f"]);

        //creating bar chart
        var $barData  = [
            { y: '2012', a: 110, b: 100 , c: 50 },
            { y: '2013', a: 85,  b: 75 , c: 30 },
            { y: '2014', a: 60,  b: 50 , c: 60 },
            { y: '2015', a: 85,  b: 75 , c: 105 },
            { y: '2016', a: 60,  b: 50 , c: 32 },
            { y: '2017', a: 85,  b: 75 , c: 66 },
            { y: '2018', a: 110, b: 100 , c: 70 }
        ];
        this.createBarChart('morris-bar-example', $barData, 'y', ['a', 'b', 'c'], ['Profit A', 'Profit B', 'Profit C'], ['#105c83', '#e33a84', "#6438c5"]);

        //creating Stacked chart
        var $stckedData  = [
            { y: '2008', a: 55, b: 190 },
            { y: '2009', a: 85,  b: 75 },
            { y: '2010', a: 110, b: 100 },
            { y: '2011', a: 85,  b: 75 },
            { y: '2012', a: 110, b: 100 },
            { y: '2013', a: 85,  b: 75 },
            { y: '2014', a: 60,  b: 50 },
            { y: '2015', a: 85,  b: 75 },
            { y: '2016', a: 60,  b: 50 },
            { y: '2017', a: 85,  b: 75 },
            { y: '2018', a: 110, b: 100 }
        ];
        this.createStackedChart('morris-bar-stacked', $stckedData, 'y', ['a', 'b'], ['Product A', 'Product B'], ['#57a94f', '#f21f5d']);

        //creating donut chart
        var $donutData = [
                {label: "iphone", value: 15},
                {label: "Android", value: 50},
                {label: "Windows", value: 35}
            ];
        this.createDonutChart('morris-donut-example', $donutData, ['#fb5020', '#0da559', "#6438c5"]);
    },
    //init
    $.MorrisCharts = new MorrisCharts, $.MorrisCharts.Constructor = MorrisCharts
}(window.jQuery),

//initializing
function($) {
    "use strict";
    $.MorrisCharts.init();
}(window.jQuery);

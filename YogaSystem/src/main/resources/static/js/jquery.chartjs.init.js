//edited from eon
!function($) {


    var ChartJs = function() {};

    ChartJs.prototype.respChart = function(selector,type,data, options) {
        // get selector by context
        var ctx = selector.get(0).getContext("2d");
        // pointing parent container to make chart js inherit its width
        var container = $(selector).parent();

        // enable resizing matter
        $(window).resize( generateChart );

        // this function produce the responsive Chart JS
        function generateChart(){
            // make chart width fit with its container
            var ww = selector.attr('width', $(container).width() );
            switch(type){
                case 'Line':
                    new Chart(ctx, {type: 'line', data: data, options: options});
                    break;
                case 'Doughnut':
                    new Chart(ctx, {type: 'doughnut', data: data, options: options});
                    break;
                case 'Pie':
                    new Chart(ctx, {type: 'pie', data: data, options: options});
                    break;
                case 'Bar':
                    new Chart(ctx, {type: 'bar', data: data, options: options});
                    break;
                case 'Radar':
                    new Chart(ctx, {type: 'radar', data: data, options: options});
                    break;
                case 'PolarArea':
                    new Chart(ctx, {data: data, type: 'polarArea', options: options});
                    break;
            }
            // Initiate new chart or Redraw

        };
        // run function - render chart at first load
        generateChart();
    },
    //init
    ChartJs.prototype.init = function() {
        //creating lineChart
        var lineChart = {
            labels: ["Jan", "Feb", "March", "Apr", "May", "Jun", "July", "Aug", "Sept", "oct", "Nov", "Dec"],
            datasets: [
                {
                    label: "Monthly Profits",
                    fill: false,
                    lineTension: 0.1,
                    backgroundColor: "#fb5020",
                    borderColor: "#fb5020",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    pointBorderColor: "#fb5020",
                    pointBackgroundColor: "#fff",
                    pointBorderWidth: 2,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "#fb5020",
                    pointHoverBorderColor: "#eef0f2",
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    data: [55, 66, 77, 88, 35 , 45, 60, 99, 70, 60, 50, 20, 40]
                }
            ]
        };

        var lineOpts = {
            scales: {
                yAxes: [{
                    ticks: {
                        max: 100,
                        min: 20,
                        stepSize: 10
                    }
                }]
            }
        };

        this.respChart($("#lineChart"),'Line',lineChart, lineOpts);

        //donut chart
        var donutChart = {
            labels: [
                "Android",
                "iphone",
                "windows",
				"Opermin"
            ],
            datasets: [
                {
                    data: [500, 150, 300, 100],
                    backgroundColor: [
                        "#f21f5d",
                        "#4f94f4",
                        "#ac3ba9",
						"#6438c5"
                    ],
                    hoverBackgroundColor: [
                        "#f21f5d",
                        "#4f94f4",
                        "#ac3ba9",
						"#6438c5"
                    ],
                    hoverBorderColor: "#fff"
                }]
        };
        this.respChart($("#doughnut"),'Doughnut',donutChart);


        //Pie chart
        var pieChart = {
            labels: [
                "Wordpress",
                "Html&css",
                "Psd",
				"Ecommerce",
				"PHp"
            ],
            datasets: [
                {
                    data: [100, 400, 200, 450, 170],
                    backgroundColor: [
                        "#105c83",
                        "#57a94f",
                        "#fb5020",
						"#47dfe2",
						"#e33a84"
                    ],
                    hoverBackgroundColor: [
                        "#105c83",
                        "#57a94f",
                        "#fb5020",
						"#47dfe2",
						"#e33a84"
                    ],
                    hoverBorderColor: "#fff"
                }]
        };
        this.respChart($("#pie"),'Pie',pieChart);


        //barchart
        var barChart = {
            labels: ["May", "Jun", "July", "Aug", "Sep", "Nov", "Dec"],
            datasets: [
                {
                    label: "Visitors",
                    backgroundColor: "rgba(242, 31, 93,0.2)",
                    borderColor: "#f21f5d",
                    borderWidth: 1,
                    hoverBackgroundColor: "rgba(242, 31, 93,0.2)",
                    hoverBorderColor: "#f21f5d",
                    data: [80, 90, 70, 66, 83, 67, 93,]
                }
            ]
        };
        this.respChart($("#bar"),'Bar',barChart);


        //radar chart
        var radarChart = {
            labels: ["Requriments", "Building", "Design", "Coding", "Implementing", "Testing", "Maintenance"],
            datasets: [
                {
                    label: "Windows",
                    backgroundColor: "rgba(122, 85, 247,0.3)",
                    borderColor: "rgb(122, 85, 247)",
                    pointBackgroundColor: "rgb(122, 85, 247)",
                    pointBorderColor: "#fff",
                    pointHoverBackgroundColor: "#fff",
                    pointHoverBorderColor: "rgb(122, 85, 247)",
                    data: [30, 40, 80, 90, 70, 60, 50]
                },
                {
                    label: "Mobiles",
                    backgroundColor: "rgba(87, 169, 79,0.2)",
                    borderColor: "rgb(87, 169, 79)",
                    pointBackgroundColor: "rgb(87, 169, 79)",
                    pointBorderColor: "#fff",
                    pointHoverBackgroundColor: "#fff",
                    pointHoverBorderColor: "rgb(87, 169, 79)",
                    data: [40, 50, 90, 95, 80, 70, 60]
                }
            ]
        };
        this.respChart($("#radar"),'Radar',radarChart);

        //Polar area chart
        var polarChart = {
            datasets: [{
                data: [
                    35,
                    19,
                    22,
                    42,
                    47
                ],
                backgroundColor: [
                    "#e33a84",
                    "#ac3ba9",
                    "#57a9af",
                    "#f21f5d",
                    "#fb5020"
                ],
                label: 'My dataset', // for legend
                hoverBorderColor: "#fff"
            }],
            labels: [
                "User 1",
                "User 2",
                "User 3",
                "User 4",
                "User 5"
            ]
        };
        this.respChart($("#polarArea"),'PolarArea',polarChart);
    },
    $.ChartJs = new ChartJs, $.ChartJs.Constructor = ChartJs

}(window.jQuery),

//initializing
function($) {
    "use strict";
    $.ChartJs.init()
}(window.jQuery);


$( document ).ready(function() {

    var DrawSparkline = function() {
        $('#sparkline1').sparkline([0, 22, 33, 45, 34, 40, 50, 47, 50], {
            type: 'line',
            width: "100%",
            height: '165',
            chartRangeMax: 50,
            lineColor: '#47dfe2',
            fillColor: 'rgba(71, 223, 226,0.3)',
            highlightLineColor: 'rgba(0,0,0,.1)',
            highlightSpotColor: 'rgba(0,0,0,.2)'
        });

        $('#sparkline1').sparkline([35, 33, 46, 34, 45, 52, 60, 34, 39], {
            type: 'line',
            width: "100%",
            height: '165',
            chartRangeMax: 40,
            lineColor: '#77f0c0',
            fillColor: 'rgba(119, 240, 192,0.3)',
            composite: true,
            highlightLineColor: 'rgba(0,0,0,.1)',
            highlightSpotColor: 'rgba(0,0,0,.2)'
        });

        $('#sparkline2').sparkline([4, 7, 8, 9, 7, 5, 8, 12, 13, 8, 5, 10, 13, 14, 12, 13], {
            type: 'bar',
            height: '165',
            barWidth: '10',
            barSpacing: '3',
            barColor: '#a185f9'
        });

        $('#sparkline3').sparkline([20, 10, 30, 20], {
            type: 'pie',
            width: '165',
            height: '165',
            sliceColors: ['#ffdb4a',  '#fb6d8b', '#ff66b3', '#99d683']
        });

        $('#sparkline4').sparkline([0, 25, 45, 35, 55, 40, 65, 30, 70], {
            type: 'line',
            width: "100%",
            height: '165',
            chartRangeMax: 50,
            lineColor: '#fb5020',
            fillColor: 'transparent',
            highlightLineColor: 'rgba(0,0,0,.1)',
            highlightSpotColor: 'rgba(0,0,0,.2)'
        });

        $('#sparkline4').sparkline([35, 45, 20, 55, 65, 30, 25, 30, 65], {
            type: 'line',
            width: "100%",
            height: '165',
            chartRangeMax: 40,
            lineColor: '#f21f5d',
            fillColor: 'transparent',
            composite: true,
            highlightLineColor: 'rgba(0,0,0,1)',
            highlightSpotColor: 'rgba(0,0,0,1)'
        });

        $('#sparkline6').sparkline([4, 7, 8, 9, 7, 5, 8, 11, 13, 8, 5, 10, 13, 14, 12, 11], {
            type: 'bar',
            height: '165',
            barWidth: '10',
            barSpacing: '3',
            barColor: '#f21f5d'
        });

        $('#sparkline6').sparkline([4, 5, 8, 9, 7, 5, 8, 11, 13, 8, 5, 10, 11, 12, 10, 9], {
            type: 'line',
            width: "100%",
            height: '165',
            lineColor: '#89adf8',
            fillColor: 'transparent',
            composite: true,
            highlightLineColor: 'rgba(0,0,0,.1)',
            highlightSpotColor: 'rgba(0,0,0,.2)'
        });


    },
        DrawMouseSpeed = function () {
            var mrefreshinterval = 500; // update display every 500ms
            var lastmousex=-1;
            var lastmousey=-1;
            var lastmousetime;
            var mousetravel = 0;
            var mpoints = [];
            var mpoints_max = 30;
            $('html').mousemove(function(e) {
                var mousex = e.pageX;
                var mousey = e.pageY;
                if (lastmousex > -1) {
                    mousetravel += Math.max( Math.abs(mousex-lastmousex), Math.abs(mousey-lastmousey) );
                }
                lastmousex = mousex;
                lastmousey = mousey;
            });
            var mdraw = function() {
                var md = new Date();
                var timenow = md.getTime();
                if (lastmousetime && lastmousetime!=timenow) {
                    var pps = Math.round(mousetravel / (timenow - lastmousetime) * 1000);
                    mpoints.push(pps);
                    if (mpoints.length > mpoints_max)
                        mpoints.splice(0,1);
                    mousetravel = 0;
                    $('#sparkline5').sparkline(mpoints, {
                        tooltipSuffix: ' pixels per second',
                        type: 'line',
                        width: "100%",
                        height: '165',
                        chartRangeMax: 50,
                        lineColor: '#e33a84',
                        fillColor: 'rgba(227, 58, 132,0.1)',
                        highlightLineColor: 'rgba(24,147,126,.1)',
                        highlightSpotColor: 'rgba(24,147,126,.2)'
                    });
                }
                lastmousetime = timenow;
                setTimeout(mdraw, mrefreshinterval);
            }
            // We could use setInterval instead, but I prefer to do it this way
            setTimeout(mdraw, mrefreshinterval);
        };

    DrawSparkline();
    DrawMouseSpeed();

    var resizeChart;

    $(window).resize(function(e) {
        clearTimeout(resizeChart);
        resizeChart = setTimeout(function() {
            DrawSparkline();
            DrawMouseSpeed();
        }, 300);
    });
});

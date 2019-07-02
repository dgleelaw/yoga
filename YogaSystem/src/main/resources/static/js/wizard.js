$(document).on("pagecreate", "#wizard", function () {
    $(".step").not(":eq(0)").addClass("ui-screen-hidden");
    $(".step:eq(0)").addClass("active");
    $(".progress p:eq(0)").addClass("currentStep");
    $(".ui-content").on("swipeleft swiperight", function (e) {
        var swipe = e.type,
            nextStep = $(".steps").find(".active").next(".step"),
            prevStep = $(".steps").find(".active").prev(".step");
        switch (true) {
            case (swipe == "swipeleft" && nextStep.length > 0):
                $(".step.active")
                    .toggleClass("slide out");
                break;
            case (swipe == "swiperight" && prevStep.length > 0):
                $(".step.active")
                    .toggleClass("slide out reverse");
                break;
        }
    });
}).on("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", ".step", function (e) {
    var elm = $(e.target);
    switch (true) {
        case (elm.hasClass("out") && !elm.hasClass("reverse")):
            $(elm).toggleClass("slide out ui-screen-hidden active");
            $(elm).next(".step").toggleClass("slide in active ui-screen-hidden");
            break;
        case (elm.hasClass("out") && elm.hasClass("reverse")):
            $(elm).toggleClass("slide out ui-screen-hidden reverse active");
            $(elm).prev(".step").toggleClass("slide in active reverse ui-screen-hidden");
            break;
        case (elm.hasClass("in") && !elm.hasClass("reverse")):
            elm.toggleClass("slide in");
            break;
        case (elm.hasClass("in") && elm.hasClass("reverse")):
            elm.toggleClass("slide in reverse");
            break;
    }
    var dot = $(".active").index();
    $("p:eq(" + dot + ")").prevAll("p").addBack().addClass("currentStep");
    $("p:eq(" + dot + ")").nextAll("p").removeClass("currentStep");
});
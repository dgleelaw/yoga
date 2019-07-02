function register() {
	$.ajax({
		url : "/user/register",
		data : {
			acc : $("#acc").val(),
			pwd : $("#pwd").val(),
			code : $("#code").val(),
		},
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			if (data == "成功") {
				location.href = "http://localhost:8080/html/login.html";
			}else{
				var con="";
				con+=' <a id="mes" style="color: red">'+data+'</a>';
				$("#mes").html(con)
			}s
		}
	})
}

// 验证码获取

function registerCode(val) {
	var re = 1;
	$.ajax({
		url : "/user/getcode",
		data : {
			acc : $("#acc").val(),
			i : re
		},
		success : function(data) {
			alert(data)

		}
	})

	settime(val);
}
// 验证码倒计时
var countdown = 2;
function settime(val) {
	if (countdown == 0) {
		val.removeAttribute("disabled");
		val.value = "获取验证码";
		countdown = 2;
	} else {
		val.setAttribute("disabled", true);
		val.value = "重新发送(" + countdown + ")";
		countdown--;
		setTimeout(function() {
			settime(val)
		}, 1000)
	}
}

// 重复密码验证
$(function() {
	$("#rpwd").blur(function() {
		var pass = $("#pwd").val();
		var rpass = $("#rpwd").val();
		if (pass != rpass) {
			//alert("两次密码不一致");
			var con="";
			con+=' <a id="mes" style="color: red">两次密码不一致</a>';
			$("#mes").html(con)
		}
	})
})

// 查询手机号是否被注册
$(function() {
	$("#acc").blur(function() {
		$.ajax({
			url : "/user/checkacc",
			data : {
				acc : $("#acc").val(),
			},
			success : function(data) {
				var con="";
				con+=' <a id="mes" style="color: red">'+data+'</a>';
				$("#mes").html(con)
			}
		})
	})
})
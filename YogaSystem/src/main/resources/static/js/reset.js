function reset1(){
	var erro="请输入";
	var acc=$("#acc").val();
	var pwd=$("#pwd").val();
	var code=$("#code").val();
	if(acc.length==0){
		erro+="账号！"
	}
	 if(pwd.length==0){
		 erro+="重置密码！"
	}
	 if(code.length==0){
		 erro+="验证码！"
	 }
	 if(erro!="请输入"){
		 var con="";
			con+=' <a id="mes" style="color: #red">'+erro+'</a>';
			$("#mes").html(con)
			return;
	 }
	$.ajax({
		url:"/user/reset",
		data:{
			acc:$("#acc").val(),
			pwd:$("#pwd").val(),
			code:$("#code").val(),
		},
		 contentType: "application/json; charset=utf-8",
		 success: function(data){
		
				if(data=="成功"){
					location.href="http://localhost:8080/html/login.html";
				}else{
					var con="";
					con+=' <a id="mes" style="color: #red">'+data+'</a>';
					$("#mes").html(con)
				}
			}
	})
}

//重复密码验证 
$(function(){
	$("#rpwd").blur(function(){
		var pass=$("#pwd").val();
		var rpass=$("#rpwd").val();
		if(pass!=rpass){
			//alert("两次密码不一致");
			var con="";
			con+=' <a id="mes" style="color: #red">两次密码不一致</a>';
			$("#mes").html(con)
		}
	})
})


//验证码获取

function registerCode(val){
	var re = 2;
	$.ajax({
		url:"/user/getcode",
		data:{
			acc:$("#acc").val(),
			i:re
		},
		success:function(data){
			alert(data)
		}
	})
	settime(val);
}

//验证码倒计时
var countdown=60;
function settime(val) {
	if (countdown == 0) {
	val.removeAttribute("disabled");
	val.value="获取验证码";
	countdown=60;
	} else {
	val.setAttribute("disabled", true);
	val.value="重新发送(" + countdown + ")";
	countdown--;
	setTimeout(function() {
	settime(val)
	},1000)
	}
	}
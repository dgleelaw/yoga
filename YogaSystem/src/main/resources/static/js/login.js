function login() {
	$.ajax({
		url : "/user/login",
		data : {
			acc : $("#acc").val(),
			pwd : $("#pwd").val()
		},
		success : function(data) {
			var re = $.isEmptyObject(data);
			if (!re) {
				//0学员 1教练 2场馆 3管理 4超管  5游客
				if (data.role == 5) {
					location.href = "/index";
				} else if(data.role == 4){
					location.href = "/html/addmanager.html";
				}else if(data.role==0){
					location.href = "/index";
				}else if(data.role==1){
					location.href = "/index";
				}else if(data.role==2||data.role==6){
					location.href = "/index";
				}else if(data.role==3){
					location.href = "/html/applymessage.html";
				}
			} else {
				//alert("账号或密码错误");
				var con="";
				con+=' <a id="mes" style="color: red">账号或密码错误</a>';
				$("#mes").html(con)
			}

		}
	})
}

function register(){
	location.href = "http://localhost:8080/html/register.html";
}
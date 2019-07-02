$(function(){
	$.ajax({
		url:"/user/modifycoach",
		success: function(data){
			
			console.info(data.message)
			
			var pic="";
			var uname="";
			var usex="";
			var umes="";
			var usalry="";
			var uprivatetime="";
			 if(data.message.sex==0){
				 usex+='性别 <input type="radio" name="sex" value="1">男 <input type="radio" name="sex" value="0" checked="checked">女</div>'
			 }
			 if(data.message.sex==1){
				 usex+='性别 <input type="radio" name="sex" value="1" checked="checked">男 <input type="radio" name="sex" value="0" >女</div>'
			 }
			 
			if(data.message.infostatus==0){
				umes+='<input id="state" type="radio" name="infostatus" value="0" checked="checked">保密'+
					'<input id="state" type="radio" name="infostatus" value="1">好友公开'+
					'<input id="state" type="radio" name="infostatus" value="2">全部公开'
			}
			 if(data.message.infostatus==1){
				umes+='<input id="state" type="radio" name="infostatus" value="0" >保密'+
				'<input id="state" type="radio" name="infostatus" value="1" checked="checked">好友公开'+
				'<input id="state" type="radio" name="infostatus" value="2">全部公开'
				}
			 if(data.message.infostatus==2){
				umes+='<input id="state" type="radio" name="infostatus" value="0" >保密'+
				'<input id="state" type="radio" name="infostatus" value="1" >好友公开'+
				'<input id="state" type="radio" name="infostatus" value="2" checked="checked">全部公开'
			}
			
			pic+='<img  src="'+data.message.img+'" width="80px";height="80px">'
			uname+= '<label for="userName3">User Name*</label>'+
				'<input type="text" name="nick" required placeholder="Enter user name" class="form-control" id="name" value="'+data.message.name+'">'
			usalry+= ' <label for="salary">收费标准 *</label>'+'<input id="salary" type="text" name="salary" Placeholder="收费标准人民币" class="form-control" value="'+data.message.salary+'">'
           
			if(data.message.privatetime==1){
				uprivatetime+='<label for="time">是否任课 *</label>' +
	              '<input type="radio" name="time" value="1" checked="checked">是 <input type="radio" name="time" value="0">否' 
			}
			
			if(data.message.privatetime==0){
				uprivatetime+='<label for="time">是否任课 *</label>' +
	              '<input type="radio" name="time" value="1" >是 <input type="radio" name="time" value="0" checked="checked">否' 
			}
				$("#pic").html(pic);
				$("#coachName").html(uname)
				$("#coachSex").html(usex)
				$("#coachstate").html(umes)
				$("#coachSalary").html(usalry)
				$("#coachTime").html(uprivatetime)
				
				
		}
		
	})
})


//上传信息
function coachResubmit(){
	var i =1;
	$.ajax({
		type : "post",
		url:"/user/modifycoachmes",
		data:{
			role:i,
			//流派
			school:$("#school").val(),
			//薪资
			salary:$("#salary").val(),
			//任课类型
			authstatus:$("#selauthstatus").val(),
			//是否接客
			privatetime:$('input[type="radio"][name="time"]:checked').val(),
			 sex:$('input[type="radio"][name="sex"]:checked').val(),
			//信息是否显示
			 infostatus:$('input[type="radio"][name="infostatus"]:checked').val(),
			 province:$("#selProvince").val(),
			 city:$("#selCity").val(),
			 county:$("#selCounty").val(),
			 town:$("#selTown").val(),
			 detail:$("#detailAddress").val(),
			 name:$("#name").val(), 
			 vx:$("#vx").val(),
			 vy:$("#vy").val(),
		},
		success:function(data){
			alert(data)
		}
	})
}


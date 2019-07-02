$(function(){
	$.ajax({
		url:"/user/modifytrainee",
		success: function(data){
			
			console.info(data.message)
			console.info(data.address)
			
			var pic="";
			var uname="";
			var usex="";
			var umes="";
			 if(data.message.sex==0){
				 usex+='性别 <input type="radio" name="sex" value="1">男 <input type="radio" name="sex" value="0" checked="checked">女</div>'
			 }
			 if(data.message.sex==1){
				 usex+='性别 <input type="radio" name="sex" value="1" checked="checked">男 <input type="radio" name="sex" value="0" >女</div>'
			 }
			 
			if(data.message.status==0){
				umes+='<input id="state" type="radio" name="state" value="0" checked="checked">保密'+
					'<input id="state" type="radio" name="state" value="1">好友公开'+
					'<input id="state" type="radio" name="state" value="2">全部公开'
			}
			 if(data.message.status==1){
				umes+='<input id="state" type="radio" name="state" value="0" >保密'+
				'<input id="state" type="radio" name="state" value="1" checked="checked">好友公开'+
				'<input id="state" type="radio" name="state" value="2">全部公开'
				}
			 if(data.message.status==2){
				umes+='<input id="state" type="radio" name="state" value="0" >保密'+
				'<input id="state" type="radio" name="state" value="1" >好友公开'+
				'<input id="state" type="radio" name="state" value="2" checked="checked">全部公开'
			}
			
			pic+='<img  src="'+data.message.img+'" width="80px";height="80px">'
			uname+= '<label for="userName3">User Name*</label>'+
				'<input type="text" name="nick" required placeholder="Enter user name" class="form-control" id="name" value="'+data.message.name+'">'
				
				$("#pic").html(pic);
				$("#username").html(uname)
				$("#usersex").html(usex)
				$("#userstatus").html(umes)
				
		}
		
	})
})

function resubmit(){
	var i =0;
	$.ajax({
		type : "post",
		url:"/user/modifytraineemes",
		data:{
			role:i,
			 sex:$('input[type="radio"][name="sex"]:checked').val(),
			//信息是否显示
			 status:$('input[type="radio"][name="state"]:checked').val(),
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


function coachAdopt(uid){
	var i= 1;
	$.ajax({
		url:"/user/agree",
		type:"post",
		data:{
			role:i,
			uid1:uid,
		},
		success:function(data){
			window.location.href = "/html/applymessage.html"
		}
	})
}

function venuesAdopt(uid){
	var i= 2;
	$.ajax({
		url:"/user/agree",
		type:"post",
		data:{
			role:i,
			uid1:uid,

		},
		success:function(data){
			window.location.href = "/html/applymessage.html"
		}
	})
}

function coachRefuse(uid){
	var i= 1;
	$.ajax({
		url:"/user/refuse",
		type:"post",
		data:{
			role:i,
			uid1:uid,
		},
		success:function(data){
			window.location.href = "/html/applymessage.html"
		}
	})
}

function venuesRefuse(uid){
	var i= 2;
	$.ajax({
		url:"/user/refuse",
		type:"post",
		data:{
			role:i,
			uid1:uid,
		},
		success:function(data){
			window.location.href = "/html/applymessage.html"
		}
	})
}


$(function() {

	function request() {
		$.ajax({
			url : "/user/findCoachMessage",
			dataType : "json",
			success : function(data) {
				var content = "";
				for (var i = 0; i < data.length; i++) {
					var po = data[i];
					content += '<div class="zurb-div-xl-6">'
							+ '<div class="m-b-10">'
							+ '<div class="callout callout-info shadow">'
							+ '<img src="'+po.img+'" width="80px";height="80px"/>'+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + po.name + ''
							+ '<h4 class="text-info">流派：'
							+ po.school + '</h4>' 
							if(po.sex==1){
								content +='<p>'
									+ '性别：男' +'</p>'
							}
							if(po.sex==0){
								content +='<p>'
									+ '性别：女' +'</p>'
							}
							content +='<p>'
							+ '任课类型：'+po.authstatus+ '</p>';
							if(po.type==1){
								content +="<a href='javascript:void(0);' onclick='coachAdopt("+po.uid+")'>同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							}
							if(po.type==0){
								content +='<a href="javascript:void(0);" onclick="venuesAdopt('+po.uid+')">同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
							}
					
							
					content += '<a href="javascript:void(0);" onclick="coachRefuse('+po.uid+')">拒绝</a>'
							+ '</div>' + '</div>'
							+ '</div>'
				}
				$("#traineeMes").html(content)	
				$(".page-title").text("教练申请请求")
			}
		})
	}

	function hint() {
		
				$.ajax({
					url : "/user/findVenuesMessage",				
					dataType : "json",
					success : function(data) {
						var content = "";
						for (var i = 0; i < data.length; i++) {
							var po = data[i];
							content += '<div class="zurb-div-xl-6">'
									+ '<div class="m-b-10">'
									+ '<div class="callout callout-info shadow">'
									+ '<img src="'+po.img+'" width="80px";height="80px"/>'+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + po.name + ''
									+ '<h4 class="text-info">场馆名称：'
									+ po.name
									+ '</h4>'
									+ '<p>'
									+'描述：'+po.descrie+ '<p>'
									+'提供薪资：'+po.salary+ '元/月<p>'
									if(po.type==1){
										content +="<a href='javascript:void(0);' onclick='coachAdopt("+po.uid+")'>同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
									}
									if(po.type==0){
										content +='<a href="javascript:void(0);" onclick="venuesAdopt('+po.uid+')">同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
									}
							
									
							content += '<a href="javascript:void(0);" onclick="venuesRefuse('+po.uid+')">拒绝</a>'
									+ '</div>' + '</div>'
									+ '</div>'
									
						}
						$("#traineeMes").html(content)
						$(".page-title").text("场馆申请请求")
					}
				})
	}
	$("#request").click(function() {
		request()
	})
	$("#hint").click(function() {
		hint();
	})

	hint()
})
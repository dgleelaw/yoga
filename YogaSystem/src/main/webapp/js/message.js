function removeHit(mid) {
	$.ajax({
		url : "/coach/removeMes",
		type : "post",
		data : {
			id : mid
		},
		success : function(data) {
			alert(data);
			window.location.href = "/html/message.html"
		}
	})
}

function coachAdopt(uid1,uid2,price,startTime,vid,mid,pid){
	alert(startTime)
	$.ajax({
		url:"/coach/adopt",
		type:"post",
		data:{
			"uid1":uid1,
			"uid2":uid2,
			"price":price,
			"startTime":startTime,
			"vid":vid,
			"mid":mid,
			"pid":pid
		},
		success:function(data){
			alert(data)
			window.location.href = "/html/message.html"
		}
	})
}

function venuesAdoptTrainee(uid1,uid2,mid){
	$.ajax({
		url:"/venues/acceptTrainMsg",
		type:"post",
		data:{
			"uid1":uid1,
			"uid2":uid2,
			"mid":mid,
		},
		success:function(data){
			alert(data)
			window.location.href = "/html/message.html"
		}
	})
}


function venuesAdoptCoach(uid1,uid2,mid){
	$.ajax({
		url:"/venues/acceptCoachMsg",
		type:"post",
		data:{
			"uid1":uid1,
			"uid2":uid2,
			"mid":mid,
		},
		success:function(data){
			alert(data)
			window.location.href = "/html/message.html"
		}
	})
}



function refuse(uid1,uid2,messid){
	$.ajax({
		url:"/coach/refuse",
		type:"post",
		data:{
			vid:uid2,
			tid:uid1,
			id:messid
		},
		success:function(data){
			alert(data)
			window.location.href = "/html/message.html"
		}
	})
}

function acceptTrainMsg(uid1,uid2,messid){
	$.ajax({
		url:"/venues/acceptTrainMsg",
		type:"post",
		data:{
			vid:uid2,
			cid:uid1,
			mid:messid
		},
		success:function(data){
			alert(data)
			window.location.href = "/html/message.html"
		}
	})
}
function acceptCoachMsg(uid1,uid2,messid){
	$.ajax({
		url:"/venues/acceptCoachMsg",
		type:"post",
		data:{
			cid:uid2,
			tid:uid1,
			mid:messid
		},
		success:function(data){
			alert(data)
			window.location.href = "/html/message.html"
		}
	})
}


$(function() {

	function request() {
		$.ajax({
			url : "http://localhost:8080/coach/findResMessage",
			
			dataType : "json",
			success : function(data) {
				var content = "";
				for (var i = 0; i < data.mes.length; i++) {
					var po = data.mes[i];
					content += '<div class="zurb-div-xl-6">'
							+ '<div class="m-b-10">'
							+ '<div class="callout callout-info shadow">'
							+ '<img src="'+po.img+'" width="50" height="40"/>' + po.name + '';
							if(data.role==1){
								content += '<h4 class="text-info">私教时间：'
									+ po.private_Course.startTime + '</h4>';
							}
							content += '<p>'+ po.content + '</p>';	
							if(data.role==1&&po.character==0){
								content +="<a href='javascript:void(0);' onclick='coachAdopt("+po.uid1+","+po.uid2+","+po.private_Course.price+",\""+po.private_Course.startTime+"\","+po.vid+","+po.id+","+po.private_Course.id+")'>同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							}
							if(data.role==2&&po.character==0){
								content +='<a href="javascript:void(0);" onclick="venuesAdoptTrainee('+po.uid1+','+po.uid2+','+po.id+')">同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
							}
							if(data.role==2&&po.character==1){
								content +='<a href="javascript:void(0);" onclick="venuesAdoptCoach('+po.uid1+','+po.uid2+','+po.id+')">同意</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp';
							}
							
					content += '<a href="javascript:void(0);" onclick="refuse('+po.uid1+','+po.uid2+','+po.id+')">拒绝</a>'
							+ '</div>' + '</div>'
							+ '</div>'
				}
				$("#traineeMes").html(content)	
				$(".page-title").text("请求信息")
			}
		})
	}

	function hint() {
		$
				.ajax({
					url : "http://localhost:8080/coach/findHintMessage",
					
					dataType : "json",
					success : function(data) {
						var content = "";
						for (var i = 0; i < data.length; i++) {
							var po = data[i];
							content += '<div class="zurb-div-xl-6">'
									+ '<div class="m-b-10">'
									+ '<div class="callout callout-info shadow">'
									+ '<img src="'+po.img+'"/>'
									+ '<h4 class="text-info">'
									+ po.name
									+ '</h4>'
									+ '<p>'
									+ po.content
									+ '</p>'
									+ '</div>'
									+ '</div>'
									+ '<input type="button" value="删除" onclick="removeHit('
									+ po.id + ')" />' + '</div>'
						}
						$("#traineeMes").html(content)
						$(".page-title").text("提示信息")
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
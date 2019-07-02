
$(function() {

			$
			.ajax({
				url : "/follows/findAllOtherFollows",
				data:{uid:$("#myUid").val()},
				success : function(data) {
					
					var content = "";
					var character = "普通会员";
					for (var i = 0; i < data.length; i++) {
						if (data[i].character == 0) {
							character = "普通会员";
						} else if (data[i].character == 4) {
							character = "教练";
						} else if (data[i].character == 5) {
							character = "学员";
						}
						content += '<li class="list-group-item">'
								+ '<div class="pull-left">'
								+ '<a href="#" class="user-list-item">'
								+ '<div class="avatar">'
								+ '<img src="'
								+ data[i].img
								+ '" alt="">'
								+ '</div>'
								+ '<div class="user-desc">'
								+ '<span class="name">'
								+ data[i].name
								+ '</span>'
								+ '<span class="desc">'
								+ character
								+ '</span>'
								+ '</div>'
								+ '</a>'
								+ '</div>'
								+ '<div class="pull-right">'
								+ '<a href="/follows/insertFollows?followuid='+data[i].followuid+'" class="btn btn-outline-default btn-rounded  btn-sm"><i class="ti-user"></i>关注</a>'
								+ '<a href="/user/chat?mineId='+data[i].uid+'&otherId='+data[i].followuid+'" class="btn btn-outline-default btn-rounded  btn-sm"><i class="ti-user"></i> 聊天</a>'
								+ '</div>' + '</li>'
					}
					$("#myconcerned").append(content);
				}
			})
			$
			.ajax({
				url : "/follows/findMyOtherFollows",
				
				type : "post",
				data:{uid:$("#myUid").val()},
				success : function(data) {
					var content = "";
					var character = "普通会员";
					for (var i = 0; i < data.length; i++) {
						if (data[i].character == 0) {
							character = "普通会员";
						} else if (data[i].character == 4) {
							character = "教练";
						} else if (data[i].character == 5) {
							character = "学员";
						}
						content += '<li class="list-group-item">'
								+ '<div class="pull-left">'
								+ '<a href="#" class="user-list-item">'
								+ '<div class="avatar">'
								+ '<img src="'
								+ data[i].myimg
								+ '" alt="">'
								+ '</div>'
								+ '<div class="user-desc">'
								+ '<span class="name">'
								+ data[i].myname
								+ '</span>'
								+ '<span class="desc">'
								+ character
								+ '</span>'
								+ '</div>'
								+ '</a>'
								+ '</div>'
								+ '<div class="pull-right">'
								+ '<a href="/follows/insertFollows?followuid='+data[i].uid+'" class="btn btn-outline-default btn-rounded  btn-sm"><i class="ti-user"></i>关注</a>'
								+ '<a href="/user/chat?mineId='+data[i].uid+'&otherId='+data[i].followuid+'" class="btn btn-outline-default btn-rounded  btn-sm"><i class="ti-user"></i> 聊天</a>'
								+ '</div>' + '</li>'
					}
					$("#myfensi").append(content);
				}
			})
showPartCircle();
});
function showPartCircle(){
	$
	.ajax({
		url : "/circle/findMyTenCircle",
		data:{ nowpage: $("#nowpage").val(),
				uid:$("#myUid").val()							},
		success : function(map) {
			$("#totalpage").val(map.totalPage);
			 var data=map.data;
			var content = "";
			for (var i = 0; i < data.length; i++) {
				var circle=data[i];
				
				content+= '<div class="comment">'
	            +'<img src="'+circle.head+'" alt="" class="comment-avatar">'
	            +'<div class="comment-body">'
	              +'<div class="comment-text">'
	               +' <div class="comment-header">'
	                  +'<a href="#" title="">'+circle.name+'</a><span>'+circle.time+'</span>'
	                +'</div> '+circle.content+''
	              +'</div>'
	              +'<div class="m-t-10" >'
	                +'<a href="">'
	                  +'<img src="'+circle.img+'" alt="" >'
	                +'</a>'
	              +'</div>';
				if(data[i].list!=null){
					var list= data[i].list;
					for (var j = 0; j < list.length; j++) {
						content+=	'<div class="comment">'
							+'<img src="'+list[j].img+'" alt="" class="comment-avatar">'
							+'<div class="comment-body">'
							+' <div class="comment-text">'
							+'<div class="comment-header">'
							+'<a href="#" title="">'+list[j].name+'</a><span>'+list[j].time+'</span>'
							+'</div> '+list[j].text+''
							+'</div>'
							+' </div>'
							+'</div>'
					}
					
				}
				content+=	'<div class="comment-footer">'
					+' <form class="bs-example bs-example-form" role="form" method="post" action="/view/insertOtherView">'
					+' <div class="row">'

					+' <div class="col-lg-6">'
					+' <div class="input-group">'
					+' <input type="text" class="form-control" name="text">'
					+' <input type="hidden" class="form-control" name="id" value="'+circle.id+'">'
					+' <input type="hidden" class="form-control" name="uid" value="'+$("#myUid").val()+'">'
					+' <input type="hidden" class="form-control" name="img" value="'+$("#myImg").val()+'">'
					+' <input type="hidden" class="form-control" name="name" value="'+$("#myName").val()+'">'
					+' <span class="input-group-btn">'
					+' <input type="submit" class="btn btn-default" value="评论" />'
					+' </span>'
					+' </div><!-- /input-group -->'
					+' </div><!-- /.col-lg-6 -->'
					+' </div><!-- /.row -->'
					+' </form>'
					+' </div>'
					+' </div>'
					+'</div>'
			}
			$("#allcircleodme").html(content);
		}
	})
}
function next(){
	if(($("#totalpage").val()-1> $("#nowpage").val())){
		$("#nowpage").val(parseInt($("#nowpage").val())+1);
		showPartCircle();
	}else{
		alert("没有更多");
	}

};
function before(){
	 alert($("#nowpage").val());
	if(($("#nowpage").val()>0)){
		$("#nowpage").val($("#nowpage").val()-1);
		showPartCircle();
	}else{
		alert("没有更多");
	}
};
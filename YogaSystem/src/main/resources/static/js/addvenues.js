var imgSrc1 = [];  //存放图片路径
var imgFile1 = []; //存放文件流
var imgName1 = []; //存放图片名字



$(function(){
	$("#file2").on('change',function() {
		//获取type=file的input
		var fileImg = $("#file2")[0];
		//得到所有的图片列表
		var fileList = fileImg.files;
		for(var i = 0; i < fileList.length; i++) {
			//得到每个图片的路径
			var imgSrcI = getObjectURLImg(fileList[i]);
			//向文件名的数组末尾添加此文件名
			imgName1.push(fileList[i].name);
			//向路径的数组末尾添加路径
			imgSrc1.push(imgSrcI);
			//在文件流数组的末尾添加文件
			imgFile1.push(fileList[i]);
		}
		//将图片展示出去
		addNewContentImg();
	})

})


function addNewContentImg(obj){
	//var b = $.isEmptyObject(obj)
	if(obj!=null){
		alert("现在是传值时刻")
		$("#imgbox").html("");
		for(var a = 0; a < imgSrc1.length; a++) {
			var oldBox = $(obj).html();
			$(obj).html(oldBox + '<div class="imgContainer" style="float: left;"><img title=' + imgName1[a] + ' alt=' + imgName1[a] + ' src=' + imgSrc1[a] + ' onclick="imgDisplay(this)"><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
		obj=null;
		}
		}
	$("#imgbox").html("");
	for(var a = 0; a < imgSrc1.length; a++) {
		var oldBox = $("#imgbox").html();
		$("#imgbox").html(oldBox + '<div class="imgContainer" style="float: left;"><img title=' + imgName1[a] + ' alt=' + imgName1[a] + ' src=' + imgSrc1[a] + ' onclick="imgDisplay(this)"><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
	}


}


//当鼠标移到图片上时，显示x删除
function imgDisplay(obj) {
	var src = $(obj).attr("src");
	var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
	$('body').append(imgHtml);
}
//关闭窗口
function closePicture(obj) {
	$(obj).parent("div").remove();
}



function uploadpic(){
	if(!limitNum()){
	  	alert("最多只能上传"+4+"张照片!");
	  	return false;
	}
	
	upload();
	alert("上传成功")
}

//限制图片个数
function limitNum(){
	if(!4){
		alert(1+4)
		return true;
	}else if(imgFile1.length>4){
		alert(1+3)
		return false;
	}else{
		alert(1+2)
		return true;
	}
}

//删除
function removeImg(obj, index) {
	//向数组中删除元素
	imgSrc1.splice(index, 1);
	imgFile1.splice(index, 1);
	imgName1.splice(index, 1);
	var boxId = "#" + $(obj).parent('.imgContainer').parent().attr("id");
	alert(boxId)//#imgbox
	addNewContentImg(obj);
}



//图片预览路径
function getObjectURLImg(file) {
	var url = null;
	if(window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if(window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if(window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

//上传场馆图片并返回地址
function uploadpic(){
	//用FormData对象上传
	var formData = new FormData($("#form1")[0]);
	//由于formData对象中已经包含<input type='file'>的input标签，如果不删除，就会造成重复上传
	formData.delete("file");
	
	/* 	formData.append("type", type);//也可以添加其他字段 */
	for(var i=0;i<imgFile1.length;i++){
		formData.append("file",imgFile1[i]);
	}
		
			$.ajax({
				type : "post",
				url : "/user/uploadpic",
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {
					alert("存入成功")
				}
			})
}


//上传场馆信息
function submit(){
	//等待申请身份6
	var i =6;
	$.ajax({
		type : "post",
		url:"/user/applyvenues",
		data:{
			role:i,
			 name:$("#name").val(), 
			 province:$("#selProvince").val(),
			 city:$("#selCity").val(),
			 county:$("#selCounty").val(),
			 town:$("#selTown").val(),
			 detail:$("#detailAddress").val(),
				//薪资
				salary:$("#salary").val(),
				descrie:$("TextArea").val(),
				vx:$("#vx").val(),
				 vy:$("#vy").val(),
		},
		success:function(data){
			alert(data)
			location.href="/index";
		}
	})
}

//查询场馆名字是否被注册
$(function(){
	$("#name").blur(function(){
		$.ajax({
			url:"/user/checkname",
			data:{
				name:$("#name").val(),
			},
			success:function(data){
				//alert(data)
				var con="";
			con+=' <a id="mes" style="color: red">'+data+'</a>';
			$("#mes").html(con)
			}
		})
	})
})

	
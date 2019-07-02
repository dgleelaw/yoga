var imgSrc = [];  //存放图片路径
var imgFile = []; //存放文件流
var imgName = []; //存放图片名字

function back(){
	var state=confirm("确定要取消吗，刚刚填写的数据将不做保存");
	if(state){
		location.href="http://localhost:8080/index.html";
	}
}
//上传头像并返回地址
function upload(){
	var formData = new FormData($("#form")[0]);
	/* 	formData.append("type", type);//也可以添加其他字段 */
			$.ajax({
				type : "post",
				url : "/user/upload",
				data : formData,
				contentType : false,
				processData : false,
				success : function(picadd) {
					if (picadd == null) {
						alert("请添加图片文件");
					}
					/*var context = '<input type="text" hidden  id="img" value="'+picadd+'" />';
					alert(context)*/
					/*'<img id="a" src="'+picadd+'" width="80px";height="80px" ><br>'
					+*/ 
				
				/*	$("#pic").append(context);*/
					alert("上传成功")
				}
			})
}

//监听文件上传框
$(function(){
	$("#file1").on('change',function() {
		//获取type=file的input
		var fileImg = $("#file1")[0];
		//得到所有的图片列表
		var fileList = fileImg.files;
		for(var i = 0; i < fileList.length; i++) {
			//得到每个图片的路径
			var imgSrcI = getObjectURL(fileList[i]);
			//向文件名的数组末尾添加此文件名
			imgName.push(fileList[i].name);
			//向路径的数组末尾添加路径
			imgSrc.push(imgSrcI);
			//在文件流数组的末尾添加文件
			imgFile.push(fileList[i]);
		}
		//将图片展示出去
		addNewContent();
	})

})
//将图片展示出去
function addNewContent(){
	for(var a = 0; a < imgSrc.length; a++) {
		$("#pic").html('<img id="a" src="'+imgSrc[a]+'" width="80px";height="80px" >');
			
	}


}
//图片预览路径
function getObjectURL(file) {
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


//申请教练上传信息
function submit(){
	//6申请
	var i =6;
	$.ajax({
		type : "post",
		//url:"/user/regcoach",
		url:"/user/applycoach",
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
			location.href="/index";
		}
	})
}

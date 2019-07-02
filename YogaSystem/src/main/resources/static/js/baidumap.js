// 百度地图API功能
	var map = new BMap.Map("allmap");  // 创建Map实例
	map.centerAndZoom("成都",10);      // 初始化地图,用城市名设置地图中心点
	//104.06792346,30.67994285
	
	   //删除标注点
    var removeMarker = function (e, ee, marker) {
        map.removeOverlay(marker);
    };
	
	var marker = new BMap.Marker();
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		map.removeOverlay(marker);
		var point = new BMap.Point(e.point.lng,e.point.lat);
		 marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);               // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		var con="";
		con+='<input id="vx" type="hidden"  value="'+e.point.lng+'"/>'+'<input id="vy" type="hidden"  value="'+e.point.lat+'"/>';
			$("#zuo").html(con)
	});
	
	
	
	setTimeout(function(){
		map.setZoom(12);   
	}, 2000);  //2秒后放大到14级
	map.enableScrollWheelZoom(true);
	map.disableDragging();     //禁止拖拽
	setTimeout(function(){
	   map.enableDragging();   //两秒后开启拖拽
	   //map.enableInertialDragging();   //两秒后开启惯性拖拽
	}, 2000);
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角
	map.addControl(top_right_navigation);   
	 
	 // 添加定位控件
  var geolocationControl = new BMap.GeolocationControl();
  geolocationControl.addEventListener("locationSuccess", function(e){
    // 定位成功事件
    var address = '';
    address += e.addressComponent.province;
    address += e.addressComponent.city;
    address += e.addressComponent.district;
    address += e.addressComponent.street;
    address += e.addressComponent.streetNumber;
    alert("当前定位地址为：" + address);
  });
  geolocationControl.addEventListener("locationError",function(e){
    // 定位失败事件
    alert(e.message);
  });
  map.addControl(geolocationControl);
  
 //逆向解析地址
  var geoc = new BMap.Geocoder(); 
  var marker = new BMap.Marker();
	map.addEventListener("click", function(e){        
	var pt = e.point;
	geoc.getLocation(pt, function(rs){
		var addComp = rs.addressComponents;
		//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
		var con="";
		con+='<input class="form-control" type="text" class="f-input"/ id="detailAddress"  class="form-control" value="'+addComp.province+addComp.city+addComp.district+addComp.street+ addComp.streetNumber+'">'
		$("#detailbaidu").html(con)
	});        
});
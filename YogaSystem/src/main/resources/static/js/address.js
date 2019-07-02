var cityList = new Array();
cityList['北京市'] = [ '朝阳区', '东城区', '西城区', '海淀区', '宣武区', '丰台区', '怀柔', '延庆', '房山' ];
cityList['上海市'] = [ '宝山区', '长宁区', '丰贤区', '虹口区', '黄浦区', '青浦区', '南汇区', '徐汇区','卢湾区' ];
cityList['广州省'] = [ '广州市', '惠州市', '汕头市', '珠海市', '佛山市', '中山市', '东莞市' ];
cityList['深圳市'] = [ '福田区', '罗湖区', '盐田区', '宝安区', '龙岗区', '南山区', '深圳周边' ];
cityList['重庆市'] = [ '俞中区', '南岸区', '江北区', '沙坪坝区', '九龙坡区', '渝北区', '大渡口区', '北碚区' ];
cityList['天津市'] = [ '和平区', '河西区', '南开区', '河北区', '河东区', '红桥区', '塘古区', '开发区' ];
cityList['江苏省'] = [ '南京市', '苏州市', '无锡市' ];
cityList['浙江省'] = [ '杭州市', '宁波市', '温州市' ];
cityList['四川省'] = [ '成都市', '绵阳市','资阳市','德阳市' ];
cityList['海南省'] = [ '海口市' ];
cityList['福建省'] = [ '福州市', '厦门市', '泉州市', '漳州市' ];
cityList['山东省'] = [ '济南市', '青岛市', '烟台市' ];
cityList['江西省'] = [ '江西省', '南昌市' ];
cityList['广西省'] = [ '柳州市', '南宁市' ];
cityList['安徽省'] = [ '安徽省', '合肥市' ];
cityList['河北省'] = [ '邯郸市', '石家庄市' ];
cityList['河南省'] = [ '郑州市', '洛阳市' ];
cityList['湖北省'] = [ '武汉市', '宜昌市' ];
cityList['湖南省'] = [ '湖南省', '长沙市' ];
cityList['陕西省'] = [ '陕西省', '西安市' ];
cityList['山西省'] = [ '山西省', '太原市' ];
cityList['黑龙江省'] = [ '黑龙江省', '哈尔滨市' ];
cityList['其他'] = [ '其他' ];
var countyList = new Array();
countyList['成都市']=['成华区','崇州市','大邑县','简阳市','锦江区','金牛区','青羊区','武侯区'];
countyList['德阳市']=['广汉市','罗江区','绵竹市','什邡市','中江县'];
countyList['绵阳市']=['安州区','北川羌族自治县','江油市','平武县','三台县'];

var townList = new Array();
townList['锦江区']=['成龙路街道','春熙路街道','东光街道','督院街街道','合江亭街道','莲新街道','柳江街道','龙舟路街道','牛市口街道'];
townList['成华区']=['白莲池街道','保和街道','二仙桥街道','府青路街道','建设路街道','龙潭街道','猛追湾街道','青龙街道'];
townList['武侯区']=['簇锦街道','芳草街道','桂溪街道','红牌楼街道','华兴街道','火车南站街道'];
//选择省份以后，在城市下拉框中添加对应的城市
function changeCity() {
	var province = document.getElementById("selProvince").value;
	var city = document.getElementById("selCity");
	city.innerHTML = "";//先清空再赋值
	for ( var i in cityList) {
		if (i == province) {
			city.add(new Option("--选择城市--"));
			for ( var j in cityList[i]) {
				city.add(new Option(cityList[i][j], cityList[i][j], null));
			}
		}
	}
}
//选择城市以后，在区县下拉框中添加对应的区县
function changeCounty() {
	var city = document.getElementById("selCity").value;
	var county = document.getElementById("selCounty");
	county.innerHTML = "";//先清空再赋值
	for ( var i in countyList) {
		if (i == city) {
			county.add(new Option("--选择区县--"));
			for ( var j in countyList[i]) {
				county.add(new Option(countyList[i][j], countyList[i][j], null));
			}
		}
	}
}
//选择区县以后，在街道镇下拉框中添加对应的街道镇
function changeTown() {
	var county = document.getElementById("selCounty").value;
	var town = document.getElementById("selTown");
	town.innerHTML = "";//先清空再赋值
	for ( var i in townList) {
		if (i == county) {
			town.add(new Option("--选择街道镇--"));
			for ( var j in townList[i]) {
				town.add(new Option(townList[i][j], townList[i][j], null));
			}
		}
	}
}

//加载页面时候添加省份下拉框里面的信息
function addProvince() {
	var province = document.getElementById("selProvince");
	for ( var i in cityList) {
		province.add(new Option(i, i, null));
	}
}
$(function(){
	addProvince();
})
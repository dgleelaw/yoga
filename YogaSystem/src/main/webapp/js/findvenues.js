$(function() {
	
	$.ajax({
		url : "/coach/findVenues",
		dataType : "json",
		success : function(data) {
			
			var content = "";
			for (var i = 0; i < data.length; i++) {
				var po = data[i];
				content += '<div class="zurb-div-lg-4">'
						+ '<div class="card m-b-20">'
						+ '<div class="card-body">'
						+ '<table class="table mb-0 border">' + '<tbody>'
						+ '<tr>' + '<td colspan="2"><a href="/venues/find?id='
						+ po.id
						+ '"><img src="'+po.img+'" width="100%"'
						+ 'height="150" /></a></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>姓名</td>'
						+ '<td>'
						+ po.name
						+ '</td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>地址</td>'
						+ '<td>'
						+ po.address.town
						+ '</td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>详细</td>'
						+ '<td>'
						+ po.descrie
						+ '</td>'
						+ '</tr>'
						+ '</tbody>'
						+ '</table>'
						+ '</div>' + '</div>' + '</div>';
			}
			$("#show").html(content)
			
		}
	})
})
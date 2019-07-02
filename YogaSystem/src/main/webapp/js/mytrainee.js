$(function() {
	$
			.ajax({
				url : "/coach/findRole",
				type : "post",
				success : function(data) {
					$("#role").val(data)
					var role = $("#role").val();
					if (role == 1) {
						$
								.ajax({
									url : "/coach/findMyTrainee",
									type : "post",
									data : {
										uid : 3
									},
									success : function(data) {
										alert(2)
										var content = '<div class="new-row zurb-row">'
												+ '<div class="zurb-div-sm-12">'
												+ '<h4 class="page-title">我的学员</h4>'
												+ '</div>' + '</div>';
										for (var i = 0; i < data.length; i++) {
											var po = data[i]
											content += ' <div class="new-row zurb-row">'
													+ '<div class="zurb-div-xl-12">'
													+ '<div class="card m-b-20">'
													+ '<h5 class="card-header">'
													+ po.name
													+ '</h5>'
													+ '<div class="card-body">'
													+ '<img src="'+po.img+'" />'
													+ '<div class="btn-group">'
													+ '<button type="button" class="btn btn-primary m-b-5 dropdown-toggle waves-effect waves-light" data-toggle="dropdown" aria-expanded="false">电话：'
													+ po.phone
													+ ' <span class="caret"></span></button>'
													+ '</div>'
													+ '<div class="btn-group">'
													+ '<button type="button" class="btn btn-secondary m-b-5 dropdown-toggle waves-effect waves-light" data-toggle="dropdown" aria-expanded="false">地址：'
													+ po.address.detail
													+ ' <span class="caret"></span></button>'
													+ '</div>'
													+ '</div>'
													+ '</div>'
													+ '</div>'
													+ '</div>'
										}
										$("#trainees").html(content)
									}
								})
					}
					if (role == 2) {
						$
						.ajax({
							url : "/venues/findSignTraineeMsg",
							type : "post",
							data : {
								uid : 3
							},
							success : function(data) {
								alert(2)
								var content = '<div class="new-row zurb-row">'
										+ '<div class="zurb-div-sm-12">'
										+ '<h4 class="page-title">我的学员</h4>'
										+ '</div>' + '</div>';
								for (var i = 0; i < data.length; i++) {
									var po = data[i]
									content += ' <div class="new-row zurb-row">'
											+ '<div class="zurb-div-xl-12">'
											+ '<div class="card m-b-20">'
											+ '<h5 class="card-header">'
											+ po.name
											+ '</h5>'
											+ '<div class="card-body">'
											+ '<img src="'+po.img+'" />'
											+ '<div class="btn-group">'
											+ '<button type="button" class="btn btn-primary m-b-5 dropdown-toggle waves-effect waves-light" data-toggle="dropdown" aria-expanded="false">电话：'
											+ po.phone
											+ ' <span class="caret"></span></button>'
											+ '</div>'
											+ '<div class="btn-group">'
											+ '<button type="button" class="btn btn-secondary m-b-5 dropdown-toggle waves-effect waves-light" data-toggle="dropdown" aria-expanded="false">地址：'
											+ po.address.detail
											+ ' <span class="caret"></span></button>'
											+ '</div>'
											+ '</div>'
											+ '</div>'
											+ '</div>'
											+ '</div>'
								}
								$("#trainees").html(content)
							}
						})
					}
				}
			})

})
$(function(){
	var search_key = window.location.search.substring(1);
	search_key = search_key.split('&&');
	$.ajax({
		'url':'consultDetail.action',
		'type':'POST',
		'data':{
			'action':'getDetailConsultion',
			'logId':search_key[1]
		},
		success:function(result){
			if(result.mes != '成功'){
				alert('返回错误');
				return false;
			}else{
				$('#doctor_name').text(((result.detailConsultion[0])[2]));
				$('#user_name').text(((result.detailConsultion[0])[1]));
				for(var i=0;i < result.detailConsultion.length;i++){
					if((result.detailConsultion[i])[0].isReply == 'Y'){
						$('#chat_record_content').append(
							"<div class='content_doctor'>"+
								"<h4><span>"+(result.detailConsultion[i])[2]+"</span>&nbsp;&nbsp;<span>"+(result.detailConsultion[i])[0].submitTime+"</span></h4>"+
								"<section>"+(result.detailConsultion[i])[0].doctorReply+"</section>"+
							"</div>"
						);					
					}else if((result.detailConsultion[i])[0].isReply == 'N'){
						$('#chat_record_content').append(
							"<div class='content_user'>"+
								"<h4><span>"+(result.detailConsultion[i])[1]+"</span>&nbsp;&nbsp;<span>"+(result.detailConsultion[i])[0].submitTime+"</span></h4>"+
								"<section>"+(result.detailConsultion[i])[0].symptomDescribe+"</section>"+
							"</div>"						
						);					
					}
				}
			}
		}
	});	
});
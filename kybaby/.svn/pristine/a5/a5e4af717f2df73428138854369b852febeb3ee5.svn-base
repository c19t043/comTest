$(function(){

getOrderResult(window.location.search.substring(1));

});

function getOrderResult(orderNum){
	//实现获取订单结果的逻辑
	$.ajax({
		type:'post',
		url:host+'getOrderInfo.action',
		cache:false,
    async:false, 
		data:{action:"getOrderResult",orderNum:orderNum},
		success:function(result){
			if(result.someOrderResultList == null){
				return false;
			}
			for(var i=0;i < result.someOrderResultList.length;i++){
				$('#result_unit').append(
					"<tr>"+
						"<td>"+result.someOrderResultList[i][0]+"</td>"+
						"<td>"+result.someOrderResultList[i][1]+"</td>"+
						"<td>"+result.someOrderResultList[i][2]+"</td>"+
					"</tr>"
				);			
			}

			//document.writeln(JSON.stringify(result));
		},
		error: function () {
			layer();
		}
	});
};
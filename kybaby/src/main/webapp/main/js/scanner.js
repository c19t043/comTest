var flag=0;
var timeFlag;

var orderNum; //订单编号

function checkOrderStatus(orderNum){
	ale("orderNum:"+orderNum);
	flag+=1;
	if(flag>6){
		ale("30s后取消订单");
		window.clearInterval(timeFlag);
		//不支付处理
	} else{
		$.ajax({
			type:'post',
			cache:false,
			url:'cardManage.action',
			data:{action:"checkOrderStatus",strOrderNum:orderNum},
			success:function(result){
				ale("come in");
				ale("mes is"+result.mes);
				if(!(result.mes.indexOf("<trade_state>")<0)){
					tradeState=result.mes.substring(result.mes.indexOf("<trade_state>")+13,result.mes.indexOf("</trade_state>")-3);
					if(tradeState=="SUCCESS"){
						//支付成功后的订单处理
						//传入参数：id:数据库记录的Id
						ale("支付成功");
//						$.ajax({
//							type:'post',
//							cache:false,
//							url:'orderManage.action',
//							data:{action:"paySuccess",orderNum:scanId},
//							success:function(data){
//								ale(data.mes);
//							}
//						});
					}
					else if(tradeState=="CLOSED"||tradeState=="REVOKED"){
						//不支付处理
						ale("支付失败");
					}
				}
			},
			error: function () {
				layer();
			}
		});
	}
	
}
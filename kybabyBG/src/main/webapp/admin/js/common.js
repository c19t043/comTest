/*---------后台接口函数集合---------*/
//获取左侧菜单栏
	$.ajax({
		url:'adminManage.action',
	  	data:{action:"hadLogin"},
	  	async:false,
	    success:function(result){
	    	if(result.mes!="已登录"){
					window.location.href="login.html";
	      }
	    }
	});
function getAllFunctionListAndParent(){
	$.ajax({
		type:'post',
		url:'getFunctionInfo.action',
		async:false,
		data:{action:"getAllFunctionList"},
		success:function(result){
			//document.write(JSON.stringify(result));
			if(result.mes=="操作成功"){
				for(var i=0;i<result.allFunctionParentList.length;i++){
					$('#function').append(
						"<dl>"+
							"<dt onclick='left_meu_cli(this)'><span class='iconfont'>"+result.allFunctionParentList[i].functionParIcon+"&nbsp</span><span class='fir_name'>"+result.allFunctionParentList[i].name+"</span></dt>"+
							"<dd><ul id='function_"+i+"'></ul></dd>"+
						"</dl>"						
					);
					var functionList=result.allFunctionList[i];
					if(result.allFunctionList[i]!=null){
						for(var j=0;j<result.allFunctionList[i].length;j++){
							$("#function_"+i).append("<li onclick=\"window.location.href='"+functionList[j].functionUrl+"'\">"+functionList[j].name+"</li>");
						}
					}
				}
			}
		}
	});
}

function logout()
{
	$.ajax({
		type:'post',
		url:'adminManage.action',
		data:{action:"logout"},
	    success:function(result)
	    {
	    	window.location.href="login.html";
	    	
	    }
	})
}
$(function(){
	$.ajax({
		type:'post',
		url:'adminManage.action',
		data:{action:"showMyName"},
		success:function(result)
		{
			$("#head_name").text(result.myName);
		}
	})	
	
});

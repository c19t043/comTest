/**
 * Created by Administrator on 2015/9/22.
 */
function getEightData()
{
	$.ajax({
	type:'post',
	url:'newControl.action',
	data:{action:"all"},
	success:function(result)
	{

		if(result.mes=="成功")
			{
			$("#curConsult").text(result.consultationNum[0]);
			$("#allConsult").text(result.consultationNum[1]);
			$("#curCaseClip").text(result.caseClipNum[0]);
			$("#allCaseClip").text(result.caseClipNum[1]);
			$("#curOrder").text(result.orderNum[0]);
			$("#allOrder").text(result.orderNum[1]);
			$("#curBalance").text(result.withdrawalsNum[0]);
			$("#allBalance").text(result.withdrawalsNum[1]);
			}
	}
		
	});
	
}
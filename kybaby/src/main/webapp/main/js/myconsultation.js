/**
 * Created by vinny on 2015/10/19.
 */

$(function(){
	$.ajax({
		type:'post',
		url:host+'consultDoctorManage.action',
		cache:false,
		async:false,
		data:{action:"getListMsg"},
		success:function(result){
			//ale(result.toString());
			for(var i=0;i<result.doctorSomeMsgList.length;i++){
				var doclist = result.doctorSomeMsgList[i].toString().split(",");
				var conlist = result.newLatestConsultList[i].toString().split(",");
				
				if(doclist[1] == '' || doclist[1] == null || doclist[1] == undefined){
					imgurl = 'images/doctor_default.png';
				}else{
					imgurl = hostBG+'images/doctorFaceIcon/'+doclist[1];

				}
				
				if(result.newMesList[i]<=0){
					$("#container").append(
						"<p class='gray_2'></p>"+
						"<div class='mycon_box clearfix' onclick=\"window.location.href='consultation1.html?doctorId="+doclist[2]+"&doctorName="+doclist[0]+"&LogId="+conlist[4]+"&isBefore="+conlist[6]+"&isEnd="+conlist[7]+"'\">"+
						"<div class='mycon_left'><img src='"+imgurl+"'></div>"+
						"<div class='mycon_right'>"+
						"<p class='dorcor_name'>"+doclist[0]+"</p>"+
						"<p class='con_time'>"+conlist[1]+"</p>"+
						"<p class='con_info'>"+conlist[0]+"</p>"+
						"</div>"+
						"</div>");
				}else if(result.newMesList[i]>0){
					$("#container").append(
						"<p class='gray_2'></p>"+
						"<div class='mycon_box clearfix' onclick=\"window.location.href='consultation1.html?doctorId="+doclist[2]+"&doctorName="+doclist[0]+"&LogId="+conlist[4]+"&isBefore="+conlist[6]+"&isEnd="+conlist[7]+"'\">"+
						"<div class='mycon_left'><img src='"+imgurl+"'>"+"<span class='red_point'>"+result.newMesList[i]+"</span> </div>"+
						"<div class='mycon_right'>"+
						"<p class='dorcor_name'>"+doclist[0]+"</p>"+
						"<p class='con_time'>"+conlist[1]+"</p>"+
						"<p class='con_info'>"+conlist[2]+"</p>"+
						"</div>"+
						"</div>");
				}
			}
		},
		error: function () {
			layer();
		}
	});
})
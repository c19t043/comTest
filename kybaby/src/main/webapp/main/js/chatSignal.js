var zjid = window.location.search.substring(1).split("&");
var json={};
for(var i= 0,l=zjid.length;i<l;i++){
    var s=zjid[i].split("=");
    json[s[0]]=s[1];
}

var backWay = decodeURIComponent(window.location.search.substring(1).split("&&")[1]);
$(function(){
	somePostInfoDetail(json.id);
	giveStar();
});

function somePostInfoDetail(zj_id){
	$.ajax({
		type:'post',
		url:ringHost+'getMommyRingInfo.action',
		cache:false,
    async:false, 
		data:{action:"detail",postInfoId:zj_id},
		success:function(result){

			$('.doctor_name').eq(0).text(result.doctorName).attr('loginId',result.loginDoctorId);
			$('.tzContent').eq(0).html( "<b><p>"+result.postInfoTitle+"</p></b><br>"+result.postInfoContent.toString().replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&nbsp;/g,' '));
			$('.tzContent img,.tzContent iframe').css('float','left');
			$('.fbTime').eq(0).text(result.dateStr);
			if(result.doctorImg == "admin_"){
				$('.author_info_a').eq(0).find('img').attr('src',"images/adminManage.jpg");
			}else{
				$('.author_info_a').eq(0).find('img').attr('src',"/kybabyBG/admin/images/doctorFaceIcon/"+result.doctorImg+"");
			}
			
			if(result.postInfoLabelNameList != null){
				$.each(result.postInfoLabelNameList,function(i,content){
					$('.tagList').append(
						"<div class='belongTag'>"+content+"</div>"
					);
				});			
			}

			
			$('.doctor_view').eq(0).text(result.browseNum);
			$('.doctor_good').eq(0).text(result.pointNum);
			$('.doctor_message').eq(0).text(result.replyNum);
			if(result.isAllowReply == 'Y'){
				$('#isReplay').show();
			}else if(result.isAllowReply == 'N'){
				$('#isReplay').remove();
			}
			if(result.somePostInfoReply == null){
				return false;
			}
			$('#pinglun').empty();
			for(var i=0;i<result.somePostInfoReply.length;i++){
				var state = 'hidden';
				var state_ = 'N';
				if(result.somePostInfoReply[i][1] == result.doctorName){
					state = 'visible';
					state_='Y';
				}
				var responseState = 'none';
				var isResponse = 'hidden';
				if(result.somePostInfoReply[i][5] != 0){
					responseState = 'block';
				}
				if(result.somePostInfoReply[i][7] != 'N'){
					isResponse = 'visible';
				}
				
				$('#pinglun').append(
					"<div resId='"+result.somePostInfoReply[i][8]+"' class='pinglunbox "+state_+"' zjid='"+result.somePostInfoReply[i][0]+"'>"+
						"<div class='author_info_plus'>"+
							"<div class='author_info_a'>"+
								"<img src='/kybabyBG/admin/images/doctorFaceIcon/"+result.somePostInfoReply[i][4]+"'>"+
							"</div>"+
							
							"<div class='author_info_b'>"+
								"<div class='a'>"+
									"<span class='doctor_name'>"+result.somePostInfoReply[i][1]+"</span>"+
									
									"<span style='visibility:"+state+"' class='doctor_lz'>楼主</span>"+
									"<span class='doctor_num_pos'>"+(i+1)+"楼</span>"+
								"</div>"+
								"<div class='b'>"+
									"<span class='doctor_zhiye' style='visibility:hidden'>主任医师</span>"+
								"</div>"+
							"</div>"+
						"</div>"+
						"<section data_id='"+result.somePostInfoReply[i][0]+"' data_name='"+result.somePostInfoReply[i][1]+"' data_i='"+(i+1)+"' class='tzContent_plus'>"+result.somePostInfoReply[i][2]+"</section>"+
						
						"<div style='display:"+responseState+"' class='response_fa'>"+
							"<div class='sjx'></div>"+
							"<p class='response_p' data_id='"+result.somePostInfoReply[i][5]+"'>"+
								"<span class='a'></span>"+
								"<span class='b'></span>"+
							"</p>"+
							"<div class='response_con'>"+result.somePostInfoReply[i][6]+"</div>"+
						"</div>"+
						
						
						"<p class='fbTime_plus'>"+
							"<span>"+result.somePostInfoReply[i][3]+"</span>"+
							"<span class='delresponse' style='display:none'>删除</span>"+
							"<span class='delresponse' style='visibility:"+isResponse+"' onclick=\"replayS(this,'"+(i+1)+"楼','"+result.somePostInfoReply[i][0]+"')\">回复</span>"+
						"</p>"+
					"</div>"
				);
			}
			$('.response_p').each(function(){
				var _this = $(this);
				var obj = $('.tzContent_plus').filter(function(){
					return $(this).attr('data_id') == _this.attr('data_id');
				});
				if(obj.get(0) != undefined){
					_this.find('span').eq(0).text(obj.attr('data_name'));
					_this.find('span').eq(1).text(obj.attr("data_i")+"楼");
				}

			});
		},
		error: function () {
			layer();
		}
	});
} 
//取消
function replayC(){
	$(event.target).parent().animate({
		'top':'-70%',
		'opacity':'0'
	},600);
	$('#replayContent').val('');
	
}
//显示
function replayS(){
	if(arguments.length == 0){
		$('#replayContent').attr('placeholder','我也说俩句');
		$('#currRes').attr("onclick","replayT()");
	}else{
		$('#replayContent').attr("placeholder","@"+arguments[1]);//名字
		$('#currRes').attr("onclick","replayT('"+arguments[2]+"')");//id
	}
	$('#replayTiezi').animate({
		'top':'10%',
		'opacity':'1'
	},600);
	$('#replayContent').val('');
}
//回复
function replayT(){
	if($('#replayContent').val().trim() == ''){
		return false;
	}
	postReply(arguments[0]);
	$(event.target).parent().animate({
		'top':'-70%',
		'opacity':'0'
	},600);
	$('#replayContent').html('');
}
//帖子点赞
function giveStar(){
	var postInfoId=json.id;
	$.ajax({
		type:'post',
		url:ringHost+'mommyRingManage.action',
		cache:false,
     async:false, 
		data:{action:"giveStar",postInfoId:postInfoId},
		success:function(result){
			if(result.mes == '错误'){
				$('.doctor_good').eq(0).css('backgroundPositionY','-8px').attr('mark','Y');
			}else{
				$.ajax({//取消赞
					type:'post',
					url:ringHost+'mommyRingManage.action',
					cache:false,
			    async:false, 
					data:{action:"cancleStar",postInfoId:json.id},
					success:function(result){
						$('.doctor_good').eq(0).attr('mark','N');
					},
					error: function () {
						layer();
					}
				});	
			}
		},
		error: function () {
			layer();
		}
	});
}
//点赞
function dianzan(){
	var state = $(event.target).attr('mark');
	if(state == "Y"){
		$.ajax({//取消赞
			type:'post',
			url:ringHost+'mommyRingManage.action',
			cache:false,
	    async:false, 
			data:{action:"cancleStar",postInfoId:json.id},
			success:function(result){
				$('.doctor_good').eq(0).css('backgroundPositionY','-40px').attr('mark','N').text(Number($('.doctor_good').eq(0).text())-1);				
			},
			error: function () {
				layer();
			}
		});	
	}else if(state == "N"){
		$.ajax({//点赞
			type:'post',
			url:ringHost+'mommyRingManage.action',
			cache:false,
	    async:false, 
			data:{action:"giveStar",postInfoId:json.id},
			success:function(result){
				$('.doctor_good').eq(0).css('backgroundPositionY','-8px').attr('mark','Y').text(Number($('.doctor_good').eq(0).text())+1);
			},
			error: function () {
				layer();
			}
		});	
	}
}
//回复
//对帖子进行回复
function postReply(){
	var data_;
	if(arguments[0] == undefined){
		data_ = {
			action:"postReply",
			postInfoId:json.id,
			postContent:$('#replayContent').val()
		};	
	}else{
		data_ = {
			action:"postReply",
			postInfoId:json.id,
			postContent:$('#replayContent').val(),
			replyId:arguments[0]
		};		
	}

	$.ajax({
		type:'post',
		url:ringHost+'mommyRingManage.action',
		cache:false,
    async:false, 
		data:data_,
		success:function(result){
			if(result.mes == "未登录"){
				ale('请先登录');
				setTimeout(function(){
					window.location.href="login.html";
				},2500);
			}
			if(result.mes == '操作成功'){
				ale('发表成功');
				somePostInfoDetail(json.id);
			}
			if(result.mes=='无权限'){
				ale('您暂无发表评论的权限！');
			}
		},
		error: function () {
			layer();
		}
	});
}



function cliShow(obj){
	if($(obj).text() == '全部'){
		$('.pinglunbox').hide();
		$('.pinglunbox').filter(function(){
			return $(this).hasClass('Y');
		}).show();
		$(obj).text('楼主');
		
		$('#remindBox').show();
		$('#remindName').text('只看楼主');
		$('#remindBox').fadeOut(1500);
	}else if($(obj).text() == '楼主'){
		$('.pinglunbox').hide();
		$('.pinglunbox').filter(function(){
			return $(this).attr('resId') == $('.doctor_name').eq(0).attr('loginid');
		}).show();
		
		
		$(obj).text('自己');
		$('#remindBox').show();
		$('#remindName').text('只看自己');
		$('#remindBox').fadeOut(1500);
	}else if($(obj).text() == '自己'){
		$('.pinglunbox').show();
		$(obj).text('全部');
		$('#remindBox').show();
		$('#remindName').text('显示全部');
		$('#remindBox').fadeOut(1500);
	}
}

var categoryId=window.location.search.substring(1).split('&&')[0];
var typename = decodeURIComponent(window.location.search.substring(1).split('&&')[1]);
var backWay = decodeURIComponent(window.location.search.substring(1).split('&&')[2]);

$(function(){
	getAllNote();
//	getAll();
});
function getAllNote(){

	$.ajax({
		type:'post',
		url:ringHost+'getMommyRingInfo.action',
		cache:false,
		async:true,
		data:{action:"turnTo",ringTypeId:categoryId},
		success:function(result){
			$('#article_list').empty();
			$('#typeName').text(typename);
			$.ajax({
				type:'post',
				url:ringHost+'getMommyRingInfo.action',
				cache:false,
				async:false,
				data:{action:"allDoctorRingLabelInfo",ringCategory:"Y"},
				success:function(result){
					if(result.mes != '操作成功' || result.someCategoryRingLabelObject == null){
						return false;
					}
					$('.noteTag').empty();
					$('.noteTag').append(
						"<span onclick=\"getAll()\" class='tagName blue'>全部</span>"
					);
					$.each(result.someCategoryRingLabelObject,function(i,content){
						if(content[2] == 'Y'){
							$('.noteTag').append(
								"<span onclick=\"filterNote('"+categoryId+"','"+content[0]+"','"+content[1]+"')\" class='tagName' zjid='"+content[0]+"'>"+content[1]+"</span>"
							);
						}
					});
				},
				error: function () {
					layer();
				}
			});

			if(location.search.split('&&')[location.search.split('&&').length-1] != encodeURIComponent("全部")){


				$('.tagName').filter(function(){
					return $(this).text() == decodeURIComponent(location.search.split('&&')[location.search.split('&&').length-1]);
				}).trigger('click');
				return false;
			}

			if(result.somePostInfo != null){

				for(var i=0;i<result.somePostInfo.length;i++){
					var oldTime = Number(Date.parse(new Date(result.somePostInfo[i][3])))/1000;
					var newTime = Number(Date.parse(new Date()))/1000;
					var cha = parseInt((newTime-oldTime)/60);
					if(cha < 60){
						cha = cha+"分钟前";
					}
					else if(cha > 60 && cha < 1440){
						cha = parseInt(cha/60) +"小时前";
					}else{
						cha = result.somePostInfo[i][3].toString().substring(5,16);
					}


					$('#article_list').append(
						"<div class='article_con' onclick=\"goChatSignal('"+result.somePostInfo[i][0]+"')\" zjid='"+result.somePostInfo[i][0]+"'>"+
						"<div class='con_row1'>"+
						"<h3>"+result.somePostInfo[i][1]+"</h3>"+
						"</div>"+
						"<div class='con_row2'>"+
						"<span class='row2_chat'>"+result.somePostInfo[i][5]+"</span>"+
						"<span class='row2_view'>"+result.somePostInfo[i][4]+"</span>"+
						"<span class='row2_user'>"+result.somePostInfo[i][2]+"</span>"+
						"<span class='row2_time' '"+result.somePostInfo[i][3]+"'>"+cha+"</span>"+

						"</div>"+
						"</div>"+
						"<p class='gray_1'></p>"
					);
				}
			}
		},
		error: function () {
			layer();
		}
	});
}
function goChatSignal(zjid){
	window.location.href = "chatSignal.html?id="+zjid+"&str="+encodeURIComponent($('#showTagList').text());
}
function goChatTiezi(){
	window.location.href = 'chatTiezi.html?'+categoryId+"&&"+encodeURIComponent($('#showTagList').text());
}
function tagShow(){

	$('#showTag').animate({
		'top':'-22'
	},function(){
		$(this).animate({
			'top':'-32'
		},100);
	});
}
function tagHide(){
	if($('#showTag').css('top') != '-32px'){
		return false;
	}
	$('#showTag').animate({
		'top':'-22'
	},100,function(){
		$(this).animate({
			'top':'-280'
		});
	});
}
function filterNote(circleId,tagId,tagName){
	var doctorRingId=$.trim(circleId);
	var doctorRingLableId=$.trim(tagId);
	$.ajax({
		type:'post',
		url:ringHost+'getMommyRingInfo.action',
		cache:false,
		async:false,
		data:{action:"postInfoScreen",doctorRingId:doctorRingId,doctorRingLableId:doctorRingLableId},
		success:function(result){
			$("span.tagName").removeClass('blue');
			$("span.tagName").filter(function(){
				return $(this).text() == tagName;
			}).addClass('blue');
			tagHide();

			if(result.mes != '操作成功' || result.somePostInfo == null){
				return false;
			}
			$('#article_list').empty();
			for(var i=0;i<result.somePostInfo.length;i++){
				var oldTime = Number(Date.parse(new Date(result.somePostInfo[i][3])))/1000;
				var newTime = Number(Date.parse(new Date()))/1000;
				var cha = parseInt((newTime-oldTime)/60);
				if(cha < 60){
					cha = cha+"分钟前";
				}
				else if(cha > 60 && cha < 1440){
					cha = parseInt(cha/60) +"小时前";
				}else{
					cha = result.somePostInfo[i][3].toString().substring(5,16);
				}


				$('#article_list').append(
					"<div class='article_con' onclick=\"goChatSignal('"+result.somePostInfo[i][0]+"')\" zjid='"+result.somePostInfo[i][0]+"'>"+
					"<div class='con_row1'>"+
					"<h3>"+result.somePostInfo[i][1]+"</h3>"+
					"</div>"+
					"<div class='con_row2'>"+
					"<span class='row2_chat'>"+result.somePostInfo[i][5]+"</span>"+
					"<span class='row2_view'>"+result.somePostInfo[i][4]+"</span>"+
					"<span class='row2_user'>"+result.somePostInfo[i][2]+"</span>"+
					"<span class='row2_time' '"+result.somePostInfo[i][3]+"'>"+cha+"</span>"+

					"</div>"+
					"</div>"+
					"<p class='gray_1'></p>"
				);
			}
			$('#showTagList').text(tagName);

		},
		error: function () {
			layer();
		}
	});
	var arr = location.search.split('&&');
	arr.pop();
	arr.push(encodeURIComponent($('#showTagList').text()));
	var newSearch = '';
	$.each(arr,function(i,content){
		newSearch = newSearch +"&&" +content;
	});
	window.history.replaceState({},'',"chatAll.html"+decodeURIComponent(newSearch.substring(2)));
}
function getAll(){
	tagHide();
	$('#showTagList').text('全部');

	var arr = location.search.split('&&');
	arr.pop();
	arr.push(encodeURIComponent("全部"));
	var newSearch = '';
	$.each(arr,function(i,content){
		newSearch = newSearch +"&&" +content;
	});
	window.history.pushState({},'',"chatAll.html"+decodeURIComponent(newSearch.substring(2)));
	getAllNote();
}

	

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="com.java.ec"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.java.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<html>
<head>
<title>生儿家庭访视记录表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- css/js -->
<link
	href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/style2.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/css/pop_button.css"
	rel="stylesheet" type="text/css">
<style>
input[type="radio"],input[type="checkbox"] {
	margin: 0 3px 0 8px;
	vertical-align: -3px;
}

input[type="text"] {
	width: 115px;
}

#choking_time,#referral_reason {
	display: none;
	float: left;
	margin-left: 15px;
	line-height: 38px;
}

label {
	display: inline-block;
	width: 110px;
	height: 38px;
	line-height: 38px;
	border-right: 1px solid #B7C6DD;
	text-align: right;
	padding-right: 8px;
}

#parents_information label {
	width: 55px;
}

table {
	min-width: 915px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/environment/js/mootools/mootools-1.2-more.js"></script>

<!-- ecTable列表 -->
<link
	href="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/css/ec.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/ectable/EcTable.js"></script>

<!-- environment弹出窗口样式 -->
<link
	href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/SimpleUI/css/SimpleUI.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/environment/js/environment/environment.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/environment/js/SimpleUI/SimpleUI.js"></script>

<!-- formcheck表单验证 -->
<link
	href="${pageContext.request.contextPath}/environment/themes/winxp/resource/skin/blue/formcheck/css/formcheck.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/environment/js/formcheck/formcheck.js"></script>

<!-- alert消息提示 -->
<link
	href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/skin/qq/ymPrompt.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/ymPrompt.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/message/ymPrompt/showMsg.js"></script>

<!-- attachment组件  -->
<link
	href="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/css/attachment.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/attachment/js/attachment.js"></script>

<!-- util 工具 js -->
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils2.js"></script>-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/utils2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/product/theme/winxp/resource/js/wdate/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>

<script type="text/javascript">
	var $j = jQuery.noConflict();
	window.addEvent('domready', function() {
		fc = new FormCheck('form_handle', {
			display : {
				showErrors : 1
			}
		});
	});
	//页面加载时执行，把存在数据的复选框选中
        $j(function(){
			//母亲妊娠期患病情况复选
			//先获取到被选中的值
        	var mumGestationIlls = "${phNeonatalVisitRecord.mumGestationIll}";
			//拿到标签的元素name属性值
        	var mumGestationIll_input = $j("input[name='mumGestationIll']");
			//判断拿到的值不为空
        	if(mumGestationIlls!=null){
				//在把字符串的逗号截取掉
        		mumGestationIlls=mumGestationIlls.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(mumGestationIll_input).each(function(){
	        		//在循环mumGestationIlls去拿到mumGestationIlls的值
	        		for(var i=0,len=mumGestationIlls.length;i<len;i++){
	        			//在判断当前对象mumGestationIll_input取比较获取的值
	        			if($j(this).val()==mumGestationIlls[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
       	
       		//出生情况
      		//先获取到被选中的值
        	var birthConditions = "${phNeonatalVisitRecord.birthCondition}";
			//拿到标签的元素name属性值
        	var birthCondition_input = $j("input[name='birthCondition']");
			//判断拿到的值不为空
        	if(birthConditions!=null){
				//在把字符串的逗号截取掉
        		birthConditions=birthConditions.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(birthCondition_input).each(function(){
	        		//在循环birthConditions去拿到birthConditions的值
	        		for(var i=0,len=birthConditions.length;i<len;i++){
	        			//在判断当前对象birthCondition_input取比较获取的值
	        			if($j(this).val()==birthConditions[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
        	
      		//新生儿疾病筛查	
     		//先获取到被选中的值
	       	var illScreenings = "${phNeonatalVisitRecord.illScreening}";
			//alert("新生儿疾病筛查被选中的值	"+illScreenings);
			//拿到标签的元素name属性值
	       	var illScreening_input = $j("input[name='illScreening']");
			//判断拿到的值不为空
	       	if(illScreenings!=null){
				//在把字符串的逗号截取掉
	       		illScreenings=illScreenings.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(illScreening_input).each(function(){
	        		//在循环illScreenings去拿到illScreenings的值
	        		for(var i=0,len=illScreenings.length;i<len;i++){
	        			//在判断当前对象illScreening_input取比较获取的值
	        			if($j(this).val()==illScreenings[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
	       	}
       
       		//面色
       		//先获取到被选中的值
        	var faces = "${phNeonatalVisitRecord.face}";
			//拿到标签的元素name属性值
        	var face_input = $j("input[name='face']");
			//判断拿到的值不为空
        	if(faces!=null){
				//在把字符串的逗号截取掉
        		faces=faces.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(face_input).each(function(){
	        		//在循环faces去拿到faces的值
	        		for(var i=0,len=faces.length;i<len;i++){
	        			//在判断当前对象face_input取比较获取的值
	        			if($j(this).val()==faces[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
        	
        	//黄疸部位
        	//先获取到被选中的值
        	var locationJaundices = "${phNeonatalVisitRecord.locationJaundice}";
			//拿到标签的元素name属性值
        	var locationJaundice_input = $j("input[name='locationJaundice']");
			//判断拿到的值不为空
        	if(locationJaundices!=null){
				//在把字符串的逗号截取掉
        		locationJaundices=locationJaundices.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(locationJaundice_input).each(function(){
	        		//在循环locationJaundices去拿到locationJaundices的值
	        		for(var i=0,len=locationJaundices.length;i<len;i++){
	        			//在判断当前对象locationJaundice_input取比较获取的值
	        			if($j(this).val()==locationJaundices[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
			//前卤
        	//先获取到被选中的值
        	var bregmaSelects = "${phNeonatalVisitRecord.bregmaSelect}";
			//拿到标签的元素name属性值
        	var bregmaSelect_input = $j("input[name='bregmaSelect']");
			//判断拿到的值不为空
        	if(bregmaSelects!=null){
				//在把字符串的逗号截取掉
        		bregmaSelects=bregmaSelects.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(bregmaSelect_input).each(function(){
	        		//在循环bregmaSelects去拿到bregmaSelects的值
	        		for(var i=0,len=bregmaSelects.length;i<len;i++){
	        			//在判断当前对象bregmaSelect_input取比较获取的值
	        			if($j(this).val()==bregmaSelects[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//眼外观
        	//先获取到被选中的值
        	var eyeAppearances = "${phNeonatalVisitRecord.eyeAppearance}";
			//拿到标签的元素name属性值
        	var eyeAppearance_input = $j("input[name='eyeAppearance']");
			//判断拿到的值不为空
        	if(eyeAppearances!=null){
				//在把字符串的逗号截取掉
        		eyeAppearances=eyeAppearances.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(eyeAppearance_input).each(function(){
	        		//在循环eyeAppearances去拿到eyeAppearances的值
	        		for(var i=0,len=eyeAppearances.length;i<len;i++){
	        			//在判断当前对象eyeAppearance_input取比较获取的值
	        			if($j(this).val()==eyeAppearances[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//四肢活动度
        	//先获取到被选中的值
        	var limbActivitys = "${phNeonatalVisitRecord.limbActivity}";
			//拿到标签的元素name属性值
        	var limbActivity_input = $j("input[name='limbActivity']");
			//判断拿到的值不为空
        	if(limbActivitys!=null){
				//在把字符串的逗号截取掉
        		limbActivitys=limbActivitys.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(limbActivity_input).each(function(){
	        		//在循环limbActivitys去拿到limbActivitys的值
	        		for(var i=0,len=limbActivitys.length;i<len;i++){
	        			//在判断当前对象limbActivity_input取比较获取的值
	        			if($j(this).val()==limbActivitys[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//耳外观
        	//先获取到被选中的值
        	var earAppearances = "${phNeonatalVisitRecord.earAppearance}";
			//拿到标签的元素name属性值
        	var earAppearance_input = $j("input[name='earAppearance']");
			//判断拿到的值不为空
        	if(earAppearances!=null){
				//在把字符串的逗号截取掉
        		earAppearances=earAppearances.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(earAppearance_input).each(function(){
	        		//在循环earAppearances去拿到earAppearances的值
	        		for(var i=0,len=earAppearances.length;i<len;i++){
	        			//在判断当前对象earAppearance_input取比较获取的值
	        			if($j(this).val()==earAppearances[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//颈部包块
        	//先获取到被选中的值
        	var neckMasss = "${phNeonatalVisitRecord.neckMass}";
			//拿到标签的元素name属性值
        	var neckMass_input = $j("input[name='neckMass']");
			//判断拿到的值不为空
        	if(neckMasss!=null){
				//在把字符串的逗号截取掉
        		neckMasss=neckMasss.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(neckMass_input).each(function(){
	        		//在循环earAppearances去拿到earAppearances的值
	        		for(var i=0,len=neckMasss.length;i<len;i++){
	        			//在判断当前对象earAppearance_input取比较获取的值
	        			if($j(this).val()==neckMasss[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//鼻
        	//先获取到被选中的值
        	var noses = "${phNeonatalVisitRecord.nose}";
			//拿到标签的元素name属性值
        	var nose_input = $j("input[name='nose']");
			//判断拿到的值不为空
        	if(noses!=null){
				//在把字符串的逗号截取掉
        		noses=noses.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(nose_input).each(function(){
	        		//在循环noses去拿到noses的值
	        		for(var i=0,len=noses.length;i<len;i++){
	        			//在判断当前对象nose_input取比较获取的值
	        			if($j(this).val()==noses[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//皮肤
        	//先获取到被选中的值
        	var skins = "${phNeonatalVisitRecord.skin}";
			//拿到标签的元素name属性值
        	var skin_input = $j("input[name='skin']");
			//判断拿到的值不为空
        	if(skins!=null){
				//在把字符串的逗号截取掉
        		skins=skins.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(skin_input).each(function(){
	        		//在循环skins去拿到skins的值
	        		for(var i=0,len=skins.length;i<len;i++){
	        			//在判断当前对象skin_input取比较获取的值
	        			if($j(this).val()==skins[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//口腔
        	//先获取到被选中的值
        	var mouses = "${phNeonatalVisitRecord.mouse}";
			//拿到标签的元素name属性值
        	var mouse_input = $j("input[name='mouse']");
			//判断拿到的值不为空
        	if(mouses!=null){
				//在把字符串的逗号截取掉
        		mouses=mouses.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(mouse_input).each(function(){
	        		//在循环mouses去拿到mouses的值
	        		for(var i=0,len=mouses.length;i<len;i++){
	        			//在判断当前对象mouse_input取比较获取的值
	        			if($j(this).val()==mouses[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//肛门
        	//先获取到被选中的值
        	var anuss = "${phNeonatalVisitRecord.anus}";
			//拿到标签的元素name属性值
        	var anus_input = $j("input[name='anus']");
			//判断拿到的值不为空
        	if(anuss!=null){
				//在把字符串的逗号截取掉
        		anuss=anuss.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(anus_input).each(function(){
	        		//在循环mouses去拿到mouses的值
	        		for(var i=0,len=anuss.length;i<len;i++){
	        			//在判断当前对象mouse_input取比较获取的值
	        			if($j(this).val()==anuss[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//心肺听诊
        	//先获取到被选中的值
        	var heartLungAuscultations = "${phNeonatalVisitRecord.heartLungAuscultation}";
			//拿到标签的元素name属性值
        	var heartLungAuscultation_input = $j("input[name='heartLungAuscultation']");
			//判断拿到的值不为空
        	if(heartLungAuscultations!=null){
				//在把字符串的逗号截取掉
        		heartLungAuscultations=heartLungAuscultations.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(heartLungAuscultation_input).each(function(){
	        		//在循环heartLungAuscultations去拿到heartLungAuscultations的值
	        		for(var i=0,len=heartLungAuscultations.length;i<len;i++){
	        			//在判断当前对象heartLungAuscultation_input取比较获取的值
	        			if($j(this).val()==heartLungAuscultations[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//心肺听诊
        	//先获取到被选中的值
        	var aedeas = "${phNeonatalVisitRecord.aedea}";
			//拿到标签的元素name属性值
        	var aedea_input = $j("input[name='aedea']");
			//判断拿到的值不为空
        	if(aedeas!=null){
				//在把字符串的逗号截取掉
        		aedeas=aedeas.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(aedea_input).each(function(){
	        		//在循环aedeas去拿到aedeas的值
	        		for(var i=0,len=aedeas.length;i<len;i++){
	        			//在判断当前对象aedea_input取比较获取的值
	        			if($j(this).val()==aedeas[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//腹部触诊
        	//先获取到被选中的值
        	var abdominalTouchs = "${phNeonatalVisitRecord.abdominalTouch}";
			//拿到标签的元素name属性值
        	var abdominalTouch_input = $j("input[name='abdominalTouch']");
			//判断拿到的值不为空
        	if(abdominalTouchs!=null){
				//在把字符串的逗号截取掉
        		abdominalTouchs=abdominalTouchs.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(abdominalTouch_input).each(function(){
	        		//在循环abdominalTouchs去拿到abdominalTouchs的值
	        		for(var i=0,len=abdominalTouchs.length;i<len;i++){
	        			//在判断当前对象aedea_input取比较获取的值
	        			if($j(this).val()==abdominalTouchs[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//脊柱
        	//先获取到被选中的值
        	var spines = "${phNeonatalVisitRecord.spine}";
			//拿到标签的元素name属性值
        	var spine_input = $j("input[name='spine']");
			//判断拿到的值不为空
        	if(spines!=null){
				//在把字符串的逗号截取掉
        		spines=spines.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(spine_input).each(function(){
	        		//在循环spines去拿到spines的值
	        		for(var i=0,len=spines.length;i<len;i++){
	        			//在判断当前对象spine_input取比较获取的值
	        			if($j(this).val()==spines[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//脐带
        	//先获取到被选中的值
        	var umbilicalCords = "${phNeonatalVisitRecord.umbilicalCord}";
			//拿到标签的元素name属性值
        	var umbilicalCord_input = $j("input[name='umbilicalCord']");
			//判断拿到的值不为空
        	if(umbilicalCords!=null){
				//在把字符串的逗号截取掉
        		umbilicalCords=umbilicalCords.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(umbilicalCord_input).each(function(){
	        		//在循环umbilicalCords去拿到umbilicalCords的值
	        		for(var i=0,len=umbilicalCords.length;i<len;i++){
	        			//在判断当前对象umbilicalCord_input取比较获取的值
	        			if($j(this).val()==umbilicalCords[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
			
        	//指导
        	//先获取到被选中的值
        	var advices = "${phNeonatalVisitRecord.advice}";
			//拿到标签的元素name属性值
        	var advice_input = $j("input[name='advice']");
			//判断拿到的值不为空
        	if(advices!=null){
				//在把字符串的逗号截取掉
        		advices=advices.split(',');
	        	//通过循环input标签的name属性的值，去拿到这个input标签的jquery标签的jquery对象
	        	$j(advice_input).each(function(){
	        		//在循环advices去拿到advices的值
	        		for(var i=0,len=advices.length;i<len;i++){
	        			//在判断当前对象advice_input取比较获取的值
	        			if($j(this).val()==advices[i]){
	        				//值相等就选中
	        				$j(this).attr("checked","checked");
	        			}
	        		}
	        	});
        	}
    });
	
	function doHandle() {
	//把复选框选中的值封装到隐藏域里面
	//母亲妊娠期患病情况复选
		text = $j("input:checkbox[name='mumGestationIll']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_mumGestationIll").val(text);
	var t1 = $j("#phNeonatalVisitRecord_mumGestationIll").val();
	// alert("隐藏域的值>>>>>>"+t1);
	
	//出生情况
		text2 = $j("input:checkbox[name='birthCondition']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_birthCondition").val(text2);
	var t2 = $j("#phNeonatalVisitRecord_birthCondition").val();
	
	//新生儿疾病筛查
		text3 = $j("input:checkbox[name='illScreening']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_illScreening").val(text3);
	var t3 = $j("#phNeonatalVisitRecord_illScreening").val();	
		
	
	//面色
	text4 = $j("input:radio[name='face']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_face").val(text4);
	var t4 = $j("#phNeonatalVisitRecord_face").val();
	
	//黄疸部位
	text5 = $j("input:checked[name='locationJaundice']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_locationJaundice").val(text5);
	var t5 = $j("#phNeonatalVisitRecord_locationJaundice").val();
	
	//前卤
	text6 = $j("input:radio[name='bregmaSelect']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_bregmaSelect").val(text6);
	var t6 = $j("#phNeonatalVisitRecord_bregmaSelect").val();
	
	//眼外观
	text7 = $j("input:radio[name='eyeAppearance']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_eyeAppearance").val(text7);
	var t7 = $j("#phNeonatalVisitRecord_eyeAppearance").val();
	
	//四肢活动度
	text8 = $j("input:radio[name='limbActivity']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_limbActivity").val(text8);
	var t8 = $j("#phNeonatalVisitRecord_limbActivity").val();

	//耳外观
	text9 = $j("input:radio[name='earAppearance']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_earAppearance").val(text9);
	var t9 = $j("#phNeonatalVisitRecord_earAppearance").val();
	
	//颈部包块
	text10 = $j("input:radio[name='neckMass']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_neckMass").val(text10);
	var t10 = $j("#phNeonatalVisitRecord_neckMass").val();
	
	//鼻
	text11 = $j("input:radio[name='nose']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_nose").val(text11);
	var t11 = $j("#phNeonatalVisitRecord_nose").val();
	
	//皮肤
	text12 = $j("input:radio[name='skin']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_skin").val(text12);
	var t12 = $j("#phNeonatalVisitRecord_skin").val();
	
	//口腔
	text13 = $j("input:radio[name='mouse']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_mouse").val(text13);
	var t13 = $j("#phNeonatalVisitRecord_mouse").val();
	
	//肛门
	text14 = $j("input:radio[name='anus']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_anus").val(text14);
	var t14 = $j("#phNeonatalVisitRecord_anus").val();
	
	//心肺听诊
	text15 = $j("input:radio[name='heartLungAuscultation']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_heartLungAuscultation").val(text15);
	var t15 = $j("#phNeonatalVisitRecord_heartLungAuscultation").val();
	
	//外生殖器
	text16 = $j("input:radio[name='aedea']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_aedea").val(text16);
	var t16 = $j("#phNeonatalVisitRecord_aedea").val();
	
	//腹部触诊
	text17 = $j("input:radio[name='abdominalTouch']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_abdominalTouch").val(text17);
	var t17 = $j("#phNeonatalVisitRecord_abdominalTouch").val();
	
	//脊柱
	text18 = $j("input:radio[name='spine']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_spine").val(text18);
	var t18 = $j("#phNeonatalVisitRecord_spine").val();
	
	//脐带
	text19 = $j("input:radio[name='umbilicalCord']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_umbilicalCord").val(text19);
	var t19 = $j("#phNeonatalVisitRecord_umbilicalCord").val();
	
	//指导
	text20 = $j("input:checkbox[name='advice']:checked").map(function(index, elem) {
			return $j(elem).val();
	}).get().join(',');
	$j("#phNeonatalVisitRecord_advice").val(text20);
	var t20 = $j("#phNeonatalVisitRecord_advice").val();
	
	$('form_handle').submit();
	}
	
	function doBack(){
        window.location.href = 
        	"${pageContext.request.contextPath}/publichealth/neonatalvisit/toList.action"
        	+"?accountId="+$j("#accountId",parent.document).val()
        			+"&babyId="+$j("#babyId",parent.document).val();
	}

	function doSubmit() {
		confirmMsg(
				"确定提交?",
				function(tp) {
					if (tp == 'ok') {
						$('form_handle').action = "<s:url namespace="dataEntry" action="submitManagementOrg" includeParams="true"/>";
						$('form_handle').click();
					}
				});
	}
	function download(exchangeId) {
		window.location.href = '<s:url action="downloadAttachment" includeParams="true"/>'
				+ '?exchange.exchangeId=' + exchangeId;
	}
	function delAttachment() {
		confirmMsg(
				"确定删除！！",
				function(tp) {
					if (tp == 'ok') {
						document.getElementById('fileAttachmentId').style.display = 'none';
						document.getElementById('exchange.exchangePname').value = '';
					}
				});
	}

	//检测附件是否是合法的后缀  inputStr：输入文件值    description：描述信息
	function checkAttachment(inputStr, description) {
		var photostr = ".doc;.docx;.xls;.xlsx;.pdf;.gif;.jpg;.jpeg;.bmp;.txt";
		var isok = true;
		if (inputStr.lastIndexOf(".") < 0) {
			isok = false;
		}
		iPos = inputStr.lastIndexOf(".");
		var fileext = inputStr.substring(iPos, inputStr.length).toLowerCase();
		if (photostr.lastIndexOf(fileext) < 0) {
			isok = false;
		}
		if ((description != null) && (description.length > 0) && isok == false) {
			//alert(description+"不是合法的文件,必须为"+photostr+"格式的!");
			showMsg(description + "不是合法的文件,必须为" + photostr + "格式的!");
		}
		return isok;
	}
	//帮助
	function help() {
		var date = new Date();
		var action = '<s:url namespace="/dataEntry" action="help" includeParams="true"/>';
		environment.dialog
				.open({
					url : action + '?formCode=ManagementOrg&_t='
							+ date.getTime(),
					width : window.getCoordinates().width - 200,
					height : window.getCoordinates().height,
					icon : '${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/images/display.gif',
					title : '帮助详情'
				});
	}
	
	$j(function(){
		$j("#accountId").val($j("#accountId",parent.document).val());
		$j("#babyId").val($j("#babyId",parent.document).val());
	});
</script>
</head>

<body>
	<s:actionmessage theme="popwind" />
	<div id="wz">
		<%-- <ap:step></ap:step> --%>
		<div class="wz_right">
			<div class="but01">
				<div class="pop_button_bar">
					<%-- <span><a href="javascript:doHandle();"
						class="pop_button_blue">保存</a></span> --%>
					<span><a href="javascript:doBack();"
						class="pop_button_green">返回</a>
					</span> 
				</div>
			</div>
		</div>
	</div>

	<form id="form_handle" name="form_handle"
		action="${pageContext.request.contextPath}/publichealth/neonatalvisit/saveOrUpdatePhNeonatalVisitRecord.action"
		method="post">
		<input name="save" id="save" type="submit" value="save" style="display: none;"> 
		<input type="hidden" name="phNeonatalVisitRecord.id" value="${phNeonatalVisitRecord.id}"> 
		<input type="hidden" id="accountId" name="phNeonatalVisitRecord.familyAccountInfo.id" value="${phNeonatalVisitRecord.familyAccountInfo.id}"> 
		<input type="hidden" id="babyId" name="phNeonatalVisitRecord.consultBabyInfo.id" value="${phNeonatalVisitRecord.consultBabyInfo.id}"> 
		<div class="eXtremeTable">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top"><img
						src="${pageContext.request.contextPath}/product/theme/winxp/resource/skin/blue/usap/images/null.gif"
						width="50" height="5"></td>
				</tr>
			</table>
			<table width="100%" class="tableRegion2">
				<tr>			
					<td colspan="2"><label>姓名</label> <input type="text"
						name="phNeonatalVisitRecord.name" value="${phNeonatalVisitRecord.name}"
						class="validate['required','length[20]','required'] text3"> <span
						class="text4">*</span></td>
					<td colspan="2"><label>编号</label> <input type="text"
						name="phNeonatalVisitRecord.number"
						value="${phNeonatalVisitRecord.number}"
						class="validate['length[30]','number'] text3"></td>
				</tr>
				<tr>
					<td colspan="2"><label>性别</label> 
					<s:select cssStyle="width:100px;" cssClass="text3" 
					list="#{'男':'男','女':'女','未知的性别':'未知的性别','未说明的性别':'未说明的性别'}"
					listKey="key" theme="simple" listValue="value" value=""
					name="phNeonatalVisitRecord.sex" headerKey="未知的性别"
					onchange="referral(this)" /> <span class="text4">*</span></td>
					<td colspan="2"><label>出生日期</label> <input type="text"
						name="phNeonatalVisitRecord.birthday"
						value="<fmt:formatDate value='${phNeonatalVisitRecord.birthday}' pattern='yyyy-MM-dd'/>"
						class="validate['required','length[20]'] text3 Wdate"
						onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
						<span class="text4">*</span></td>
				</tr>
				<tr>
					<td colspan="2"><label>身份证号</label> <input type="text"
						name="phNeonatalVisitRecord.idCardNum" value="${phNeonatalVisitRecord.idCardNum}"
						class="validate['required','length[20]','IDCard'] text3"> <span
						class="text4">*</span></td>
					<td colspan="2"><label>家庭住址</label> <input type="text"
						name="phNeonatalVisitRecord.familyAddress"
						value="${phNeonatalVisitRecord.familyAddress}"
						class="validate['length[30]'] text3"></td>
				</tr>
				<tr>
					<td colspan=" 4 " style="padding:0">
						<table cellspacing='0' width="100%" id="parents_information">
							<tr>
								<td style="text-align:right;">父亲</td>
								<td><label>姓名</label> <input type="text"
									name="phNeonatalVisitRecord.dadName" value="${phNeonatalVisitRecord.dadName}"
									class="validate['required','length[20]'] text3">
								</td>
								<td><label>职业</label> <input type="text"
									name="phNeonatalVisitRecord.dadProfession" value="${phNeonatalVisitRecord.dadProfession}"
									class="validate['required','length[20]'] text3">
								</td>
								<td><label>联系电话</label> <input type="text"
									name="phNeonatalVisitRecord.dadPhone" value="${phNeonatalVisitRecord.dadPhone}"
									class="validate['required','phone','length[20]'] text3">
								</td>
								<td><label>出生日期</label> <input type="text"
									name="phNeonatalVisitRecord.dadBirthday"
									value="${phNeonatalVisitRecord.dadBirthday}"
									class="validate['required','length[20]'] text3 Wdate"
									onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
								</td>
							</tr>
							<tr>
								<td style="border-bottom:none;width:49px;text-align:right;">母亲</td>
								<td style="border-bottom:none"><label>姓名</label> <input
									type="text" name="phNeonatalVisitRecord.mumName"
									value="${phNeonatalVisitRecord.mumName}"
									class="validate['required','length[20]'] text3">
								</td>
								<td style="border-bottom:none"><label>职业</label> <input
									type="text" name="phNeonatalVisitRecord.mumProfession"
									value="${phNeonatalVisitRecord.mumProfession}"
									class="validate['required','length[20]'] text3">
								</td>
								<td style="border-bottom:none"><label>联系电话</label> <input
									type="text" name="phNeonatalVisitRecord.mumPhone"
									value="${phNeonatalVisitRecord.mumPhone}"
									class="validate['required','phone','length[20]'] text3">
								</td>
								<td style="border-bottom:none"><label>出生日期</label> <input
									type="text" name="phNeonatalVisitRecord.mumBirthday"
									value='${phNeonatalVisitRecord.mumBirthday}'
									class="validate['required','length[20]'] text3 Wdate"
									onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				
				<tr>
					<td width="33%"><label>出生孕周</label> <input type="text"
						name="phNeonatalVisitRecord.gestationWeeks" value="${phNeonatalVisitRecord.gestationWeeks}"
						class="validate['fax','length[20]','number'] text3"> <span>周</span>
					</td>
					<td colspan="3"><label>母亲妊娠期患病情况</label> 
						<input name="mumGestationIll" type="checkbox" value="糖尿病 "/>糖尿病 
						<input name="mumGestationIll" type="checkbox" value="妊娠期高血压 " />妊娠期高血压 
						<input name="mumGestationIll" type="checkbox" value="其他" />其他
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.mumGestationIllOther"
							value="${phNeonatalVisitRecord.mumGestationIllOther}"
								class="validate['length[20]'] text3">
						<input type="hidden" id="phNeonatalVisitRecord_mumGestationIll" name="phNeonatalVisitRecord.mumGestationIll" value="${phNeonatalVisitRecord.mumGestationIll}" class="validate['length[100]'] text3">	
					</td>
				</tr>
				<tr>
					<td colspan="2" width="44%"><label>助产机构名称</label> <input
						type="text" name="phNeonatalVisitRecord.midwiferyOrganizationName" value="${phNeonatalVisitRecord.midwiferyOrganizationName}"
						class="validate['fax','length[20]'] text3"></td>
					<td colspan="2"><label>是否有畸形</label> <s:select
							cssStyle="width:100px;" cssClass="text3"
							list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
							theme="simple" name="phNeonatalVisitRecord.isAbnormal"
							value='phNeonatalVisitRecord.isAbnormal' /></td>
				</tr>
				<tr>
					<td colspan="4" style="overflow:hidden;">
						<div id="choking" style="float:left">
							<label>新生儿窒息</label>
							<s:select cssStyle="width:100px;" cssClass="text3"
								list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
								theme="simple" name="phNeonatalVisitRecord.newbothAsphyxia"
								value="phNeonatalVisitRecord.newbothAsphyxia"
								onchange="babyChoking(this)" />
						</div>
						<div id="choking_time">
							<label style="width:114px">Apgar评分</label>
							<s:select cssStyle="width:100px;" cssClass="text3"
								list="#{'1分钟':'1分钟','5分钟':'5分钟','不详'	:'不详'}" listKey="key"
								listValue="value" theme="simple" name="phNeonatalVisitRecord.apgar"
								value='' />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><label>出生情况</label> 
						<input name=birthCondition type="checkbox" value="顺产 "/>顺产 
						<input name="birthCondition" type="checkbox" value="胎头吸引" />胎头吸引 
						<input name="birthCondition" type="checkbox" value="产钳" />产钳
						<input name="birthCondition" type="checkbox" value="剖宫 " />剖宫 
						<input name="birthCondition" type="checkbox" value="双多胎" />双多胎
					    <input name="birthCondition" type="checkbox" value="臀位 " />臀位 
					    <input name="birthCondition" type="checkbox" value="其他" />其他
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.birthConditionOther"
							value="${phNeonatalVisitRecord.birthConditionOther}"
								class="validate['length[20]'] text3">
						<input type="hidden" id="phNeonatalVisitRecord_birthCondition" name="phNeonatalVisitRecord.birthCondition" value="${phNeonatalVisitRecord.birthCondition}" class="validate['length[100]'] text3">		
					</td>
				</tr>
				<tr>
					<td colspan="4"><label>新生儿听力筛查</label> <s:select
							cssStyle="width:100px;" cssClass="text3"
							list="#{'通过':'通过','未通过':'未通过','未筛查':'未筛查','不详':'不详'}"
							listKey="key" listValue="value" theme="simple"
							name="phNeonatalVisitRecord.hearingScreening" value='phNeonatalVisitRecord.hearingScreening' />
					</td>
				</tr>
				<tr>
					<td colspan="4"><label>新生儿疾病筛查</label> 
						<input name="illScreening" type="checkbox" value="甲低"/>甲低 
						<input name="illScreening" type="checkbox" value="苯丙酮尿症" />苯丙酮尿症 
						<input name="illScreening" type="checkbox" value="其他遗传代谢病 " />其他遗传代谢病   
						其它<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.illScreeningOther"
							value="${phNeonatalVisitRecord.illScreeningOther}"
							class="validate['length[20]'] text3">
						<input type="hidden" id="phNeonatalVisitRecord_illScreening" name="phNeonatalVisitRecord.illScreening" value="${phNeonatalVisitRecord.illScreening}" >	
					</td>
				</tr>
				<tr>
					<td colspan=" 4 " style="padding:0">
						<table cellspacing='0' width="100%">
							<tr>
								<td><label>新生儿出生体重</label> <input type="text"
									name="phNeonatalVisitRecord.bornWeight" value="${phNeonatalVisitRecord.bornWeight}"
									class="validate['fax','length[20]'] text3"> <span>kg</span>
								</td>
								<td><label>目前体重</label> <input type="text"
									name="phNeonatalVisitRecord.nowWeight" value="${phNeonatalVisitRecord.nowWeight}"
									class="validate['fax','length[20]'] text3"> <span>kg</span>
								</td>
								<td><label>出生身长</label> <input type="text"
									name="phNeonatalVisitRecord.bornHeight" value="${phNeonatalVisitRecord.bornHeight}"
									class="validate['fax','length[20]'] text3"> <span>cm</span>
								</td>
							</tr>
							
							<tr>
								<td><label>喂养方式</label> <s:select cssStyle="width:100px;"
										cssClass="text3" list="#{'纯母乳':'纯母乳','混合':'混合','人工':'人工'}"
										listKey="key" listValue="value" theme="simple"
										name="phNeonatalVisitRecord.feedingPatterns" value='phNeonatalVisitRecord.feedingPatterns' />
								</td>
								<td><label>吃奶量</label> <input type="text"
									name="phNeonatalVisitRecord.feedingAmount" value="${phNeonatalVisitRecord.feedingAmount}"
									class="validate['fax','length[20]'] text3"> <span>ml/次</span>
								</td>
								<td><label>吃奶次数</label> <input type="text"
									name="phNeonatalVisitRecord.feedingTimes" value="${phNeonatalVisitRecord.feedingTimes}"
									class="validate['fax','length[20]'] text3"> <span>次/日</span>
								</td>
							</tr>
							<tr>
								<td><label>呕吐</label> <s:select cssStyle="width:100px;"
										cssClass="text3" list="#{'无':'无','有':'有'}" listKey="key"
										listValue="value" theme="simple"
										name="phNeonatalVisitRecord.vomit" value='phNeonatalVisitRecord.vomit' />
								</td>
								<td><label>大便</label> <s:select cssStyle="width:100px;"
										cssClass="text3" list="#{'糊状':'糊状','稀':'稀'}" listKey="key"
										listValue="value" theme="simple"
										name="phNeonatalVisitRecord.stool" value='phNeonatalVisitRecord.stool' />
								</td>
																<td><label>大便次数</label> <input type="text"
									name="phNeonatalVisitRecord.stoolTimes" value="${phNeonatalVisitRecord.stoolTimes}"
									class="validate['fax','length[20]'] text3"> <span>次/日</span>
								</td>
							</tr>
							<tr>
								<td style="border-bottom:none"><label>体温</label> <input
									type="text" name="phNeonatalVisitRecord.temperature"
									value="${phNeonatalVisitRecord.temperature}"
									class="validate['fax','length[20]'] text3"> <span>℃</span>
								</td>
								<td style="border-bottom:none"><label>脉搏</label> <input
									type="text" name="phNeonatalVisitRecord.pulseRate"
									value="${phNeonatalVisitRecord.pulseRate}"
									class="validate['fax','length[20]'] text3"> <span>次/分钟</span>
								</td>
								<td style="border-bottom:none"><label>呼吸频率</label> <input
									type="text" name="phNeonatalVisitRecord.breathingRate"
									value="${phNeonatalVisitRecord.breathingRate}"
									class="validate['fax','length[20]'] text3"> <span>次/分钟</span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>面色</label> 
						<input type="radio" name="face" value="红润" checked />红润 
						<input type="radio" name="face" value="黄染" />黄染
						<input type="radio" name="face" value="其他" />其他
						<input style="margin-left:3px;width:100px" type="text" name="phNeonatalVisitRecord.faceOther"
							value="${phNeonatalVisitRecord.faceOther}"
								class="validate['length[20]'] text3">
						<input type="hidden" id="phNeonatalVisitRecord_face" name="phNeonatalVisitRecord.face" 
							value="${phNeonatalVisitRecord.face}" >
					</td>
					<td colspan="2"><label>黄疸部位</label> 
						<input name="locationJaundice" type="checkbox" value="面部"/>面部
						<input name="locationJaundice" type="checkbox"value="躯干" />躯干 
						<input name="locationJaundice" type="checkbox" value="四肢" />四肢
						<input name="locationJaundice" type="checkbox" value="手足" />手足
						<input type="hidden" id="phNeonatalVisitRecord_locationJaundice" name="phNeonatalVisitRecord.locationJaundice" value="${phNeonatalVisitRecord.locationJaundice}" />
					</td>
				</tr>
				<tr>
					<td colspan="4"><label>前囟</label> 
					<input type="text"
						name="phNeonatalVisitRecord.bregmaMultiplier" value="${phNeonatalVisitRecord.bregmaMultiplier}"
						class="validate['fax','length[20]'] text3"> <span>cm×</span>
					<input type="text" name="phNeonatalVisitRecord.bregmaMultiplicand"
						value="${phNeonatalVisitRecord.bregmaMultiplicand}"
						class="validate['fax','length[20]'] text3"> <span>cm</span>
						
						<input type="radio" name="bregmaSelect" value="正常" checked />正常 
						<input type="radio" name="bregmaSelect" value="膨隆" />膨隆 
						<input type="radio" name="bregmaSelect" value="凹陷" />凹陷
						<input type="radio" name="bregmaSelect" value="其他" />其他
						<input type="hidden" id="phNeonatalVisitRecord_bregmaSelect" name="phNeonatalVisitRecord.bregmaSelect" value="${phNeonatalVisitRecord.bregmaSelect}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.bregmaOther"value="${phNeonatalVisitRecord.bregmaOther}"
						class="validate['length[20]'] text3"></td>
				</tr>
				<tr>
					<td colspan="2"><label>眼外观</label> 
						<input type="radio" name="eyeAppearance" value="未见异常 " checked />未见异常 
						<input type="radio" name="eyeAppearance" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_eyeAppearance" name="phNeonatalVisitRecord.eyeAppearance" value="${phNeonatalVisitRecord.eyeAppearance}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.eyeAppearanceException" value="${managementOrg.prepared_by}"
							class="validate['length[20]'] text3">
					</td>
					<td colspan="2"><label>四肢活动度</label> 
					<input type="radio" name="limbActivity" value="未见异常"  />未见异常
					<input type="radio" name="limbActivity" value="异常" />异常
					<input type="hidden" id="phNeonatalVisitRecord_limbActivity" name="phNeonatalVisitRecord.limbActivity" value="${phNeonatalVisitRecord.limbActivity}" />
					<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.limbActivityException" value="${managementOrg.prepared_by}"
						class="validate['length[20]'] text3">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>耳外观</label> 
						<input type="radio" name="earAppearance" value="未见异常" />未见异常 
						<input type="radio" name="earAppearance" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_earAppearance" name="phNeonatalVisitRecord.earAppearance" value="${phNeonatalVisitRecord.earAppearance}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.earAppearanceException"
							value="${managementOrg.prepared_by}" class="validate['length[20]'] text3">
					</td>
					
					<td colspan="2"><label>颈部包块</label> 
					<input type="radio" name="neckMass" value="无" />无 
					<input type="radio" name="neckMass" value="有" />有
					<input type="hidden" id="phNeonatalVisitRecord_neckMass" name="phNeonatalVisitRecord.neckMass" value="${phNeonatalVisitRecord.neckMass}" />
					<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.neckMassRemark" value="${managementOrg.prepared_by}"
						class="validate['length[20]'] text3">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>鼻</label> 
						<input type="radio" name="nose" value="未见异常"  />未见异常 
						<input type="radio" name="nose" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_nose" name="phNeonatalVisitRecord.nose" value="${phNeonatalVisitRecord.nose}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.noseException"
						value="${managementOrg.prepared_by}" class="validate['length[20]'] text3">
					</td>
					
					<td colspan="2"><label>皮肤</label> 
						<input type="radio" name="skin" value="未见异常"  />未见异常 
						<input type="radio" name="skin" value="湿疹" />湿疹 
						<input type="radio" name="skin" value="糜烂 " />糜烂   
						<input type="hidden" id="phNeonatalVisitRecord_skin" name="phNeonatalVisitRecord.skin" value="${phNeonatalVisitRecord.skin}" />
						 其他<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.skinOther" value="${phNeonatalVisitRecord.skinOther}"
							class="validate['length[20]'] text3">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>口腔</label> 
						<input type="radio" name="mouse" value="未见异常"  />未见异常 
						<input type="radio" name="mouse" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_mouse" name="phNeonatalVisitRecord.mouse" value="${phNeonatalVisitRecord.mouse}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.mouseException" value="${phNeonatalVisitRecord.mouseException}"
							class="validate['length[20]'] text3">
					</td>
						
					<td colspan="2"><label>肛门</label> 
						<input type="radio" name="anus" value="未见异常"  />未见异常 
						<input type="radio" name="anus" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_anus" name="phNeonatalVisitRecord.anus" value="${phNeonatalVisitRecord.anus}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.anusException" value="${phNeonatalVisitRecord.anusException}"
							class="validate['length[20]'] text3">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>心肺听诊</label> 
						<input type="radio" name="heartLungAuscultation" value="未见异常"  />未见异常
						<input type="radio" name="heartLungAuscultation" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_heartLungAuscultation" name="phNeonatalVisitRecord.heartLungAuscultation" value="${phNeonatalVisitRecord.heartLungAuscultation}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.heartLungAuscultationException"
							value="${phNeonatalVisitRecord.heartLungAuscultationException}" class="validate['length[20]'] text3">
					</td>
					
					<td colspan="2"><label>外生殖器</label> 
						<input type="radio" name="aedea" value="未见异常"  />未见异常 
						<input type="radio" name="aedea" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_aedea" name="phNeonatalVisitRecord.aedea" value="${phNeonatalVisitRecord.aedea}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.aedeaException"
							value="${phNeonatalVisitRecord.aedeaException}"
								class="validate['length[20]'] text3">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>腹部触诊</label>
						<input type="radio" name="abdominalTouch" value="未见异常 "  />未见异常 
						<input type="radio" name="abdominalTouch" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_abdominalTouch" name="phNeonatalVisitRecord.abdominalTouch" value="${phNeonatalVisitRecord.abdominalTouch}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.abdominalTouchException"
							value="${phNeonatalVisitRecord.abdominalTouchException}" class="validate['length[20]'] text3">
					</td>
					<td colspan="2"><label>脊柱</label> 
						<input type="radio" name="spine" value="未见异常"  />未见异常
						<input type="radio" name="spine" value="异常" />异常
						<input type="hidden" id="phNeonatalVisitRecord_spine" name="phNeonatalVisitRecord.spine" value="${phNeonatalVisitRecord.spine}" />
						<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.spineException"
							value="${phNeonatalVisitRecord.spineException}" class="validate['length[20]'] text3">
					</td>
				</tr>
				
					<tr>
					<td colspan="4"><label>脐带</label> 
					<input type="radio" name="umbilicalCord" value="未脱"  />未脱 
					<input type="radio" name="umbilicalCord" value="脱落" />脱落
				    <input type="radio" name="umbilicalCord" value="脐部有渗出" />脐部有渗出
					<input type="radio" name="umbilicalCord" value="其他" />其他
					<input type="hidden" id="phNeonatalVisitRecord_umbilicalCord" name="phNeonatalVisitRecord.umbilicalCord" value="${phNeonatalVisitRecord.umbilicalCord}" />
					<input style="margin-left:3px" type="text" name="phNeonatalVisitRecord.umbilicalCordOther"
						value="${phNeonatalVisitRecord.umbilicalCordOther}" class="validate['length[20]'] text3"></td>
				</tr>
				<tr>
					<td colspan="4" style="overflow:hidden">
						<div id="referral" style="float:left">
							<label>转诊建议</label>
							<s:select cssStyle="width:100px;" cssClass="text3"
								list="#{'无':'无','有':'有'}" listKey="key" listValue="value"
								theme="simple" name="phNeonatalVisitRecord.isReferral"
								value="phNeonatalVisitRecord.isReferral" onchange="referral(this)" />
						</div>
						<div id="referral_reason">
							<lable>原因</lable>
							<input type="text" name="phNeonatalVisitRecord.ireferralReason"
								value="${phNeonatalVisitRecord.ireferralReason}"
								class="validate['required','phone','length[200]'] text3">
							<lable>机构及诊室</lable>
							<input type="text" name="phNeonatalVisitRecord.ireferralOrg"
								value="${phNeonatalVisitRecord.ireferralOrg}"
								class="validate['required','phone','length[20]'] text3">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4"><label>指导</label> 
						<input name="advice" type="checkbox" value="喂养指导 " />喂养指导 
						<input name="advice" type="checkbox" value="发育指导 " />发育指导 
						<input name="advice" type="checkbox" value="防病指导" />防病指导
						<input name="advice" type="checkbox" value="预防伤害指导" />预防伤害指导 
						<input name="advice" type="checkbox" value="口腔保健指导" />口腔保健指导
						<input type="hidden" id="phNeonatalVisitRecord_advice" name="phNeonatalVisitRecord.advice" value="${phNeonatalVisitRecord.advice}" class="validate['length[100]'] text3">
					</td>
				</tr>
				<tr>
					<td colspan="2"><label>下次访视日期</label> <input type="text"
						name="phNeonatalVisitRecord.nextFollowDate"
						value="<fmt:formatDate value='${phNeonatalVisitRecord.nextFollowDate}' pattern='yyyy-MM-dd'/>"
						class="validate['required','length[20]'] text3 Wdate"
						onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
					</td>
					<td colspan="2"><label>下次访视日随访地点</label> <input
						style="margin-left:3px" type="text"
						name="phNeonatalVisitRecord.nextFollowAddress"
						value="${phNeonatalVisitRecord.nextFollowAddress}"
						class="validate['length[20]'] text3 "></td>
				</tr>
				<tr>
					<td colspan="2"><label>下次随访日期</label> <input type="text"
						name="phNeonatalVisitRecord.nextFollowDate"
						value="<fmt:formatDate value='${phNeonatalVisitRecord.nextFollowDate}' pattern='yyyy-MM-dd'/>"
						class="validate['required','length[20]'] text3 Wdate"
						onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true});">
					</td>
					<td colspan="2"><label>随访医生签名</label> <input
						style="margin-left:3px" type="text"
						name="phNeonatalVisitRecord.doctorName"
						value="${phNeonatalVisitRecord.doctorName}"
						class="validate['length[20]'] text3"></td>
				</tr>
				<%--<tr>--%>
				<%--<td width="15%" height="32" align="right" bgcolor="#F6F9FE">备注：</td>--%>
				<%--<td width="85%" height="32" align="left" bgcolor="#F6F9FE" colspan="3">--%>
				<%--<textarea name="managementOrg.remark" class="validate['length[2000]'] Added_textarea">${managementOrg.remark}</textarea>--%>
				<%--</td>--%>
				<%--</tr>--%>
			</table>
			<br>
		</div>
	</form>
	<script>
		function babyChoking(param) {
			if ($j(param).find("option:selected").val() == "有") {
				$j("#choking_time").show();
			} else {
				$j("#choking_time").hide();
			}
		}
		function referral(param) {
			if ($j(param).find("option:selected").val() == "有") {
				$j("#referral_reason").show();
			} else {
				$j("#referral_reason").hide();
			}
		}
	</script>
</body>
</html>

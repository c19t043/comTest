//上传图片
function img_cli(obj){
	$j(obj).next().click();
}
function handleFiles(obj){ 
	var src = $j(obj).get(0).files[0];
	var imgType = src.name.split('.');
	imgType = imgType[imgType.length-1];//返回后缀名，以兼容部分移动端浏览器
	if(imgType == 'jpg'){
		imgType = 'jpeg';
	}
	if(!(imgType == 'jpeg' || imgType == 'png' || imgType == 'gif')){
		ale('请选择图片文件');
		return false;
	}
// 创建 FileReader 对象 并调用 render 函数来完成渲染.  
	var reader = new FileReader();  
// 绑定load事件自动回调函数  
	var imgData = '';
	reader.onload = function(e){
		if(e.target.result.substring(5,10) != 'image'){
			var imgDataArr = e.target.result.split('base64');
			imgData = imgDataArr[0] + "image/"+imgType+";base64"+imgDataArr[1];
			render(obj,imgData);
		}else{
			render(obj,e.target.result); 
		}    
	};
	// 读取文件内容  
	reader.readAsDataURL(src);
	$j(obj).next().show();
	$j(obj).prev().hide();
};  
//参数，最大高度  
var MAX_HEIGHT = 70;  
// 渲染  
function render(obj,src){  
	var image = new Image();  
	image.onload = function(){  
	    var canvas = $j(obj).next().get(0);
	    var x = image.width;
	    var y = image.height;
	    if(image.height > MAX_HEIGHT) {  
		      // 宽度等比例缩放 *=  
		      image.width *= MAX_HEIGHT / image.height;  
		      image.height = MAX_HEIGHT;  
	    }
	    var ctx = canvas.getContext("2d");  // 获取 canvas的 2d 环境对象,可以理解Context是管理员，canvas是房子 
		ctx.clearRect(0, 0, canvas.width, canvas.height);// canvas清屏
	    canvas.width = 130;  // 重置canvas宽高
	    canvas.height = 130;    
	    ctx.drawImage(image, 0, 0,x,y,0,0,130,130);  // 将图像绘制到canvas上
  };  
  image.src = src;  // 记住必须先绑定事件，才能设置src属性，否则会出同步问题。
  setTimeout(function(){
	    var canvas = $j(obj).next();
	    // 获取Base64编码后的图像数据，格式是字符串  
	    // "data:image/png;base64,"开头,需要在客户端或者服务器端将其去掉，后面的部分可以直接写入文件。  
	    var dataurl = canvas.get(0).toDataURL("image/png"); 
	    // 为安全 对URI进行编码  
	    // data%3Aimage%2Fpng%3Bbase64%2C 开头  
	    var imagedata =  encodeURIComponent(dataurl);
	    $j(obj).siblings("#imgBase64").val(imagedata);
	},100);
}
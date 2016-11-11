//for APP
function wlCommonInit(){

	/*
	 * Application is started in offline mode as defined by a connectOnStartup property in initOptions.js file.
	 * In order to begin communicating with Worklight Server you need to either:
	 * 
	 * 1. Change connectOnStartup property in initOptions.js to true. 
	 *    This will make Worklight framework automatically attempt to connect to Worklight Server as a part of application start-up.
	 *    Keep in mind - this may increase application start-up time.
	 *    
	 * 2. Use WL.Client.connect() API once connectivity to a Worklight Server is required. 
	 *    This API needs to be called only once, before any other WL.Client methods that communicate with the Worklight Server.
	 *    Don't forget to specify and implement onSuccess and onFailure callback functions for WL.Client.connect(), e.g:
	 *    
	 *    WL.Client.connect({
	 *    		onSuccess: onConnectSuccess,
	 *    		onFailure: onConnectFailure
	 *    });
	 *     
	 */
	
	// Common initialization code goes here

}


$(function(){
	$('.header-left p').click(function(){
			window.history.back();
	});
	
	//苹果APP
	//$("#header").css({
	//	"padding-top" : "20px"
	//});
	//$("#container").css({
	//	"margin" : "65px 0 70px"
	//});
});
//验证用户手机号
function checkMobile(userPhone){
    if(!(/^1[3|4|5|6|7|8][0-9]\d{4,8}$/.test(userPhone))){
        return false;
    }else{
        return true;
    }
}

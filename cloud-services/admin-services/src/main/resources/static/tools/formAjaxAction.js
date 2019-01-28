/**
 * ajax统一提交方式
 */
var formAjaxAction = function(opt){
	var loadIndex = parent.layer.load(1, {shade:0.3}); // 遮罩层
	// 默认参数配置
	var defaults = {
		formUrl:"",
		method:"post",
		dataType:"json",
		dataJson:null,
		callBlack:function(){}
	}
	var result=$.extend({},defaults,opt);
	var options = {
        url: result.formUrl,
        type: result.method,
        headers:result.headers,
        async:result.async,
        dataType: result.dataType,
        data: result.dataJson,
        success: function (data) {
        	parent.layer.close(loadIndex);  // 关闭遮罩层
        	result.callBlack(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	parent.layer.close(loadIndex);  // 关闭遮罩层
        	$.fn.modalAlert("请求失败，状态码="+XMLHttpRequest.status, "error");
        }
    };
    $.ajax(options);
}
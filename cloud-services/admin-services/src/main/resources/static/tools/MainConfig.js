/**
 *  全局参数配置 
 */
var MainConfig = {
	contextPath : "/"   //上下文路径
	
	//   get / set 方法
	,setContextPath : function(contextPath){
		this.contextPath = contextPath;
	}
    ,getContextPath:function(){
    	return this.contextPath;
    }
}

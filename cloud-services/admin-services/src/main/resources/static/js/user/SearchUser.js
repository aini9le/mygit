/**
 * 搜索用户信息
 */
var user = {};
$(function(){
	user = new Userinfo();
	user.initIndex();
});

var Userinfo = function(){
	var user = new Object();
	
	user.options = {
			tableName:"#userInfoTable",
	    	dataUrl : MainConfig.getContextPath() + "/sysUser/dataGrid",   // 表格查询路径
	    		
	}; //参数对象
	
	user.initIndex = function () {
		user.loadGrid().init();
		user.buttonInit();
	};
	user.loadGrid = function () {
		var loadGrid = new Object();
	    loadGrid.init = function(){
	    	$(user.options.tableName).bootstrapTable({
	            url: user.options.dataUrl,         //请求后台的URL（*）
	            method: 'get',                      //请求方式（*）
	            toolbar: '#toolbar',                //工具按钮用哪个容器
	            striped: true,                      //是否显示行间隔色
	            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	            pagination: true,                   //是否显示分页（*）
	            sortable: false,                     //是否启用排序
	            sortOrder: "asc",                   //排序方式
	            queryParams: loadGrid.queryParams,//传递参数（*）
	            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	            pageNumber:1,                       //初始化加载第一页，默认第一页
	            pageSize: 10,                       //每页的记录行数（*）
	            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	            strictSearch: false,
	            showColumns: false,                  //是否显示所有的列
	            showRefresh: false,                  //是否显示刷新按钮
	            minimumCountColumns: 2,             //最少允许的列数
	            clickToSelect: true,                //是否启用点击选中行
	            height: 480,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
	            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
	            cardView: false,                    //是否显示详细视图
	            detailView: false,                 //是否显示父子表
	            undefinedText:'',
	            columns: [{
	            	     radio: true
	            	 },{
	            		 field:"userid",
	            		 title:"帐号"
	            	 },{
	            		 field:"name",
	            		 title:"名称"
	            	 },{
	            		 field:"mobile",
	            		 title:"手机号码"
	            	 }
	    	    ],
	    	    onLoadSuccess:function(){
	    	    }
	        });
	    }
	    
	  //得到查询的参数
    	loadGrid.queryParams = function (params) {
	    	var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	    			pageSize: this.pageSize,   //页面大小
	    			pageNumber: this.pageNumber,  //页码
	    			sort: params.sort,  //排序列名
	    			sortOrder: params.order//排位命令（desc，asc）
	    		};
	        return temp;
	    };
	    
    	return loadGrid;
		
	}

	/**
	 * 绑定按钮事件
	 */
	user.buttonInit = function (){
		
		//查询
    	$("#btn_query").click(function(){
    		var files = {
    				"columns[userid]":$("input[name='userid']").val().trim(),
    				"columns[name_like]":$("input[name='name']").val().trim()
    		}
    		var par = {
    	      url: user.options.dataUrl, //重设数据来源
    	      query: files//传到后台的参数
    	     }
    		$(user.options.tableName).bootstrapTable("refresh",par);
    	});
    };
    
 // 表格加载成功后加载按钮事件
    user.successButtonInit = function (){
//	    	var url ="../advertis/saves.do";
    };
    
    user.formData = function(){
    	var rows = $(user.options.tableName).bootstrapTable("getSelections");
    	if(rows.length == 1){
    		return rows[0];
    	}else{
    		$.fn.modalMsg("请选择其中一个用户信息", "error");
    	}
    }
	return user;
}
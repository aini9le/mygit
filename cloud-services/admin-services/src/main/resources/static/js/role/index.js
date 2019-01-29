/**
 * 基础角色
 */
$(function() {
	var module = new Roleinfo();
	module.initIndex();
})

var Roleinfo = function(){
	var role = new Object();
	
	role.options = {
			tableName:"#roleTable",
			openId:"roleEditForm",
	    	dataUrl : MainConfig.getContextPath() + "/role/dataGrid",   // 表格查询路径
	    	editUrl : MainConfig.getContextPath() + "/role/editIndex",  //跳转到编辑页面路径
	    	removeUrl : MainConfig.getContextPath() + "/role/removes",  //删除路径
	    	editFormIndex : MainConfig.getContextPath() + "/role/editIndex"   //
	    		
	}; //参数对象
	
	role.initIndex = function () {
		role.loadGrid().init();
		role.buttonInit();
	};
	role.loadGrid = function () {
		var loadGrid = new Object();
	    loadGrid.init = function(){
	    	$(role.options.tableName).bootstrapTable({
	            url: role.options.dataUrl,         //请求后台的URL（*）
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
	            showColumns: true,                  //是否显示所有的列
	            showRefresh: true,                  //是否显示刷新按钮
	            minimumCountColumns: 2,             //最少允许的列数
	            clickToSelect: false,                //是否启用点击选中行
	            height: 480,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
	            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
	            cardView: false,                    //是否显示详细视图
	            detailView: false,                 //是否显示父子表
	            undefinedText:'',
	            columns: [{
	            	 checkbox: true
	            	 },{
	            		 field:"roleName",
	            		 title:"角色名称"
	            	 },{
	            		 field:"roleInfo",
	            		 title:"角色信息"
	            	 },{
	            		 field:"isEnable",
	            		 title:"是否启用",
	            		 formatter:function(value, row, index){
	            			 if(value == "Y"){
	            				 return "是";
	            			 }
	            			 if(value == "N"){
	            				 return "否";
	            			 }
	            		 }
	            	 },{
	            		 title:"操作",
	            		 formatter:function(value, row, index){
	            			 var html = "";
	            			 
	            			 return html;
	            		 }
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
	role.buttonInit = function (){
		
		//查询
    	$("#btn_query").on("click",function(){
    		var files = {
    				"columns[roleid]":$("input[name='roleid']").val().trim(),
    				"columns[name_like]":$("input[name='name']").val().trim()
    		}
    		var par = {
    	      url: role.options.dataUrl, //重设数据来源
    	      query: files//传到后台的参数
    	     }
    		$(role.options.tableName).bootstrapTable("refresh",par);
    	});
    	
    	//新增
    	$("#btn_add").on("click",function(){
    		role.modalOpen().editOpen(role.options.openId,null);
        });
    	
    	//修改
    	$("#btn_edit").on("click",function(){
            var ids = $(role.options.tableName).bootstrapTable("getSelections");
            console.log(ids);
            if(ids.length==1){
            	role.modalOpen().editOpen(role.options.openId,ids[0].id);
            }else{
        		$.fn.modalMsg("请选择一条需要修改的信息", "error");
        	}
    	});
    	
    	//删除
        $("#btn_delete").on("click",function(){
        	var ids = $(role.options.tableName).bootstrapTable("getSelections");
        	if(ids.length>0){
        		var id = "";
        		var len = ids.length;
        		$.each(ids,function(i,value){
        			id +=value.id;
        			if(i<len-1){
        				id +=",";
        			}
        		});
        		$.fn.modalConfirm("确定删除所选角色吗？",function(index,bool){
        			if(bool){
        				var opt = {ids:id};
        				role.requestGame(role.options.removeUrl,opt);
        				layer.close(index);
        			}
        		});
        	}else{
        		$.fn.modalMsg("请选择需要删除的信息", "error");
        	}
        });
    };
    
 // 表格加载成功后加载按钮事件
    role.successButtonInit = function (){
//	    	var url ="../advertis/saves.do";
    };
    
    role.requestGame = function(url,data){
    	var options = {
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                success: function (msg) {
                	if(msg.status == "SUCCESS"){
                		$.fn.modalAlert("删除成功", "success");
                		window.location.reload();
                	}else{
                		$.fn.modalAlert("error", "error");
                	}
                }
            };
            $.ajax(options);
    } 
    
    /**
     * 修改或新曾弹出框
     */
    role.modalOpen = function (){
    	var open = new Object();
        open.options = {};  // 窗口参数
    	open.editOpen = function(openId,id){
    		$.fn.modalNotFooterOpen({
//    			id :openId,
    			title : "编辑管理员信息",
    			url : role.options.editFormIndex + ((id===undefined || id === null)?"":("?id="+id)),
    			width : "800px",
    			height : "500px"
    		});
    		
    	}
    	return open;
    }
    
	return role;
}

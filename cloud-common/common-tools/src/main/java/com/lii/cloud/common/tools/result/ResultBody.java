package com.lii.cloud.common.tools.result;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 返回的数据对象标准
 * @author Administrator
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBody {

	public static final String SUCCESS="SUCCESS";  //成功的状态
	public static final String ERROR="ERROR";      // 失败的状态
	public static final String SUCCESS_CODE = "200";  //成功的编码
	public static final String ERROR_CODE = "500";   // 失败的编码
	public static final String LOGIN_CODE = "1010";   // 登录编码
	public static final String LOGIN_ERROR_CODE = "1020";   // 登录失败编码
	
	public static final String not_user = "0";   // 没有此用户
	public static final String error_password = "1022"; // 密码错误
	public static final String error_user_locking = "1030"; // 用户 已被锁定
	public static final String error_too_much = "1031"; // 登录错误次数太多
	
	public static final String business_error = "1500";  // 业务异常
	
	public static final String error_per_oper = "1201";  //无权限操作
	
	public static final String error_Illegal_Access = "1205";  //无权限访问对象
	public static final String error_resubmit_Access = "1206";  //请勿重复提交

	public static final String error_meth = "1210";  //没有此方法
	
	public static final String error_index_out = "1215"; //下标越界
	public static final String error_array_index_out = "1216"; //数组下标越界
	
	public static final String error_Class_Cast = "1230"; //类型转换异常

	public static final String error_Illegal_Argument = "1235"; // 计算异常
	
	public static final String error_Null_Pointer = "1240"; // 空指针异常
	
	public static final String error_Arithmetic = "1245"; // 数学运算异常
	
	public static final String QUERY_SUCCESS= "检索成功";
	public static final String QUERY_ERROR= "检索失败";
	public static final String EDIT_SUCCESS= "编辑成功";
	public static final String EDIT_ERROR= "编辑失败";
	public static final String DEL_SUCCESS= "删除成功";
	public static final String DEL_ERROR= "删除失败";
	

	/**
	 * 获取 不带业务数据 的 成功消息对象
	 * @param messge
	 * @return
	 */
	public static ResultBody success(String messge){
		return new ResultBody(SUCCESS,SUCCESS_CODE,messge);
	}
	
	/**
	 * 获取 具有业务数据 的 成功消息对象
	 * @param messge
	 * @param resInfo
	 * @return
	 */
	public static ResultBody success(String messge,ResponseInfo resInfo){
		return new ResultBody(SUCCESS,SUCCESS_CODE,messge,resInfo);
	}
	
	
	
	/**
	 * 获取 不带业务数据 的 失败消息对象
	 * @param messge
	 * @return
	 */
	public static ResultBody error(String messge){
		return new ResultBody(ERROR,ERROR_CODE,messge);
	}
	
	/**
	 * 获取 不带业务数据 的 失败消息对象
	 * @param messge
	 * @return
	 */
	public static ResultBody error(String code,String messge){
		return new ResultBody(ERROR,code,messge);
	}
	
	/**
	 * 获取 具有业务数据 的 失败消息对象
	 * @param messge
	 * @return
	 */
	public static ResultBody error(String messge,ResponseInfo resInfo){
		return new ResultBody(ERROR,ERROR_CODE,messge,resInfo);
	}
	
	/**
	 * 获取 具有业务数据 的 失败消息对象
	 * @param messge
	 * @return
	 */
	public static ResultBody error(String code,String messge,ResponseInfo resInfo){
		return new ResultBody(ERROR,code,messge,resInfo);
	}

	private String status;  // 成功/失败   success/error 
	private String code;    // 编码
	private String message;  //返回消息
	private ResponseInfo info; // 返回对象信息
	
	public ResultBody() {
	}
	
	public ResultBody(String message) {
		this.message = message;
	}

	public ResultBody(String status, String code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ResultBody(String status, String code, String message, ResponseInfo info) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.info = info;
	}
	

	public ResultBody(String message, ResponseInfo info) {
		super();
		this.message = message;
		this.info = info;
	}

	public ResultBody(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResultBody(String status, String message, ResponseInfo info) {
		super();
		this.status = status;
		this.message = message;
		this.info = info;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ResponseInfo getInfo() {
		return info;
	}
	public void setInfo(ResponseInfo info) {
		this.info = info;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

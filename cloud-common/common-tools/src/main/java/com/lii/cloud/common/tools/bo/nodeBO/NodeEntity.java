package com.lii.cloud.common.tools.bo.nodeBO;

import com.lii.cloud.common.tools.result.ResponseInfo;

/**
 * 节点数据存储结构
 */
public class NodeEntity implements ResponseInfo{

	private static final long serialVersionUID = 1L;
    private String id;  //id
    private String name; //  名称
    private String supId; // 上级节点id
    private IBusinessNodeData buNodeData; //子定义业务节点数据
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public IBusinessNodeData getBuNodeData() {
		return buNodeData;
	}
	public void setBuNodeData(IBusinessNodeData buNodeData) {
		this.buNodeData = buNodeData;
	}
    
}

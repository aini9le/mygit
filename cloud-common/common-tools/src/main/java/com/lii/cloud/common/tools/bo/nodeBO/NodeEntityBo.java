package com.lii.cloud.common.tools.bo.nodeBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点数据传递
 */
public class NodeEntityBo extends NodeEntity {

	private static final long serialVersionUID = 1L;
    private List<NodeEntityBo> nodes;  //下级节点集合
    
	public List<NodeEntityBo> getNodes() {
		if(this.nodes == null){
			this.nodes = new ArrayList<NodeEntityBo>();
		}
		return nodes;
	}
	public void setNodes(List<NodeEntityBo> nodes) {
		this.nodes = nodes;
	}
	
}

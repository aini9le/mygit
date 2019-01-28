package com.lii.cloud.admin.tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 批量对象转换
 * @author liyao
 * Create at:2018年6月14日 下午6:40:06
 */
@Component
public class BaseConverter<DO, VO> {

    /**
     * 单个对象转换
     */
    public VO convert(DO from, Class<VO> clazz) {
        if (from == null) {
            return null;
        }
        VO to = null;
        try {
            to = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        convert(from, to);
        return to;
    }

    /**
     * 批量对象转换
     */
    public List<VO> convert(List<DO> fromList, Class<VO> clazz) {
        if (CollectionUtils.isEmpty(fromList)) {
            return  new ArrayList<VO>();
        }
        List<VO> toList = new ArrayList<VO>();
        for (DO from : fromList) {
            toList.add(convert(from, clazz));
        }
        return toList;
    }

    /**
     * 属性拷贝方法，有特殊需求时子类覆写此方法
     */
    public void convert(DO from, VO to) {
        BeanUtils.copyProperties(from, to);
    }
}
package com.zhou.javakc.component.data.jpa.dynamic.restrictions;

import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criterion;
import com.zhou.javakc.component.data.jpa.dynamic.expression.JoinExpression;
import com.zhou.javakc.component.data.jpa.dynamic.expression.LogicalExpression;
import com.zhou.javakc.component.data.jpa.dynamic.expression.SimpleExpression;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 封装公共条件构造容器
 * 用于创建条件表达式
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午6:55:45
 * @Copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class Restrictions {
	
	/** 
     * 等于 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 
     */  
    public static SimpleExpression eq(String fieldName, Object value)
    {  
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.eq);
    }
   
    /** 
     * 不等于 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 
     */  
    public static SimpleExpression ne(String fieldName, Object value) {  
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.ne);
    }  
  
    /** 
     * 模糊匹配 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return
     */  
    public static SimpleExpression like(String fieldName, String value) {  
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.like);
    }  
  
  
    /** 
     * 大于 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 
     */  
    public static SimpleExpression gt(String fieldName, Object value) {  
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.gt);
    }  
  
    /** 
     * 大于等于 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 表达式
     */  
    public static SimpleExpression gte(String fieldName, Object value) {  
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.ge);
    }  
    
    /**
     * 小于
     * @param fieldName 属性名称
     * @param value 属性值
     * @return
     */
    public static SimpleExpression lt(String fieldName, Object value) {
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.lt);
    }
  
    /** 
     * 小于等于 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 
     */  
    public static SimpleExpression lte(String fieldName, Object value) {
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new SimpleExpression (fieldName, value, Criterion.Operator.le);
    }
    
    /** 
     * 并且 
     * @param criterions 
     * @return 
     */  
    public static LogicalExpression and(Criterion... criterions){
        return new LogicalExpression(criterions, Criterion.Operator.and);
    }  
    
    /** 
     * 或者 
     * @param criterions 动态条件
     * @return 关联结果
     */  
    public static LogicalExpression or(Criterion... criterions){
        return new LogicalExpression(criterions, Criterion.Operator.or);
    } 
    
    /** 
     * 包含于 
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 表达式
     */  
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        if(ignoreNull&&(value==null||value.isEmpty())){  
            return null;  
        }  
        SimpleExpression[] ses = new SimpleExpression[value.size()];  
        int i=0;  
        for(Object obj : value){  
            ses[i]=new SimpleExpression(fieldName,obj, Criterion.Operator.eq);
            i++;  
        }  
        return new LogicalExpression(ses, Criterion.Operator.or);
    }

    /**
     * 不包含于
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 表达式
     */
    public static LogicalExpression notin(String fieldName, Collection value, boolean ignoreNull) {
        if(ignoreNull&&(value==null||value.isEmpty())){
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i=0;
        for(Object obj : value){
            ses[i]=new SimpleExpression(fieldName, obj, Criterion.Operator.ne);
            i++;
        }
        return new LogicalExpression(ses, Criterion.Operator.and);
    }

    /**
     * between区间查询
     * @param fieldName 属性名称
     * @param object1	开始值
     * @param object2	结束值
     * @return 符合条件结果集
     */
    public static LogicalExpression between(String fieldName, Object object1, Object object2)
    {
        if(object1 == null || object2 == null)
        {
            return null;
        }
        SimpleExpression[]ses=new SimpleExpression[2];
        ses[0]=new SimpleExpression(fieldName,object1, Criterion.Operator.ge);
        ses[1]=new SimpleExpression(fieldName,object2, Criterion.Operator.le);
        return new LogicalExpression(ses, Criterion.Operator.and);
    }

    /**
     * 复杂关系连接查询
     * @param class1 基础对象
     * @param class2 关联对象
     * @param fieldName 属性名称
     * @param value 属性值
     * @return 符合条件结果集
     */
    public static JoinExpression joinList(Class class1, Class class2, String fieldName, Object value)
    {
        if(StringUtils.isEmpty(value))
        {
            return null;
        }
        return new JoinExpression (class1, class2, fieldName, value, Criterion.Operator.join_list);
    }

}

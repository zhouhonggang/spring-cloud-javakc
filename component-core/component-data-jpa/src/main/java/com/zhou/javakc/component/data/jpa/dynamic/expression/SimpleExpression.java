package com.zhou.javakc.component.data.jpa.dynamic.expression;

import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criterion;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

/**
 * 封装公共逻辑表达式查询
 * 简单的条件表达式封装
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午6:55:45
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class SimpleExpression implements Criterion {

    /**
     * 属性名称
     */
	private String fieldName;
    /**
     * 属性值
     */
    private Object value;
    /**
     * 计算符
     */
    private Criterion.Operator operator;
  
    public SimpleExpression(String fieldName, Object value, Criterion.Operator operator)
    {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    public Object getValue() {
        return value;
    }
    public Operator getOperator() {
        return operator;
    }
	
	@Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	Path expression = null;
        if(fieldName.contains("."))
        {
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++)
            {
                expression = expression.get(names[i]);
            }
        }
        else
        {  
            expression = root.get(fieldName);
        }
        switch (operator) {
	        case eq:
	            return builder.equal(expression, value);
	        case ne:
	        	return builder.notEqual(expression, value);
	        case gt:
	        	return builder.greaterThan(expression, (Comparable) value);
	        case ge:
	            return builder.greaterThanOrEqualTo(expression, (Comparable) value);
	        case lt:
	        	return builder.lessThan(expression, (Comparable) value);
	        case le:
	            return builder.lessThanOrEqualTo(expression, (Comparable) value);
	        case like:
	        	return builder.like((Expression<String>) expression, "%" + value + "%");
	        default:
	            return null;
        }
    }
    
}
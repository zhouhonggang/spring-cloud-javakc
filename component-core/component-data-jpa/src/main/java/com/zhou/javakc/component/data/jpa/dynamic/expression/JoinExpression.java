package com.zhou.javakc.component.data.jpa.dynamic.expression;

import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criterion;

import javax.persistence.criteria.*;

/**
 * 封装公共逻辑表达式查询
 * 复杂查询通过or/and拼接
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午6:53:13
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class JoinExpression implements Criterion {

	/**
	 * 基础对象
	 */
	private Class class1;
	/**
	 * 关联对象
	 */
	private Class class2;
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
	private Operator operator;

    public JoinExpression(Class class1, Class class2, String fieldName, Object value, Operator operator)
    {
    	this.class1 = class1;
    	this.class2 = class2;
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		switch (operator) {
			case join_list:
				return builder.equal(root.join(String.valueOf(root.getModel().getList("users", class2)),
						JoinType.LEFT).get(fieldName), value);
			case join_set:
				return builder.equal(root.join(String.valueOf(root.getModel().getSet("users", class2)),
						JoinType.LEFT).get(fieldName), value);
			default:
				return null;
		}
	}

}
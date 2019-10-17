package com.zhou.javakc.component.data.jpa.dynamic.expression;

import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criterion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * 封装公共逻辑表达式查询
 * 复杂查询通过or/and拼接
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午6:53:13
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class LogicalExpression implements Criterion {
	
	/**
	 * 逻辑表达式中包含的表达式  
	 */
	private Criterion[] criterion;
	/**
	 * 枚举运算符
	 */
    private Operator operator;
    
    public LogicalExpression(Criterion[] criterions, Operator operator)
    {  
        this.criterion = criterions;  
        this.operator = operator;  
    }
    
	@Override
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (Criterion criterion1 : criterion) {
			predicates.add(criterion1.toPredicate(root, query, builder));
		}
        switch (operator)
        {
	        case or:
	            return builder.or(predicates.toArray(new Predicate[predicates.size()]));
	        case and:
	            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	        default:
	            return null;
        }
	}
	
}
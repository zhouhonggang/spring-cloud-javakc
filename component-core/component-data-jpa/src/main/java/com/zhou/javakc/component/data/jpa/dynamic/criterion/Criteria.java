package com.zhou.javakc.component.data.jpa.dynamic.criterion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * 封装公共查询实现容器
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午5:37:54
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public class Criteria<T> implements Specification<T> {
	
	private static final long serialVersionUID = 1L;
	private List<Criterion> criterions = new ArrayList<Criterion>();  
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (!criterions.isEmpty())
		{  
            List<Predicate> predicates = new ArrayList<Predicate>();            
            for(Criterion c:criterions)
            {
                predicates.add(c.toPredicate(root, query, builder));
            }
            //将所有条件用 and 联合起来
            if (predicates.size() > 0)
            {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
		return builder.conjunction();
	}
	
	/** 
     * 增加简单条件表达式 
     * @Methods name add 
     * @param 
     */  
    public Criteria<T> add(Criterion criterion)
    {  
        if(criterion!=null)
        {
            criterions.add(criterion);
        }
        return this;
    }

}
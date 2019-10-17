package com.zhou.javakc.component.data.jpa.dynamic.criterion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 封装公共查询接口
 * @project javakc
 * @author zhou
 * @date 2019年01月12日下午5:34:25
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public interface Criterion {
	
	public enum Operator {
		/**
		 * The most common MatchType {@code filed=value},
		 * e.g. "testName=admin".
		 * <p>等值条件查询
		 */
		eq,
		/**
		 * The most common MatchType {@code filed!=value},
		 * e.g. "testName!=admin".
		 * <p>等值条件查询
		 */
		ne,
		/**
		 * The most common MatchType {@code filed>value},
		 * e.g. "testAge>18".
		 * <p>大于条件查询
		 */
	    gt,
	    /**
		 * The most common MatchType {@code filed>=value},
		 * e.g. "testAge>=18".
		 * <p>大于等于条件查询
		 */
	    ge,
	    /**
		 * The most common MatchType {@code filed<value},
		 * e.g. "testAge<18".
		 * <p>小于条件查询
		 */
	    lt,
	    /**
		 * The most common MatchType {@code filed<=value},
		 * e.g. "testAge<=18".
		 * <p>小于等于条件查询
		 */
	    le,
		/**
		 * The most common MatchType {@code filed like value},
		 * e.g. "testName like %admin%".
		 * <p>模糊查询
		 */
	    like,
	    or,
	    and,
		join_list, //左连接LIST
		join_set //左连接SET
	}
	
	public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
	
}
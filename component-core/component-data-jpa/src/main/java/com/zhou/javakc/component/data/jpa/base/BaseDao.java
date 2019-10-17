package com.zhou.javakc.component.data.jpa.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 基础模块数据层接口
 * 所有modules都需要继承该接口
 * @project javakc
 * @author zhou
 * @date 2019年1月19日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>{
	/**
	 * 动态参数分页查询
	 * @param specification 动态参数
	 * @param pageable 分页参数
	 * @return 结果集
	 */
    Page<T> findAll(Specification<T> specification, Pageable pageable);
    /**
     * 动态参数查询
     * @param specification 动态查询条件
     * @return 结果集
     */
    List<T> findAll(Specification<T> specification);
}

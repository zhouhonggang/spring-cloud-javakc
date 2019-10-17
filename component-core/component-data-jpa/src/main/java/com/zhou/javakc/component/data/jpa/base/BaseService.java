package com.zhou.javakc.component.data.jpa.base;

import com.zhou.javakc.component.data.entity.base.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 公共逻辑层实现类父类
 * 每个modules都需要继承该父类
 * @project javakc
 * @author zhou
 * @date 2019年1月19日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends Base, D extends BaseDao> {
	
	@Autowired
	protected D dao;
	
	/**
	 * 添加单条记录
	 * @param entity
	 */
	@Transactional(readOnly=false)
	public T save(T entity)
	{
		entity.setRdate(new Timestamp(System.currentTimeMillis()));
		return (T) dao.save(entity);
	}
	
	/**
	 * 批量添加
	 * @param entitys
	 */
	@Transactional(readOnly=false)
	public void save(List<T> entitys)
	{
		dao.saveAll(entitys);
	}
	
	/**
	 * 删除单条记录
	 * @param entity
	 */
	@Transactional(readOnly=false)
	public void delete(T entity)
	{
		dao.delete(entity);
	}

	/**
	 * 删除单条记录
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delete(Serializable id)
	{
		dao.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param entitys
	 */
	@Transactional(readOnly=false)
	public void delete(List<T> entitys)
	{
		dao.deleteAll(entitys);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(readOnly=false)
	public int delete(String[] ids)
	{
		int count=0;
		if(null!=ids && ids.length>0)
		{
			for(String id:ids)
			{
				dao.deleteById(id);
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 修改单条记录
	 * @param entity
	 */
	@Transactional(readOnly=false)
	public T update(T entity)
	{
		entity.setUdate(new Timestamp(System.currentTimeMillis()));
		return (T)dao.save(entity);
	}
	
	/**
	 * 根据主键获取单条记录
	 * @param id 主键ID
	 * @return 查询结果集
	 */
	public T get(Serializable id)
	{
		return (T) dao.findById(id).get();
	}

//	public List<T> list(final Map<String, Object> params) {
//		Specification<T> spec = new Specification<T>() {
//			@Override
//			public Predicate toPredicate(Root<T> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
//				List<Predicate> list = new ArrayList<Predicate>();
//				for(Map.Entry<String, Object> entry : params.entrySet()){
//					Object value = entry.getValue();
//					if(value == null || StringUtils.isEmpty(value.toString())){
//						continue;
//					}
//					String key = entry.getKey();
//					String[] arr = key.split(":");
//					Predicate predicate = getPredicate(arr, value, root, cb);
//					list.add(predicate);
//				}
//				Predicate[] p = new Predicate[list.size()];
//				return cb.and(list.toArray(p));
//			}
//		};
//		return dao.findAll(spec);
//	}

//	public Page<T> list(final Map<String, Object> params, Pageable pageable){
//		Specification<T> spec = new Specification<T>() {
//			@Override
//			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				List<Predicate> list = new ArrayList<Predicate>();
//				for(Map.Entry<String, Object> entry : params.entrySet()){
//					Object value = entry.getValue();
//					if(value == null || StringUtils.isEmpty(value.toString())){
//						continue;
//					}
//					String key = entry.getKey();
//					String[] arr = key.split(":");
//					Predicate predicate = getPredicate(arr,value,root,cb);
//					list.add(predicate);
//				}
//				Predicate[] p = new Predicate[list.size()];
//				return cb.and(list.toArray(p));
//			}
//		};
//		return dao.findAll(spec, pageable);
//	}

}

package com.zhou.javakc.javakc.test.dao;

import com.zhou.javakc.component.data.entity.test.Test;
import com.zhou.javakc.component.data.jpa.base.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-18 14:07
 */
public interface JavakcTestDao extends BaseDao<Test, String> {

//    [
//    {value: 3, name:'男'},
//    {value: 4, name:'女'}
//    ]
    @Query(" select new map( count(t.sex) as value, (case when t.sex = 1 then '男' else  '女' end) as name ) from Test t group by t.sex ")
    public List<Map<String, Object>> queryGroupBySex();

}

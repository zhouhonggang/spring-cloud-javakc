package com.zhou.javakc.system.user.dao;

import com.zhou.javakc.component.data.entity.system.User;
import com.zhou.javakc.component.data.jpa.base.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-08-08 15:50
 */
public interface UserDao extends BaseDao<User, String> {

    @Query(" select count(u) from User u where u.loginName = ?1 ")
    public int findByLoginName(String loginName);

    @Query(value = "select r.dates, r.nums from (select to_char(u.register_date, 'yyyy-MM-dd') as dates, count(1) as nums " +
            "from system_user u group by to_char(u.register_date, 'yyyy-MM-dd')) r order by r.dates asc", nativeQuery = true)
    public List<Map<String, Object>> chart();

}

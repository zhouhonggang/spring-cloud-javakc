package com.zhou.javakc.system.role.service;

import com.zhou.javakc.component.data.entity.system.Role;
import com.zhou.javakc.component.data.jpa.base.BaseService;
import com.zhou.javakc.component.data.jpa.dynamic.criterion.Criteria;
import com.zhou.javakc.component.data.jpa.dynamic.restrictions.Restrictions;
import com.zhou.javakc.system.role.dao.RoleDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-09-06 09:01
 */
@Service
public class RoleService extends BaseService<Role, RoleDao> {

    @Transactional(readOnly=false)
    public Role save(Role entity)
    {
        if(StringUtils.isEmpty(entity.getRoleId()))
        {
            entity.setRdate(new Timestamp(System.currentTimeMillis()));
        }
        else
        {
            entity.setUdate(new Timestamp(System.currentTimeMillis()));
        }
        return dao.save(entity);
    }

    public Page<Role> findAll(Role entity)
    {
        Criteria<Role> criteria = new Criteria<>();

        if(!StringUtils.isEmpty(entity.getRoleName()))
        {
            criteria.add(Restrictions.like("roleName", entity.getRoleName()));
        }
        if(!StringUtils.isEmpty(entity.getRoleRemark()))
        {
            criteria.add(Restrictions.like("roleRemark", entity.getRoleRemark()));
        }

        PageRequest pageable = PageRequest.of(entity.getOffset(), entity.getLimit());

        return dao.findAll(criteria, pageable);
    }

    public List<Role> findAll()
    {
        return dao.findAll();
    }

}

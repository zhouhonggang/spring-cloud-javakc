package com.zhou.javakc.component.data.entity.system;

import com.zhou.javakc.component.data.entity.base.Base;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色模块
 * @project javakc
 * @author zhou
 * @date 2019年1月18日
 * @Copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
@Entity
@Table(name = "system_role")
public class Role extends Base implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    private String roleId;

    /**
     * 用户系统昵称
     */
    @NotBlank(message="角色名称不能为空")
    @Column(name = "role_name")
    private String roleName;

    /**
     * 用户性别
     */
    @NotBlank(message="角色备注不能为空")
    @Column(name = "role_remark")
    private String roleRemark;

}
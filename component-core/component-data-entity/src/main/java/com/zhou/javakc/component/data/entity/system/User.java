package com.zhou.javakc.component.data.entity.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhou.javakc.component.data.entity.base.Base;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户模块
 * @project javakc
 * @author zhou
 * @date 2019年1月18日
 * @Copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
@Entity
@Table(name = "system_user")
public class User extends Base implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    private String userId;
    /**
     * 用户系统昵称
     */
    @NotBlank(message="用户名不能为空")
    @Column(name = "user_name")
    private String userName;
    /**
     * 用户性别
     */
    @Column(name = "user_sex")
    private Integer userSex;
    /**
     * 用户登陆名称
     */
    @NotBlank(message="登陆名不能为空")
    @Size(min=3, max=16, message = "登录名长度要求在3-16位之间")
    @Column(name = "login_name")
    private String loginName;
    /**
     * 用户登陆密码
     */
    @NotBlank(message="登陆密码不能为空")
    @Size(min=6, max=16, message = "登录密码长度要求在6-16位之间")
    @Column(name = "login_pass")
    private String loginPass;
    /**
     * 用户手机号
     */
    @NotBlank(message="手机号码不能为空")
    @Column(name = "user_phone")
    private String userPhone;
    /**
     * 用户邮箱地址
     */
    @NotBlank(message="邮箱地址不能为空")
    @Column(name = "user_email")
    private String userEmail;
    /**
     * 用户出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "user_birthday")
    private Date userBirthday;
    /**
     * 用户住址
     */
    @Column(name = "user_address")
    private String userAddress;
    /**
     * 用户头像 oracle数据库blob字段存储
     */
    @Lob
    @Column(name = "user_photo")
    private byte[] userPhoto;

    @Transient
    private String userPhotoName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * 配置用户-角色多对多关系
     */
//    @ManyToMany
//    @JoinTable(
//        name = "system_user_role",
//        joinColumns = {
//            @JoinColumn(name = "user_id")
//        },
//        inverseJoinColumns = {
//            @JoinColumn(name = "role_id")
//        }
//    )
//    private List<Role> role;


//    @Null   被注释的元素必须为 null
//    @NotNull    被注释的元素必须不为 null
//    @AssertTrue     被注释的元素必须为 true
//    @AssertFalse    被注释的元素必须为 false
//    @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
//    @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
//    @Past   被注释的元素必须是一个过去的日期
//    @Future     被注释的元素必须是一个将来的日期
//    @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
//    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message="出生日期格式不正确")

//    Hibernate Validator 附加的 constraint
//    @NotBlank(message =)   验证字符串非null，且长度必须大于0
//    @Email  被注释的元素必须是电子邮箱地址
//    @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
//    @NotEmpty   被注释的字符串的必须非空
//    @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
}

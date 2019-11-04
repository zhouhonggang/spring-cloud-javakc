package com.zhou.javakc.component.data.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.zhou.javakc.component.data.entity.system.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * entity实体对象父类对象
 * 定义一系列公共的属性方法
 * @project javakc
 * @author zhou
 * @date 2019年1月18日
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Base {
    /**
     * 添加人(待添加)
     */
//    @OneToOne
//    @JoinColumn(name = "add_user", referencedColumnName = "user_id")
//    private User addUser;
    /**
     * 修改人(待添加)
     */
//    @OneToOne
//    @JoinColumn(name = "modify_user", referencedColumnName = "user_id")
//    private User modifyUser;

    /**
     * 注册日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "register_date", updatable = false)
    public Timestamp rdate;
    /**
     * 修改日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "changed_date", insertable = false)
    public Timestamp udate;

    /**
     * 封装客户端 分页查询 条件
     * 从多少条开始查询
     */
    @JsonSetter
    @Transient
    public int offset;
    /**
     * 封装客户端 分页查询 条件
     * 每页查询条数
     */
    @JsonSetter
    @Transient
    public int limit = 10;

    /**
     * 封装客户端 数字区间 查询 开始
     */
    @JsonSetter
    @Transient
    public Long srange;
    /**
     * 封装客户端 数字区间 查询 结束
     */
    @JsonSetter
    @Transient
    public Long erange;

    /**
     * 封装客户端 日期区间 查询 开始日期
     */
    @JsonSetter
    @Transient
	@JsonIgnore
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date sdate;
    /**
     * 封装客户端 日期区间 查询 结束日期
     */
    @JsonSetter
    @Transient
	@JsonIgnore
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    public Date edate;
}

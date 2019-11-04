package com.zhou.javakc.component.data.entity.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhou.javakc.component.data.entity.base.Base;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-18 13:50
 */
@Getter
@Setter
@Entity
@Table(name = "javakc_test")
public class Test extends Base implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "address")
    private String address;

}

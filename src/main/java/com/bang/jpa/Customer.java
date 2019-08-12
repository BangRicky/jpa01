package com.bang.jpa;

import javax.persistence.*;

/**
 * @author: jinbang
 * @create: 2019/8/12 13:47
 */


//关联数据表
@Table(name="Jpa_Customers")
//持久化类
@Entity
public class Customer {

    @Id//标明是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置主键生成策略

    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private int age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

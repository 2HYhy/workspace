package com.me.gacl.pojo;

import javax.persistence.*;

/**
 * @author CH-yfy
 * @date 2017/12/26
 */

@Entity
@Table( name = "person")
public class PersonDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, name = "create_time")
    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PersonDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}

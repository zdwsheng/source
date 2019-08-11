package base.jdk8.bean;

import java.io.Serializable;

/**
 * @author: zdw.sheng@foxmail.com
 * @name:
 * @desc:
 * @jdk
 * @group
 * @os
 * @date 2018/11/13
 */

public class User implements Serializable {
    static final long serialVersionUID = 79067096080L;

    private Integer age;
    private String name;
    private char sex;

    public User() {
        this.age = 18;
        this.name = "老张";
        this.sex = '男';
    }

    public User(Integer age, String name, char sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "user--age:" + age + "　name:" + name + " sex:" + sex;
    }
}

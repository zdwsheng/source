package sourcecode.annotation;

/**
 * @author: 张灯皖
 * @name
 * @desc TODO
 * @jdk
 * @group
 * @os
 * @date 2018/12/21
 */
public class AnnotationBean {
    @MyAnnotation(id = 1)
    private Integer id = 9;
    @MyAnnotation(name = "2")
    private String name = "默认";
    @MyAnnotation(sex = '3')
    private char sex = '男';

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "toString--id: " + id + " name:" + name + " sex:" + sex;
    }
}

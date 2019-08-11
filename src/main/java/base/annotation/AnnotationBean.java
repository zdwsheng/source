package base.annotation;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author: 张灯皖
 * @name
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2018/12/21
 */
@Data
public class AnnotationBean {
    @MyAnnotation(id = 1)
    private Integer id = 9;
    @MyAnnotation(name = "2")
    private String name = "默认";
    @MyAnnotation(sex = '3')
    private char sex = '男';

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return className + ":" + JSONObject.toJSONString(this);
    }

    public static void main(String[] args) {
        AnnotationBean annotationBean = new AnnotationBean();
        System.out.println(annotationBean);
    }
}

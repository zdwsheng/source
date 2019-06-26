package sourcecode.annotation;

import java.lang.reflect.Field;

/**
 * @author: 张灯皖
 * @name 注解工具类
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2018/12/21
 */
public class AnnotationUtils {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AnnotationBean annotationBean = new AnnotationBean();
        System.out.println("赋值前:" + annotationBean.toString());
        AnnotationUtils.getAnnotation(annotationBean);
        System.out.println("赋值后:" + annotationBean.toString());
    }

    /**
     * 注解@MyAnnotation可以将注解中的值复制到定义注解的成员变量上
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Object getAnnotation(Object obj) throws IllegalAccessException, InstantiationException {
        Class tClass = obj.getClass();
        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
                System.out.println("anno-id:" + myAnnotation.id() + " anno-name:" + myAnnotation.name() + " anno-sex:" + myAnnotation.sex());
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    field.set(obj, myAnnotation.id());
                }
                if (field.getName().equals("name")) {
                    field.set(obj, myAnnotation.name());
                }
                if (field.getName().equals("sex")) {
                    field.set(obj, myAnnotation.sex());
                }
            }
        }
        return obj;
    }
}

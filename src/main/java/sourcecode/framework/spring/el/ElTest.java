package sourcecode.framework.spring.el;

import base.jdk8.bean.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @name
 * @ClassName ElTest
 * @Description spring el 解析器
 * @author: zdw
 * @date 2019/9/6 14:41
 */
public class ElTest {
    private static ElTest instance = new ElTest();

    public static void main(String[] args) {
        instance.elPojo();
    }

    /**
     * spring el使用是通过解析器从表达式上下文中取值
     */
    private void elPojo() {
        String expressElString = "#user.name";
        User user = new User(12, "spring-el", 'Y');

        //创建SpEL表达式的解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        //创建解析表达式需要的上下文
        EvaluationContext context = new StandardEvaluationContext();
        //在上下文中设置key value
        context.setVariable("user", user);

        //通过解析器从上下文中取值
//        Object result = parser.parseRaw(expressElString).getValue(context);
        String result = parser.parseRaw(expressElString).getValue(context, String.class);
        System.out.println("result:" + result);
    }
}

package college.mysql.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 执行后的方法
 * User: EDY
 * Date: 2024/5/13
 * Time: 14:56
 * Version:V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AfterHandler {
    /**
     * 要执行的Handler类
     * @return
     */
    Class<? extends Handler> value();
}

package college.mysql.aop;

import college.mysql.handler.after.AfterHandler;
import college.mysql.handler.after.Handler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 服务调度器
 * 1.执行Service 指定的方法
 * 2.确保事务是和Service的方法是一个事务
 * 3.校验指定方法是handle的实现类。
 * Date: 2024/5/11
 */
@Component
@Aspect
public class ServiceDispatcher {

    @Autowired
    private List<Handler> handlerList;

    /**
     * 这种方式拦截，和主函数是共用一个事务，如果这里报错，事务也会回滚
     */
    @After("execution(* college.mysql.service.*.*(..))")
    public void afterServiceExecution(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(AfterHandler.class)) {
            Class<?> clazz = method.getAnnotation(AfterHandler.class).value();
            Class<Handler> interfaceToCheck = Handler.class;
            if (clazz.isInterface()) {
                throw new IllegalStateException(clazz.getName() + " is an interface");
            }
            if (!interfaceToCheck.isAssignableFrom(clazz)) {
                throw new IllegalStateException(clazz.getName() + " does not implement " + interfaceToCheck.getName());
            }
            for (Handler handler : handlerList) {
                if (clazz == handler.getClass()) {
                    handler.execute();
                }
            }

        }
    }
}

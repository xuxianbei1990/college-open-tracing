package college.mysql.aop;

import college.mysql.handler.AfterHandler;
import college.mysql.handler.BeforeHandler;
import college.mysql.handler.Handler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

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

    private ThreadLocal<Object> contextThreadLocal = new ThreadLocal<>();

    public Object getResult() {
        return contextThreadLocal.get();
    }


    @Before("execution(* college.mysql.service.*.*(..))\"")
    public void beforeServiceExecution(JoinPoint joinPoint) {
        doDispatch(joinPoint, BeforeHandler.class, (method) -> method.getAnnotation(BeforeHandler.class).value());
    }

    /**
     * 这种方式拦截，和主函数是共用一个事务，如果这里报错，事务也会回滚
     */
    @AfterReturning(pointcut = "execution(* college.mysql.service.*.*(..))\"", returning = "result")
    public void afterServiceExecution(JoinPoint joinPoint, Object result) {
        contextThreadLocal.set(result);
        try {
            doDispatch(joinPoint, AfterHandler.class, (method) -> method.getAnnotation(AfterHandler.class).value());
        } finally {
            contextThreadLocal.remove();
        }
    }

    private void doDispatch(JoinPoint joinPoint, Class<? extends Annotation> annotationClass, Function<Method, Class<?>> get) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(annotationClass)) {
            Class<?> clazz = get.apply(method);
            Class<Handler> interfaceToCheck = Handler.class;
            if (clazz.isInterface()) {
                throw new IllegalStateException(clazz.getName() + " is an interface");
            }
            if (!interfaceToCheck.isAssignableFrom(clazz)) {
                throw new IllegalStateException(clazz.getName() + " does not implement " + interfaceToCheck.getName());
            }
            Object object = joinPoint.getArgs()[0];
            for (Handler handler : handlerList) {
                if (clazz == handler.getClass()) {
                    handler.execute(object);
                }
            }
        }
    }

}

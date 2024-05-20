package college.web.log.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * User: EDY
 * Date: 2024/5/20
 * Time: 18:33
 * Version:V1.0
 */
@Aspect
public class ResponseBodyLog {

    @AfterReturning(pointcut = "execution(* college.*.controller..*.*(..))", returning = "result")
    public void afterMoneyView(JoinPoint joinPoint, Object result) {
        System.out.println("BodyLog: " + result);
    }
}

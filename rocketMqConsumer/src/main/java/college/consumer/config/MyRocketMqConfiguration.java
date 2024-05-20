package college.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: EDY
 * Date: 2024/5/18
 * Time: 13:45
 * Version:V1.0
 */
@Aspect
@Component
@Slf4j
public class MyRocketMqConfiguration {

    @Around("execution(* org.apache.rocketmq.spring.core.RocketMQListener.*(..))")
    public Object aroundRocketMQListener(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs().length > 0) {
            Object message = joinPoint.getArgs()[0];
            if (message instanceof MessageExt) {
                MessageExt msgExt = (MessageExt) message;
                log.info("rocketmq Mq message received, msgId: {}, traceId: {}, body: {}",
                        msgExt.getMsgId(), msgExt.getUserProperty("My trace id"), new String(msgExt.getBody()));
            }
        }
        Object result = joinPoint.proceed();
        return result;

    }
}

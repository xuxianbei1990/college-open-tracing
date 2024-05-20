package college.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.hook.SendMessageContext;
import org.apache.rocketmq.client.hook.SendMessageHook;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * User: EDY
 * Date: 2024/5/18
 * Time: 13:32
 * Version:V1.0
 */
@Slf4j
public class SendMessageTraceHook implements SendMessageHook {
    @Override
    public String hookName() {
        return "trace id";
    }

    @Override
    public void sendMessageBefore(SendMessageContext context) {
        context.getMessage().getProperties().put("My trace id", TraceContext.traceId());
        log.info("SendMessageBefore, trace id: {}, message: {}", TraceContext.traceId(), context.getMessage());
    }

    @Override
    public void sendMessageAfter(SendMessageContext context) {

    }
}

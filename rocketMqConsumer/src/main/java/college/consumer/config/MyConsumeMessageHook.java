package college.consumer.config;

import org.apache.rocketmq.client.hook.ConsumeMessageContext;
import org.apache.rocketmq.client.hook.ConsumeMessageHook;

/**
 * User: EDY
 * Date: 2024/5/18
 * Time: 14:09
 * Version:V1.0
 */
public class MyConsumeMessageHook implements ConsumeMessageHook {
    @Override
    public String hookName() {
        return "trace id";
    }

    @Override
    public void consumeMessageBefore(ConsumeMessageContext context) {
        System.out.println("Consumer My traceId: " + context.getProps().get("My trace id"));
    }

    @Override
    public void consumeMessageAfter(ConsumeMessageContext context) {

    }
}

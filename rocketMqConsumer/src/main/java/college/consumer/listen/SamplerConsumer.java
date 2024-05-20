package college.consumer.listen;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * User: EDY
 * Date: 2024/5/14
 * Time: 14:06
 * Version:V1.0
 */


@Component
@RocketMQMessageListener(topic = "sample-topic", consumerGroup = "sample_consumer_group",
        messageModel = MessageModel.BROADCASTING)
public class SamplerConsumer implements RocketMQListener<MessageExt> {


    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println("My traceId: " + messageExt.getUserProperty("My trace id"));
        System.out.println("Received message: " + new String(messageExt.getBody()) + " traceId: " + messageExt.getUserProperty("traceId"));
    }
}

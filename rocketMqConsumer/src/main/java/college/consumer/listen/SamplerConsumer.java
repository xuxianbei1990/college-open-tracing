package college.consumer.listen;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * User: EDY
 * Date: 2024/5/14
 * Time: 14:06
 * Version:V1.0
 */


@Component
@RocketMQMessageListener(topic = "sample_topic", consumerGroup = "sample_consumer_group",
        messageModel = MessageModel.BROADCASTING)
public class SamplerConsumer implements RocketMQListener<MessageExt> {


    @Override
    public void onMessage(MessageExt s) {
        System.out.println("Received message: " + s);
    }
}

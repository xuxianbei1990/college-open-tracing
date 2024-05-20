package college.producer.config;

import jakarta.annotation.PostConstruct;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * User: EDY
 * Date: 2024/5/18
 * Time: 11:20
 * Version:V1.0
 */
@Configuration
public class MyRocketMQConfiguration {


    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @PostConstruct
    public void init() {
        defaultMQProducer.getDefaultMQProducerImpl().registerSendMessageHook(new SendMessageTraceHook());
    }

}

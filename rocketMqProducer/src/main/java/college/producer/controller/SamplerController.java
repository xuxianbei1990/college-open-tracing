package college.producer.controller;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: EDY
 * Date: 2024/5/13
 * Time: 19:57
 * Version:V1.0
 */
@RestController
@RequestMapping("producer")
public class SamplerController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("send")
    public String send() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        rocketMQTemplate.syncSend("sample-topic", "sample test");
        return "Hello, World!";
    }
}

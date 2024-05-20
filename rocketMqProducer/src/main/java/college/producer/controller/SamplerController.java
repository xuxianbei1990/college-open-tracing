package college.producer.controller;

import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public String send() {
        rocketMQTemplate.syncSend("sample-topic", "sample test:" + TraceContext.traceId());
        return "Hello, World!";
    }

    @GetMapping("send/traceId")
    public String sendTraceId() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("traceId", TraceContext.traceId());
        MessageHeaders messageHeaders = new MessageHeaders(headers);

        Message<?> message = MessageBuilder.createMessage("sample test traceId ", messageHeaders);
        rocketMQTemplate.syncSend("sample-topic", message);
        return "Hello, World!";
    }
}

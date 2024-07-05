package college.netty.controller;

import college.netty.utils.ClientChannelSendUtil;
import college.netty.utils.MessageSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("netty/sample")
public class SampleController {

    @GetMapping("hello/channel")
    public String hello() {
//        MessageSender.send("Hello from Server Netty");
        ClientChannelSendUtil.getInstance().send("1", "中文测试");
        return "Hello from Netty";
    }
}

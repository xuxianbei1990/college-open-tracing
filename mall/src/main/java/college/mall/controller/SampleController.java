package college.mall.controller;

import college.mall.remote.OrderApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: EDY
 * Date: 2024/5/6
 * Time: 11:24
 * Version:V1.0
 */
@RestController
@RequestMapping("mall/sample")
public class SampleController {

    @Autowired
    private OrderApiService orderApiService;

    @GetMapping("hello/order")
    public String hello() {
        return orderApiService.sample("I am a mall");
    }
}

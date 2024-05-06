package college.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: EDY
 * Date: 2024/5/6
 * Time: 14:13
 * Version:V1.0
 */
@RequestMapping("order/sample")
@RestController
public class SampleController {

    @GetMapping("hello")
    public String hello(@RequestParam String owner) {
        return "Hello order!" + owner;
    }
}

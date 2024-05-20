package college.order.controller;

import college.order.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SampleController {

    @GetMapping("hello")
    public String hello(@RequestParam String owner) {
        return "Hello order!" + owner;
    }

    @GetMapping("hello/entiry")
    public SampleVO helloEntity(@RequestParam String owner) {
        SampleVO sampleVO = new SampleVO();
        sampleVO.setName(owner);
        sampleVO.setAge(25);
        return sampleVO;
    }

}

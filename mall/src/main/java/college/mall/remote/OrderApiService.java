package college.mall.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: EDY
 * Date: 2024/5/6
 * Time: 13:10
 * Version:V1.0
 */
@FeignClient(value = "order-center")
public interface OrderApiService {

    @GetMapping("/order/sample/hello")
    String sample(@RequestParam String owner);
}

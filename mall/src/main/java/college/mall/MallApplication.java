package college.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * User: EDY
 * Date: 2024/5/6
 * Time: 11:23
 * Version:V1.0
 */
@EnableFeignClients(basePackages = "college.mall.remote")
@SpringBootApplication
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}

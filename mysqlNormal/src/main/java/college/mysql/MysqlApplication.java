package college.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * User: EDY
 * Date: 2024/5/11
 * Time: 10:37
 * Version:V1.0
 */
//exclude = {YudaoBannerAutoConfiguration.class}
@SpringBootApplication
@MapperScan("college.mysql.dao")
public class MysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }
}

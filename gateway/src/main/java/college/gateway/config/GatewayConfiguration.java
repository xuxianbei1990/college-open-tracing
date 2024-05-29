package college.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.CodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;

@Configuration
public class GatewayConfiguration {

    @Bean
    public CodecConfigurer codecConfigurer() {
        return new DefaultServerCodecConfigurer();
    }
}

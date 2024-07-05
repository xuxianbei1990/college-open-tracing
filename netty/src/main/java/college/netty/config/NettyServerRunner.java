package college.netty.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;
import com.alibaba.nacos.api.naming.NamingService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Component;

/**
 * starts the netty server
 */
@Component
public class NettyServerRunner implements CommandLineRunner {

    @Autowired
    private ServerBootstrap bootstrap;

    //改为这个方法
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    public static ChannelFuture future;

    @Override
    public void run(String... args) throws Exception {
        future = bootstrap.bind().sync();
        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
        namingService.registerInstance("netty-server", nacosDiscoveryProperties.getGroup(),
                nacosDiscoveryProperties.getIp(), NettyConfiguration.port, nacosDiscoveryProperties.getClusterName());
    }
}

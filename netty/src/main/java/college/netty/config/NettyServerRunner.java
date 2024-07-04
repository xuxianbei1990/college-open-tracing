package college.netty.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NettyServerRunner implements CommandLineRunner {

    @Autowired
    private ServerBootstrap bootstrap;

    public static ChannelFuture future;

    @Override
    public void run(String... args) throws Exception {
         future = bootstrap.bind().sync();
    }
}

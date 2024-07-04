package college.netty.config;

import io.netty.channel.EventLoopGroup;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyServerStop implements DisposableBean {


    @Autowired
    private EventLoopGroup boosGroup;

    @Autowired
    private EventLoopGroup workerGroup;

    @Override
    public void destroy() throws Exception {
//        NettyServerRunner.future.channel().closeFuture().sync();
        boosGroup.shutdownGracefully().sync();
        workerGroup.shutdownGracefully().sync();
    }
}

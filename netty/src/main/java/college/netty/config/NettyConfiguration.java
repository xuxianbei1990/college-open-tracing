package college.netty.config;

import college.netty.service.EchoServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.net.InetSocketAddress;

@Configuration
public class NettyConfiguration {
    public static final int port = 8001;

    @Autowired
    private EchoServerHandler echoServerHandler;

    @Bean(name = "boosGroup")
    public EventLoopGroup boosGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(name = "workerGroup")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }


    @Bean
    public ServerBootstrap serverBootstrap(EventLoopGroup boosGroup, EventLoopGroup workerGroup) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                //handler在初始化时就会执行，而childHandler会在客户端成功connect后才执行，这是两者的区别。
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        // HTTP 编解码器
                        socketChannel.pipeline().addLast(new HttpServerCodec());
                        // 聚合HTTP消息到FullHttpRequest或FullHttpResponse
                        socketChannel.pipeline().addLast(new HttpObjectAggregator(65536));
                        // 支持大消息的写操作
                        socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                        // WebSocket 握手处理
                        socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                        socketChannel.pipeline().addLast(echoServerHandler);
                    }
                });
        return bootstrap;
    }
}

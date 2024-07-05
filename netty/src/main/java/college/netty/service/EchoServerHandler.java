package college.netty.service;


import cn.hutool.json.JSONUtil;
import college.netty.entity.Message;
import college.netty.utils.ClientChannelPoolUtil;
import college.netty.utils.MessageSender;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

@Service
@ChannelHandler.Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        MessageSender.channel = ctx.channel();
        ClientChannelPoolUtil.getInstance().add(ctx.channel());
        System.out.println("Received WebSocket message: " + ctx.channel().remoteAddress().toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 收到客户端消息，打印出来
        System.out.println("Received WebSocket message: " + msg.text());
        Message message = JSONUtil.toBean(msg.text(), Message.class);
        System.out.println(message.toString());
        ClientChannelPoolUtil.getInstance().addKey(String.valueOf(message.getId()), ctx.channel());
        // 发送消息回客户端
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Hello from server!"));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 当客户端连接时，发送欢迎消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Welcome to the WebSocket server!"));
        super.handlerAdded(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常处理
        cause.printStackTrace();
        ctx.close();
    }
}

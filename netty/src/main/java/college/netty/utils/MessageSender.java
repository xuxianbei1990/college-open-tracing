package college.netty.utils;


import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MessageSender {

    public static Channel channel;


    public static void send(String message) {
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }
}

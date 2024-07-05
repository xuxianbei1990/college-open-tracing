package college.netty.utils;


import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class ClientChannelSendUtil {

    private final static ClientChannelSendUtil instance = new ClientChannelSendUtil();

    public static ClientChannelSendUtil getInstance() {
        return instance;
    }

    public boolean send(String channelKey, String message) {
        Channel channel = ClientChannelPoolUtil.getInstance().getChannel(channelKey);
        if (channel != null && channel.isActive() && channel.isWritable() && channel.isOpen()) {
            channel.writeAndFlush(new TextWebSocketFrame(message));
            return true;
        }
        return false;
    }
}

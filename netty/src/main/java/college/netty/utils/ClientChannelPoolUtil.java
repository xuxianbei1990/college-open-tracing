package college.netty.utils;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientChannelPoolUtil {
    Set<Channel> set = new HashSet<>();

    Map<String, Channel> keyChannelMap = new HashMap<>();

    private static ClientChannelPoolUtil instance = new ClientChannelPoolUtil();

    public static ClientChannelPoolUtil getInstance() {
        return instance;
    }

    public boolean add(Channel channel) {
        return set.add(channel);
    }

    public void addKey(String key, Channel channel) {
        keyChannelMap.put(key, channel);
    }

    public Channel getChannel(String channelKey) {
        return keyChannelMap.get(channelKey);
    }
}

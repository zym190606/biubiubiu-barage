package team.redrock.barrage.service;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;
import team.redrock.barrage.been.BarrageMessage;

@Component
public class BarrageCoreService {

    private ChannelGroup group;

    private Gson gson = new Gson();

    public BarrageCoreService(){
        group=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    public void addChannel(ChannelHandlerContext ctx) {
        group.add(ctx.channel());
    }

    public void send(BarrageMessage barrageMsg){
        group.writeAndFlush(new TextWebSocketFrame(gson.toJson(barrageMsg)));
    }

    public void remove(ChannelHandlerContext ctx) {
        group.remove(ctx.channel());
    }

    public boolean isNotBan(String openid) {
        //TODO:ban
        return true;
    }
}

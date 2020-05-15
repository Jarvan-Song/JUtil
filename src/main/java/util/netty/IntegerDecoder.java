package util.netty;

/**
 * Created by songpanfei on 2020-05-15.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class IntegerDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.err.println("IntegerDecoder decode msg is " + msg);
        out.add(msg.readInt());
    }
}

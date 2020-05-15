package util.netty;

/**
 * Created by songpanfei on 2020-05-15.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class IntegerEncoder extends MessageToMessageEncoder<Integer> {

    /**
     * @throws Exception
     */
    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        System.err.println("IntegerEncoder encode msg is " + msg);

        //把int转换为字节数组
        byte[] result = new byte[4];
        result[0] = (byte) (msg.intValue() >>> 24);//取最高8位放到0下标
        result[1] = (byte) (msg.intValue() >>> 16);//取次高8为放到1下标
        result[2] = (byte) (msg.intValue() >>> 8); //取次低8位放到2下标
        result[3] = (byte) (msg.intValue());      //取最低8位放到3下标

        ByteBuf byteBuf = ctx.alloc().buffer(Integer.BYTES);
        byteBuf.writeBytes(result);
        out.add(byteBuf);
    }
}

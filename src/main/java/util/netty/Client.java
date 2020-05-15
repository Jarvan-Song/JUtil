package util.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by songpanfei on 2020-05-15.
 * writeAndFlush 发送不同的数据，需要由不同的编解码器
 *
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8379).sync();
            future.channel().writeAndFlush("你好服务端");
//            future.channel().writeAndFlush(Unpooled.copiedBuffer("你好服务端".getBytes()));
            future.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    static class ClientHandler extends ChannelInboundHandlerAdapter {

        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("ClientHandler channelRegistered");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("ClientHandler channelActive");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                System.out.println("Client: " + msg.getClass());
                if(msg instanceof String){
                    System.out.println("Client: " + msg);
                }else if(msg instanceof ByteBuf){
                    ByteBuf buf = (ByteBuf)msg;
                    byte[] data = new byte[buf.readableBytes()];
                    buf.readBytes(data);
                    String request = new String(data, "utf-8");
                    System.out.println("Client: " + request);
                }else {
                    System.out.println("Client: 未知类型数据");
                }
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            System.out.println("ClientHandler channelReadComplete");
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}

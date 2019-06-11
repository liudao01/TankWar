package com.liuml.tank.net;

import java.util.UUID;

import com.liuml.tank.Direction;
import com.liuml.tank.Tank;
import com.liuml.tank.TankFrame;
import com.liuml.tank.TankGroup;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liuml
 * @explain
 * @time 2019-06-03 17:21
 */
public class Client {

    private Channel sChannel;

//    public static void main(String[] args) {
//        Client c = new Client();
//        c.connect();
//    }

    public void connect() {

        //线程池
        EventLoopGroup group = new NioEventLoopGroup(1);//nio 的线程池
        //当需要引导客户端或一些无连接协议时，需要使用Bootstrap类,创建一个新的 Bootstrap 来创建和连接到新的客户端管道
        Bootstrap bootstrap = new Bootstrap();
        try {

            //ChannelFuture 异步通知  Netty提供了ChannelFuture接口，
            // 其addListener()方法注册了一个ChannelFutureListener，以便在某个操作完成时（无论是否成功）得到通知。
            //ChannelFuture 是个观察者
            ChannelFuture channelFuture = bootstrap.group(group)
                .channel(NioSocketChannel.class)//指定连接到服务器的channel 的类型是nio的
                .handler(new ClientChannelInitializer())//handler 的意思 发生事件的时候交给ClientChannelInitializer 处理
                .connect("localhost", 8888);

            channelFuture.sync();// 服务器异步创建绑定
            System.out.println("client started");

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        sChannel = channelFuture.channel();
                        System.out.println("connected!");
                    }
                }
            });

            //服务器同步连接断开时,这句代码执行
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();//释放资源终结
        }

    }



    /**
     * 发送关闭链接消息
     */
    public void closeConnect() {
        this.sendMsg("_bye_");
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        System.out.println("发送消息" + msg);
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        sChannel.writeAndFlush(buf);
    }

}

//ChannelInitializer 它提供了一种简单的方法，可以在通道注册到其EventLoop后对其进行初始化。
class ClientChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
            .addLast(new TankMsgEncoder())//客户端加入编码器
            .addLast(new TankMsgDecoder())//客户端加入解码器
            .addLast(new ClientHandler());
    }
}

//ChannelInboundHandler的一个简单实现，默认情况下不会做任何处理，
// 只是简单的将操作通过fire*方法传递到ChannelPipeline中的下一个ChannelHandler中让链中的下一个ChannelHandler去处理。
class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("发送坦克位置");
        //发送坦克的位置
        ctx.writeAndFlush(new TankJoinMsg(5, 10, Direction.DOWN, false, TankGroup.Enemy, UUID.randomUUID()));
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TankJoinMsg tankJoinMsg) throws Exception {
        //获取从服务端返回的数据
        System.out.println("客户端接收到消息 "+tankJoinMsg.toString());
        Tank t = new Tank(tankJoinMsg);
        TankFrame.INSTANCE.addTank(t);
//        System.out.println(tankJoinMsg.toString());
    }
}









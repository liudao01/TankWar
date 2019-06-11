package com.liuml.tank.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author liuml
 * @explain
 * @time 2019-06-04 14:00
 */
public class Server {

    //    通道组
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    public void serverStart()  {
        //线程池
        //bossGroup，这个线程池处理客户端的连接请求，并将accept的连接注册到subReactor的其中一个线程上；
        //workerGroup，负责处理已建立的客户端通道上的数据读写
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);//nio 的线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);//用于工作的

        //服务的bootstrap
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            ChannelFuture future = bootstrap.group(bossGroup, workerGroup)// 绑定线程池
                .channel(NioServerSocketChannel.class)//指定使用的channel
                .childHandler(new ChannelInitializer<SocketChannel>() {// 绑定客户端连接时候触发操作
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline
                            .addLast(new TankMsgDecoder())//服务端加入解码器
                            .addLast(new TankMsgEncoder())//服务端加入编码
                            .addLast(new ServerChildHandler());//// 客户端触发操作
                    }
                })
                .bind(8888)
                .sync();// 服务器异步创建绑定
            System.out.println("server started");

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                    }
                }
            });

            //结束后关闭 关闭服务器通道
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //结束  shutdown 单词 终止  LOL中连杀被终结播放的语音
            workerGroup.shutdownGracefully();//// 释放线程池资源
            bossGroup.shutdownGracefully();
        }


    }


}

class ServerChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //channelActive 通道活动 创建的时候被调用
        Server.clients.add(ctx.channel());//假如有多个客户端链接那么 需要把新接入的客户端加入通道组

        //Successful connection
//        ByteBuf buf = Unpooled.copiedBuffer("server : welcome  ".getBytes());
//        Server.clients.writeAndFlush(buf);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收到数据了");
        //把接收到的消息在每个客户端都写出去
        System.out.println(msg.toString());
        Server.clients.writeAndFlush(msg);

//        ByteBuf buf = null;
//        try {
//            TankJoinMsg tm = (TankJoinMsg)msg;
//
//            System.out.println(tm);
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
       /* buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        String s = new String(bytes);
        System.out.println("server " + s);
        ServerFrame.INSTANCE.updateClientMsg(s);
        if (s.equals("_bye_")) {
            //客户端请求退出
        ServerFrame.INSTANCE.updateServerMsg(s);
            System.out.println("客户端请求退出");
            Server.clients.remove(ctx.channel());
            ctx.close();
        } else {
            Server.clients.writeAndFlush(buf);
        }*/
    }

    /**
     * 非正常退出处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("非正常退出");
        cause.printStackTrace();
        //删除出现异常的客户端channle，并关闭连接
        Server.clients.remove(ctx.channel());
        ctx.close();

    }
}









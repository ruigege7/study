package com.yangr.study.test;

//没有引入多路复用器的代码

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {

    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        //创建NIO
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        //设置非阻塞
        serverSocket.configureBlocking(false);
        System.out.println("服务启动。。");
        while (true) {
            //非阻塞模式accept方法不会阻塞，否则会阻塞
            //NIO的非阻塞模式是由操作系统内部实现，底层调用了Linux内核的accept函数
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("连接成功");
                //设置socketchannel为非阻塞
                socketChannel.configureBlocking(false);
                //保存客户端连接到list
                channelList.add(socketChannel);
            }
            //遍历连接读数据
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                //非阻塞模式read 方式不会阻塞 否则会阻塞
                int len = sc.read(byteBuffer);
                if (len > 0) {
                    System.out.println("接收到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) { // 如果客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }
}

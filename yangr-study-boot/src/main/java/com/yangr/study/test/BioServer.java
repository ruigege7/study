package com.yangr.study.test;

import java.net.ServerSocket;
import java.net.Socket;


public class BioServer {



    public static void main(String[] args) throws  Exception {

        ServerSocket serverSocket = new ServerSocket(9000);
        while (true){
            System.out.println("等待连接");
            //阻塞连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接。。。");
//            handle(clientSocket);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handle(clientSocket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public  static void handle(Socket clientSocket) throws  Exception{
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read 完毕。");
        if (read !=-1){
            System.out.println("接收到客户端数据：" + new String(bytes,0,read));
        }
        clientSocket.getOutputStream().write("helloClient".getBytes());
        clientSocket.getOutputStream().flush();
    }
}
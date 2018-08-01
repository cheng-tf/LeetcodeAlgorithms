package July5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerNewThread {
    public static void main(String[] args) throws Exception {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9090);
            System.out.println("服务器开启了......");
            Socket socket = null;
            while (true) {
                socket = server.accept();
                System.out.println("收到一个请求");
                new Thread(new Task(socket)).start();//开启一个线程去处理这个请求
            }
        } catch (Exception e) {

        } finally {
        }
    }
}
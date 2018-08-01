package July5;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = null;
        ExecutorService threadPool = null;
        try {
            threadPool = Executors.newFixedThreadPool(100,Executors.defaultThreadFactory());
            server = new ServerSocket(9090);
            System.out.println("服务器开启了......");
            Socket socket = null;
            while (true) {
                socket = server.accept();
                System.out.println("收到一个请求");
                threadPool.submit(new Task(socket));//从线程池中获取一个线程去处理这个请求
            }
        } catch (Exception e) {
        } finally {
        }
    }
}


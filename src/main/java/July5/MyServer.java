package July5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws Exception{
        ServerSocket server = null;
        try {
            server = new ServerSocket(9090);
            Socket socket = null;
            BufferedReader reader = null;
            PrintWriter pw = null;
            while (true) {
                socket = server.accept();
                System.out.println("收到请求了");
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw = new PrintWriter(socket.getOutputStream(),true);
                String line = null;
                while(true){
                    System.out.println("waiting....");
                    line = reader.readLine();
                    System.out.println("get the data!");
                    if(line.equals("end")) break;//读取到end表示结束
                    System.out.println("收到的内容："+ line);
                }
                System.out.println("跳出循环了");
                pw.println("你好，收到了:这是给你的Response!");
                pw.println("end");
                System.out.println("结束了一次请求一次回复");
                reader.close();
                pw.close();
                socket.close();
            }
        }catch(Exception e){
            if(!server.isClosed())
                server.close();
            System.out.println("服务器关闭");
        }finally{
        }
    }
}

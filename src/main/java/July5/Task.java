package July5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Task implements Runnable {
    private Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter pw = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            String line = null;
            while (true) {
                System.out.println("waiting....");
                line = reader.readLine();
                System.out.println("get the data!");
                if (line.equals("end")) break;//读取到end表示结束
                System.out.println("收到的内容：" + line);
            }
            pw.println("你好，收到了:这是给你的Response!");
            pw.println("end");//发送结束标志
            System.out.println("结束了一次请求一次回复");
        } catch (Exception e) {
            System.out.println("服务器关闭");
        } finally {
            try {
                reader.close();
                pw.close();
                socket.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }
}


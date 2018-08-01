package July5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        Socket client = null;
        BufferedReader reader = null;
        PrintWriter pw = null;
        try {
            client = new Socket("127.0.0.1", 9090);
            pw = new PrintWriter(client.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw.println("AAAAAAA");
            pw.println("BBBBBBBB");
            pw.println("end");
            String line = null;
            while(true){
                System.out.println("waiting....");
                line = reader.readLine();
                System.out.println("get the data!");
                if("end".equals(line)) break;
                System.out.println("br.readLine() = " + line);
            }
        }catch(Exception e){
            System.out.println("异常了");
        }finally{
            try {
                reader.close();
                pw.close();
                client.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }

    }
}

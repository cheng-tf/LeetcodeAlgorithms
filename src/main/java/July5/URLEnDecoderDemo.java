package July5;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEnDecoderDemo {
    public static void main(String[] args) throws Exception{
        String s = "我爱我的国家!China";
        String urlEncodes = URLEncoder.encode(s,"UTF-8");
        System.out.println("urlEncodes = " + urlEncodes);
        String urlDecodes = URLDecoder.decode(urlEncodes,"UTF-8");
        System.out.println("urlDecodes = " + urlDecodes);
        
    }
}

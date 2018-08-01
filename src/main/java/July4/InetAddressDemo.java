package July4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) {
        byte[] ips = new byte[]{10,0,0,(byte)211};
        try {
            InetAddress ip = InetAddress.getByAddress(ips);
            System.out.println("ip = " + ip);
        }catch(UnknownHostException e){
            System.out.println("IP地址未知");
        }
    }
}

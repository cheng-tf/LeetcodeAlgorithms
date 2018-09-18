package JavaMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

public class QQSendMailDemo {

	public static void main(String[] args) {
		
		int[] a = {1,3,3,4};
		Arrays.sort(a);
		 // 收件人电子邮箱
	      String to = "zhaohongzxy@bupt.edu.cn";
	 
	      // 发件人电子邮箱
	      String from = "zhaohongzxy@163.com";
	 
	      // 指定发送邮件的主机为 smtp.qq.com
	      String host = "smtp.163.com";  //QQ 邮件服务器
	 
	      // 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", host);
	 
	      properties.put("mail.smtp.auth", "true");
	      // 获取默认session对象
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication("zhaohongzxy@163.com", "13465002852shuo"); //发件人邮件用户名、密码
	        }
	       });
	 
	      try{
	         // 创建默认的 MimeMessage 对象
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: 头部头字段
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: 头部头字段
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         // Set Subject: 头部头字段
	         message.setSubject("This is the Subject Line ");
	 
	         // 设置消息体
	         message.setText("This is actual message,OK ...nihaoma,今天下午开会，周知，谢谢 ");
	 
	         // 发送消息
	         InternetAddress address = new InternetAddress("zhaohongzxy@bupt.edu.cn");
	         InternetAddress address2 = new InternetAddress("zhaohong@jd.com");
	         InternetAddress[] addresses = new InternetAddress[] {address,address2};
	         //Address address2 = new InternetAddress("zhaoyiyuan@gmail.com");
	         Transport.send(message,addresses);
	         System.out.println("Sent message successfully....from runoob.com");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

}

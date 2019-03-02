package queryworkday;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 注释
 * @Author zhaohong
 * @Time 2019-02-27 18:15
 */
public class GetInfoFromWeb {

    private static SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyyMMdd");

    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public char doGetProcess(String url){
        String res = doGet(url);
        return res.charAt(res.length()-4);
    }

    public char[] getOneYear() throws  Exception{
        String urlPrefix = "http://api.goseek.cn/Tools/holiday?date=";
        Date firstDay = DateUtils.parseDate("2019-01-01","yyyy-MM-dd");
        char[] flags = new char[367];
        flags[0] = 'X';
        for (int i = 1; i < 367 ; i++) {
            flags[i] = doGetProcess(urlPrefix + dateFormatDate.format(firstDay));
            firstDay = DateUtils.addDays(firstDay,1);//加一天
        }
        return flags;
    }

    @Test
    public void test() throws Exception{
//        char[] flags = getOneYear();
//        System.out.println(new String(flags));
        System.out.println("Calendar.getInstance() = " + Calendar.getInstance());
    }

    @Test
    public void test2() throws Exception{
        String urlPrefix = "http://api.goseek.cn/Tools/holiday?date=";
        int year = Calendar.getInstance().getWeekYear();
        Date startDate = DateUtils.parseDate(year+"-01-01", "yyyy-MM-dd");
        Date startDate2 = DateUtils.parseDate(year+"-01-01", "yyyy-MM-dd");
        System.out.println(year);
//        String result = doGet(url);
//        System.out.println("result = " + result);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.parseDate("2019-01-01","yyyy-MM-dd"));
        System.out.println("calendar = " + calendar);
        System.out.println("calendar = " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("calendar = " + calendar.get(Calendar.DAY_OF_YEAR));

        String res = doGet(urlPrefix+"20190101");
        System.out.println("res = " + res);

    }
    @Test
    public void test3(){
        String str = "X100033000003300000330000033000002211111110000033000003300000330000033000003300000330000033000011100000330000033000003300100330000033000003300000330000033000011100000330000033000003300000330000033000003300000330000033000003300000330000033000003300000330000111000003300000320111111100002300000330000033000003300000330000033000003300000330000033000003300000330000033000";
        System.out.println("str.length() = " + str.length());
    }

}

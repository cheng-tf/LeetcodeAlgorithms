package JSON;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class FastJSONTest {
    @Test
    public void test(){
        String str = JSON.toJSONString(new User(1,"赵红","男",new Date()));
        System.out.println(str);
        User user = (User)JSON.parseObject(str,User.class);
        System.out.println("user = " + user);
        System.out.println("user.getBirthday() = " + user.getBirthday());
        System.out.println("user.getName() = " + user.getName());
        System.out.println("user.getSex() = " + user.getSex());
    }
}

package Others;

import org.junit.Test;

import java.lang.reflect.Field;

public class IntegerSourceCode {

    /**
     * 基本数据类型的自动装箱
     */
    @Test
    public void test0(){
        Byte by = 23;
        Short s = 2;
        Integer t = 19;
        Long l = 1L;//需要添加L或l
        Character cg = '2';
        Float f = 3.2f;//f或F
        Double du = 22d;//d或D
        Boolean b = true;
    }

    /**
     *判断-128 -- 127的Integer对象是否相等
     * Integer内部封装了-128到127的Integer池
     */
    @Test
	public  void test1( ){
        int[] nums = {1,2,127,-129,-128,128,129};
        for(int num :nums){
            //自动装箱：调用的是Integer.valueOf方法：valueOf源码解读一下
            System.out.println("自动装箱:"+ num + ((Integer)num == (Integer)num));//自动装箱，对于-128--127的是同一个对象
            System.out.println("通过new方式创建：" + num +(new Integer(num) == new Integer(num)));//通过new方式都是创建新的对象
        }

	}

    /**
     * 定义一个swap函数，实现交换a和b的值
     * 由于Java中都是值传递，Integer做形参时接收到只是Integer的intValue。
     */
        @Test
        public  void test3() {
            Integer a = 1;
            Integer b = 2;
            System.out.println("a = " +a+"; b = "+b);
            swap(a,b);//十分暴力，修改了Integer内部封装的Integer数组-128至127
            System.out.println("a = " + a +"; b = " + b);
            for(int num = 1;num < 4;num++){
                Integer i = num;
                System.out.println(" num = "+num+"; Integer num = " + i.intValue());
            }
        }

    @Test
    public  void test32() {
        Integer a = 129;
        Integer b = 130;
        System.out.println("a = " + a + "; b = "+b);
        swap(a,b);
        System.out.println("a = " + a +"; b = " + b);
    }

    public void swap2(Integer a,Integer b){
            Integer temp = a;
            b = a;
            a = temp;
    }

    public void swap3(Integer a,Integer b){
            try {
                Field field = a.getClass().getDeclaredField("value");
                int temp = a.intValue();
                field.setInt(a,b.intValue());
                field.setInt(b,temp);
            }catch(Exception e){
                System.out.println(e);
            }

    }

    /**
     * 通过反射机制实现交换两个值,只适用于-128至127的整型；
     * 因为只有-128至127的Integer是保持同一个对象，即形参与实参是同一个对象
     * @param b
     */
        private  void swap(Integer a, Integer b) {
            Field field = null;
            try {
                field = Integer.class.getDeclaredField("value");
                field.setAccessible(true);//强攻
                int temp = a.intValue();
                field.set(a, b);
                field.setInt(b, temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Test
        public void test4(){
            int a =  222;
            int b = 333;
            System.out.println("a = " +a+"; b = "+b);
            swap1(a,b);
            System.out.println("a = " +a+"; b = "+b);
        }

    /**
     * 形参与实参是不同的对象
     */
        private void swap1(Integer a,Integer b){
            Integer temp = a;
            b = a;
            a = temp;
        }




}

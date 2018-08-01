package cn.edu.bupt;

public class MonitorObject {

    private  static int num =0;
    public int addAndGetNum(){
        synchronized (this){
            num = num + 1;
        }
        return num;
    }
    public int addAndGetNum2(){
        synchronized (MonitorObject.class){
            num = num + 1;
        }
        return num;
    }

}

package cn.edu.bupt;

public enum SingletonByEnum {
    INSTANCE;//枚举类型定义单例十分简单，只需要定义一个枚举元素就可以
    //其他方法
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

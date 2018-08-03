package singleton;

/**
 * 利用枚举实现
 */
public enum EnumSingleton {
    INSTANCE{
        public String toString(){
            return "Enum Singleton Instance";
        }
    };
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}

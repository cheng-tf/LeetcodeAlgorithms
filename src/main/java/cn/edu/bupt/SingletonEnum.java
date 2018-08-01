package cn.edu.bupt;

public enum SingletonEnum {
    Instance1{
        public String getString(){
            return "Hello World!";
        }
        public int getInt(){
            return 100;
        }
    },
    Instance2{
        public String getString(){
            return "Hello University";
        }
    },
    Instance3;
    public String getString(){
        return "Hello Enum!";
    }
    public int getInt(){  throw new ArrayIndexOutOfBoundsException();  }
}

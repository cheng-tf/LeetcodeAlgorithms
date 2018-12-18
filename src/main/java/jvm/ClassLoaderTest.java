package jvm;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    /**
     * 自定义一个类加载器ClassLoader
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                String filename = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream is = getClass().getResourceAsStream(filename);
                if(is == null) //交给上级加载
                    return super.loadClass(name);
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name,b,0,b.length);
                }catch(IOException e){
                    System.out.println("e = " + e);
                    throw new ClassNotFoundException();//抛出异常
                }
            }
        };

        Object obj = classLoader.loadClass("jvm.ClassLoaderTest").newInstance();
        System.out.println("obj.getClass() = " + obj.getClass());//class jvm.ClassLoaderTest
        System.out.println(obj instanceof ClassLoaderTest);//false
    }
}

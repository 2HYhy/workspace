public class LoaderTest {
     public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("1.可以获取系统类加载器: " + classLoader);

        classLoader = classLoader.getParent();
        System.out.println("2.可以获取系统类加载器的父类加载器,即扩展类加载器: " + classLoader);

        classLoader = classLoader.getParent();
        System.out.println("3.不可以获取扩展类加载器的父类加载器,即引导类加载器: " + classLoader);

        try {
            classLoader = Class.forName("com.me.gacl.LoaderTest").getClassLoader();
            System.out.println("4.可以获取指定类是由哪个类加载器加载的: " + classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            classLoader = Class.forName("java.lang.Object").getClassLoader();
            System.out.println("5.不可以获取JDK提供的Object是由引导类加载器加载的: " + classLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
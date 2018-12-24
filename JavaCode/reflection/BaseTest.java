//利用反射获取对象信息的整体流程

public class BaseTest {
    @Test
    public void testIt() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName("Child");
        // Constructor appleConstructor = clazz.getConstructor();
        // Object obj = appleConstructor.newInstance();
        //等同于以上2行
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("setAge", int.class);
        String result = String.valueOf(method.invoke(obj, 22));
        System.out.println(result);
    }

class Child extends Father {
    public String setAge(int age) {
        return "input age = " + age;
        }
    }
}
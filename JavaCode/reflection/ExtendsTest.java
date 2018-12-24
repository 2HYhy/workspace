public class ExtendsTest {

    @Test
    public void testOne() {
        Class clazz = Child.class;
        Class superClass = clazz.getSuperclass();
        System.out.println("类的父亲: " + superClass);
    }

    //访问父类的方法
    @Test
    public void testTwo() {
        Object obj = new Child();
        //对象，方法名，请求参数
        Object res1 = ReflectionUtil.invokeMethod(obj, "getPublic", null, null);
        Object res2 = ReflectionUtil.invokeMethod(obj, "getProtected", null, null);
        Object res3 = ReflectionUtil.invokeMethod(obj, "getPrivate", null, null);
        Object res4 = ReflectionUtil.invokeMethod(obj, "getDefault", null, null);
        Object[] number = {"100"};
        Class<?> [] parameterTypes = {String.class};
        Object res5 = ReflectionUtil.invokeMethod(obj, "showPublic", parameterTypes, number);
        System.out.println(res1 + "\n" + res2 + "\n" + res3 + "\n" + res4 + "\n" + res5) ;
    }

    //访问父类的属性
    @Test
    public void testThree() throws Exception {
        Object obj = new Child() ;

        //获取父类属性的名字
        Field publicField = ReflectionUtil.getDeclaredField(obj, "publicKey") ;
        if (publicField != null) {
            System.out.println(publicField.getName());
        }
        System.out.println("----------------");

        //为父类属性赋值
        ReflectionUtil.setFieldValue(obj, "publicKey", "公共类型") ;
        ReflectionUtil.setFieldValue(obj, "protectKey", "保护类型") ;
        ReflectionUtil.setFieldValue(obj, "privateKey", "私有类型") ;
        ReflectionUtil.setFieldValue(obj, "defaultKey", "默认类型") ;
        //获取父类属性值
        System.out.println("publicField = " + ReflectionUtil.getFieldValue(obj, "publicKey"));
        System.out.println("defaultField = " + ReflectionUtil.getFieldValue(obj, "protectKey"));
        System.out.println("protectedField = " + ReflectionUtil.getFieldValue(obj, "privateKey"));
        System.out.println("privateField = " + ReflectionUtil.getFieldValue(obj, "defaultKey"));
    }
}

public class Child extends Father {
   //
}

public class Father {
    public String publicKey;
    protected String protectKey;
    private String privateKey;
    String defaultKey;

    public String getPublic() {
        return "public method belong to Father";
    }

    public Integer showPublic(String num) {
        return Integer.valueOf(num);
    }

    protected String getProtected() {
        return "protected method belong to Father";
    }

    private String getPrivate() {
        return "private method belong to Father";
    }

    String getDefault() {
        return "default method belong to Father";
    }
}

public class ReflectionUtil {

    public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes, Object [] parameters) {
        Method method = getDeclaredMethod(object, methodName, parameterTypes) ;
        if (method != null) {
            method.setAccessible(true) ;
        }
        try {
            if(null != method) {
                return method.invoke(object, parameters) ;
            }
        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes){
        Method method;

        for(Class<?> clazz = object.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes) ;
                return method ;
            } catch (Exception e) {
                //不能抛出去,异常打印或者往外抛，则就不会进入父类
            }
        }
        return null;
    }

    public static Field getDeclaredField(Object object, String fieldName){
        Field field;
        Class<?> clazz = object.getClass() ;
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName) ;
                return field ;
            } catch (Exception e) {
                //如果这里的异常打印或者往外抛，就不会进入父类
            }
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, Object value){
        Field field = getDeclaredField(object, fieldName) ;
        if (field != null) {
            field.setAccessible(true) ;
        }
        try {
            if (field != null) {
                field.set(object, value) ;
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static Object getFieldValue(Object object, String fieldName){
        Field field = getDeclaredField(object, fieldName) ;
        if (field != null) {
            field.setAccessible(true) ;
        }
        try {
            return field.get(object) ;
        } catch(Exception e) {
            e.printStackTrace() ;
        }
        return null;
    }
}

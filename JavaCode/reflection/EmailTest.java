public class EmailTest {
     public static void main(String [] args) throws NoSuchFieldException {
        EmailDO email = new EmailDO();
        Class classOne = email.getClass();
        Class classTwo = EmailDO.class;
        try {
            Class classThree = Class.forName("com.me.gacl.entity.EmailDO");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(classOne + "\n" +classTwo + "\n" + classThree);

        boolean flag = classOne.equals(classTwo);
        System.out.println(flag);

        String name = classTwo.getName();
        System.out.println("类完整的名字: " + name);

        Field[] fields = classTwo.getFields();
        System.out.println("类public的属性: " + Arrays.toString(fields));

        Field [] fieldsAll = classTwo.getDeclaredFields();
        for (Field field : fieldsAll)
        System.out.println("类所有的属性: " + field);

        Field field = classTwo.getField("email");
        System.out.println("类指定的public属性: " + field);

        Filed fieldNew = classTwo.getField("content");
        fieldNew.setAccessible(true);
        EmailDO emailDo = new EmailDO("123@qq.com", "email-content");
        System.out.println("类的私有属性: " + fieldNew.get(emailDo)); 


        Method[] methods = classTwo.getMethods();
        System.out.println("类public的方法: " + Arrays.toString(methods));

        Method[] methodsAll = classTwo.getDeclaredMethods();
        for (Method method : methodsAll) {
            System.out.println("类所有的方法: " + method);
        }

        try {
            Method method1 = classTwo.getMethod("setEmail", String.class);
            //若参数是int型，就必须写成int.class
            Method method2 = classTwo.getMethod("setId", Integer.class);
            System.out.println("类指定的方法: " + method1 + "\n" + method2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Constructor[] constructors = classTwo.getConstructors();
        System.out.println("类的构造方法: " + Arrays.toString(constructors));

        try {
            Object object = classTwo.newInstance();
            field.set(object, "123456@qq.com");
            System.out.println("创建一个类的实例，为类的属性赋值:" + field.get(object));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Object obj = classTwo.newInstance();
            Method method = classTwo.getMethod("testEmail", String.class);
            //invoke表示执行方法，param1为类对象，param2为请求参数
            Object res = method.invoke(obj, "lucky@qq.com");
            System.out.println("执行testEmail，该方法的返回结果是 " + res.toString());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

public class EmailDO {
    private Integer id;
    public String email;
    private String content;
    private String pkgName;
    private Timestamp sendTime;

    EmailDO(String email, String content) {
        this.email = email;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getpkgName() {
        return pkgName;
    }

    public void setpkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Timestamp getsendTime() {
        return sendTime;
    }

    public void setsendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String testEmail(String email) {
        return "test email finish, parameter = " + email;
    }

    @Override
    public String toString() {
        return "EmailDO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
### 添加maven依赖
```
 <!--自动生成API文档 -->
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.6.0</version>
    </dependency>
    <!--展示API文档-->
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.6.0</version>
    </dependency>
```  
### 创建swagger配置类
```
@Configuration   //让spring来加载该配置类
@EnableSwagger2 //启动Swagger2
//@ComponentScan(basePackages = {"com.hyh.gacl"})
public class Swagger2 {

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hyh.gacl"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        //方式一
        return new ApiInfoBuilder()
                .title("Spring Boot利用Swagger自动生成API文档")
                .description("省略手写API文档的步骤，让开发过程更简化")
                .contact("hyh.he")
                .version("1.0")
                .build();

        //方式二
        Contact concact = new Contact("HYH", "http://hyh.com", "3936123@qq.com");
        return new ApiInfoBuilder()
                .title("Spring Boot利用Swagger自动生成API文档")
                .description("省略手写API文档的步骤，让开发过程更简化")
                .license("Apache License Version 2.0")
                .contact(concact)
                .version("2.0")
                .build();
    }
}
```  
### 创建controller控制类
```
@RestController
@RequestMapping("/hyh")
@Api("UserController") //可以不添加，会默认添加
public class UserController {

    /**
     * 获取用户信息
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("获取用户信息")//具体方法右侧信息
    @ApiImplicitParams({// paramType表示参数的位置，常用的有header即@RequestHeader、query即@RequestParam、path即@PathVariable
            @ApiImplicitParam(paramType = "header", name = "username", dataType = "String", required = true, value = "用户的姓名", defaultValue = "momo"),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "用户的密码", defaultValue = "123456")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数不正确"),
            @ApiResponse(code = 404, message = "请求路径或页面跳转路径不对")
    })
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public UserDO getUserInfo(@RequestHeader("username")String username, @RequestParam("password")String password){
        UserDO user = new UserDO();
        user.setName(username);
        user.setPassword(password);
        return user;
    }

    /**
     * 创建新用户
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据user对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体", required = true, dataType = "UserDO")//不能用在方法的形参中
    @ApiResponses({
            @ApiResponse(code = 401, message = "未认证"),
            @ApiResponse(code = 403, message = "请求被禁止")
    })
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody UserDO user) {
        Map<Integer, UserDO> users = Collections.synchronizedMap(new HashMap<Integer, UserDO>());//线程同步
        users.put(user.getId(), user);
        return "success";
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @ApiOperation("更新用户信息")
    @PostMapping("/updateUserInfo")
    public int updateUserInfo(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true)UserDO user){
        Map<Integer, UserDO> users = Collections.synchronizedMap(new HashMap<Integer, UserDO>());
        users.put(user.getId(), user);
        System.out.println("name = "+user.getName()+", age = "+ user.getAge());
        return 1;
    }

    @ApiOperation(value = "删除用户信息", notes = "根据用户的id删除用于信息",httpMethod = "GET")
    @RequestMapping("/removeUser/{id}")
    public String removeUser(@PathVariable("id")Integer id){
        return "删除用户的id = "+ id;
    }

    /**
     * swagger忽略的API
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/", method = RequestMethod.GET) 
    public String home(){
        return "hello";
    }
}
```   
**自动生成的API接口文档如图片所示:**    
![alt-text](/images/create.jpeg)   
![alt-text](/images/get.jpeg)    
![alt-text](/images/update.png)    
![alt-text](/images/remove.png) 

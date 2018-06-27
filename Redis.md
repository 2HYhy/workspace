## redis   
下载链接:[https://redis.io/download](https://redis.io/download),stable版。  

1. 下载解压后，进入安装目录路径，依次执行`make`, `make test`, `make install`,编译安装redis。  
2. mac启动redis服务端，运行`redis-server`。 
3. mac启动redis客户端，运行`redis-cli`。
4. 关闭redis:`quit` 或者 `exit`。  
5. 成功启动redis的效果图如: ![alt-text](/images/redis1.png)  
6. 程序运行结果图: ![alt-text](/images/redis2.png) 
7. 连接远程的redis服务器:`redis-cli -h host -p port -a password`

## docker启动redis服务:
```java
docker run --name myredis -d -p 6500:6379 redis
//重启一个页面
redis-cli -a redis -h 127.0.0.1 -p 6500   
```
  
## 操作命令(redis是使用内存存储的非关系数据库):     
> config get requirepass   
>- 查看当前redis有没有设置密码     

> config set requirepass 123456
>- 设置密码(redis重启之后密码就会失效)

> redis-server.exe  
> redis-cli.exe -h 127.0.0.1 -p 6379 (重新打开一个命令框输入)
>- 启动该redis服务器  

> set key value   
> get key   
>- String字符串赋值，取值 

> del key
>- 删除key  

> keys *  
>- 查看所有key

> more key db  
>- 当前db的key移到指定db中 

> exists key  
>- 检查key是否存在  

> persist key  
>- 移除key的过期时间 

> expire key seconds   
> pexpire key millseconds   
> epireat key timestamp   
>- 为key设定过期时间，单位：秒，毫秒，时间戳 

> rename key newname   
>- 修改key的名字 

> type key  
>- 返回key所存储的值的类型  

> config set requirepass "your pwd"  
> config get requirepass  
>- 为数据库设置密码，并查看密码

> flushdb  
>- redisDeskManage清空redis缓存

> select 0/1/2    
>- redisDeskManage选择要操作的数据库

#### 注解:   
启动类或者具体的DAO类中添加注解@EnableCaching:
此注解会对每个bean中被@Cacheable, @CachePut, @CacheEvict修饰的public方法进行缓存操作。   

#### @Cacheable用法(value属性是必须的)
```java
@Cacheable(value = "companyCache:", key = "'companyCache:'.concat(#root.methodName)")
```

#### @CacheEvict用法
```java
 @CacheEvict(value = "companyCache", key = "'myCompanyCache:'.concat('findByCompanyId')")
    public void updateCompanyId(){}
 // allEntries = true, 清除缓存中所有元素
 // beforeInvocation = true, 清除操作在对应方法成功执行后触发，即方法因为抛出异常未能成功返回不会触发该操作   
```
> 可用在update类方法上，也可用在remove类方法上。

#### @cachePut用法
> 每次都会执行方法，并将结果进行缓存。用法与@Cacheable用法一致,用在update类方法上。

#### @Caching用法
> 可以包含以上三个注解，key-value分别对应(cachable=[@Cacheable], put=[@CachePut], evict=[@CacheEvict])。

#### @CacheConfig用法
> 类级的注解，统一指定缓存的value和key。

### 自定义KeyGenerator和CacheManager  
```java
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }
}
```

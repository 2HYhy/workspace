## redis 下载链接:[https://redis.io/download](https://redis.io/download),stable版。
1. 下载解压后，进乳安装目录路径，依次执行`make`, `make test`, `make install`,编译安装redis。  
2. mac启动redis服务端，运行`redis-server` 或者 `./redis-server redis.conf`。 
3. mac启动redis客户端，运行`redis-cli`。
4. 关闭redis:`quit` 或者 `./redis-cli -p 6379 shutdown`。  
5. 成功启动redis的效果图如: ![alt-text](/images/redis1.png)  
6. 程序运行结果图: ![alt-text](/images/redis2.png)
7.连接远程的redis服务器:`redis-cli -h host -p port -a password`

#### docker启动redis服务:
```java
docker run --name myredis -d -p 6500:6379 redis
redis-cli -a redis -h 127.0.0.1 -p 6500   //重启一个页面
```
  
## 操作命令(redis是使用内存存储的非关系数据库):     
> redis-server.exe  
> redis-cli.exe -h 127.0.0.1 -p 6379 (重新打开一个命令框输入)
>- 启动该redis服务器  

> set key value   
> get key   
>- String字符串赋值，取值 

> hmset key field value  
>- hash哈希赋值,适用于存储对象

> hget all key  
>- 获取对象所有信息  

> hget key field  
>- 获取对象的某属性值  

> hkeys key  
>- 获取对象的所有属性值  

> hdel key field  
>- 删除对象的某属性值 

> lpush key value / rpush key value  
>- 添加一个元素到列表的头(左)部或尾(右)部  

> lrange key start end   
>- 获取指定范围内的元素  

> llen key  
>- 获取key的长度  

> lpop / rpop  key  
>- 取出并移除key中的第一个(最后一个)元素    

> sadd key value  
>- 添加元素到集合中，成功返回1，元素已存在返回0，其余返回nil  

> smembers key  
>- 取出集合中的元素

> srem key value  
>- 从集合中移除元素

> zadd key count value 
>- 添加元素到有序集合中  

> zrangebyscore key start end  
>- 获取有序集合中指定范围的元素  

> zcount key min max  
>- 获取有序集合中score在min和max之间的元素的个数  

> zscore key member   
>- 获取该元素的score  

> del key
>- 删除key  

> keys *  
>- 查看所有key

> dump key  
>- 序列化指定的key 

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

## 注解:   
启动类或者具体的DAO类中添加注解@EnableCaching:
此注解会对每个bean中被@Cacheable, @CachePut, @CacheEvict修饰的public方法进行缓存操作。   

#### @Cacheable用法(value属性是必须的)
```java
@Cacheable(value = "companyCache", key = "'myCompanyCache:'.concat(#root.methodName)")
public void findByCompanyId(){ }
```
> redis中生成: `companyCache~keys` 和 `myCompanyCache: (文件夹)   
myCompanyCache:findByCompanyId (key)
`

**正确应设置成**:  
```java
@Cacheable(value = "companyCache:", key = "'companyCache:'.concat(#root.methodName)")
```

#### @CacheEvict用法
```java
 @CacheEvict(value = "companyCache", key = "'myCompanyCache:'.concat('findByCompanyId')")
    public void updateCompanyId(){}
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

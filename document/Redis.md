### 基本概念
1. 在项目中使用redis，主要考虑2个角度:性能和并发。
2. redis是单线程工作模型。
3. 使用内存存储的非关系型数据库。
> 优点:
>- 读写速度快，因为数据保存在内存中   
>- 支持丰富的数据类型(list,set,hash,string)
>- 支持事务，操作都是原子性的(对数据的更改要么全都执行，要么全不执行)
>- 处理集群和分布式缓存都比较方便    

> 缺点:
>- 不具备自动容错和恢复功能    
>- 较难支持在线扩容

4. 下载链接:[https://redis.io/download](https://redis.io/download),stable版。  
> 下载解压后，进入安装目录路径，依次执行`make`, `make test`, `make install`,编译安装redis。  
> mac启动redis服务端，运行`redis-server`。 
> mac启动redis客户端，运行`redis-cli`。
> 关闭redis:`quit` 或者 `exit`。  
> 成功启动redis的效果图如: ![alt-text](/images/redis1.png)  
> 程序运行结果图: ![alt-text](/images/redis2.png) 
> 连接远程的redis服务器:`redis-cli -h host -p port -a password`

5. Redis服务器之间是可以互相通信，共享数据的。客户端只需要知道主服务器就可以了，不用关心数据时如何在各个服务器间分配的。
6. 支持数据持久化，将数据从内存持久化到硬盘中，Redis提供了两种持久化方案:基于snapshot快照的全量模式和基于AOF（append-onlu file）的增量模式。
7. 支持数据备份。通过master-slave模式，将Master节点中的数据定期备份到数台Slave节点上，一旦Master节点出现故障，可以迅速将请求切换到Slave节点，保证系统的可用性。

### 操作命令:     
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

> set key 'string'
> get key
>- 存储String字符串

> hmset key field1 'value1' field2 'value2'
> hmget key filed1 [field2]
>- 存储hash值

> lpush key 'value1' 'value2'
> lrange key
>- 存储list列表

> sadd key 'value1' 'value2' 
> smembers key
>- 存储set无序集合

> zadd key score1 'value1' score2 'value2'
> zrange key index1 index2 [withscores]
>- 存储zset有序集合，score必须是数字，index是存入元素的下标，一般从0开始

### redisDeskManage的命令   
> flushdb  
>- 清空redis缓存

> select 0/1/2    
>- 选择要操作的数据库

### docker启动redis服务:
```java
docker run --name myredis -d -p 6500:6379 redis
//重启一个页面
redis-cli -a redis -h 127.0.0.1 -p 6500   
```
  
### 涉及的注解   
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
 // allEntries = true, 清除缓存中所有元素，默认fasle
 // beforeInvocation = true, 清除操作在对应方法成功执行后触发，即方法因为抛出异常未能成功返回不会触发该操作   
 // condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
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


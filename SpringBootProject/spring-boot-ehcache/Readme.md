针对不同的缓存技术，需要实现不同的cacheManager，Spring定义的常用类型有:EhCacheCacheManager,GuavaCacheManager,RedisCacheManager。

### Ehcache
> 是一种纯java的进程内缓存框架，直接在jvm虚拟机中缓存。是hibernate默认的缓存提供者。分布式缓存。
> 存储方式分为内存和磁盘两种。
> Ehcache会自动加载classpath根目录下名为ehcache.xml文件,Spring Boot会自动为我们配置EhCacheCacheManager的Bean。

### memcached
> 是一种基于内存的分布式缓存系统。key-value数据表，数据类型只支持String。服务器之间相互独立，也不共享数据，也不进行通信，客户端需要知道所有的服务器，并自行负责管理数据在各个服务器间的分配。不支持数据持久化，所有数据都是保存在内存中的。所以一旦服务器遇到宕机/重启等突发情况，内存中的数据就消失了。无法进行数据备份。


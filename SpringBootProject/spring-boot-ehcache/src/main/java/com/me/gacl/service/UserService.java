package com.me.gacl.service;

import com.me.gacl.entity.User;
import com.me.gacl.mapper.UserMapper;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.ibatis.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author CH-yfy
 * @date 2019/2/19
 * Ehcache缓存一般放在service层
 */
@Service
public class UserService implements Serializable{

    /**
     * 单引号不能少，否则会被识别成一个对象
     */
    private static final String CACHE_KEY = "'user'";
    private static final String CACHE_NAME = "users";

    private static final long serialVersionUID = -4901391646969052266L;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CacheManager cacheManager;


    public void save(User user) {
        userMapper.addUser(user);
    }

    //检查缓存
    @Cacheable(value = CACHE_NAME, key = "'user_'.concat(#uuid)")
    public User get(String uuid) {
        System.out.println("进入数据库查询，没走缓存:" + uuid);
        return userMapper.getUser(uuid);
    }

    //清除缓存，value指的是ehcache.xml中的缓存策略空间，key是缓存的标识，缓存的内容是该方法的返回值
    @Caching(evict = {@CacheEvict(value = CACHE_NAME, key = "'user_'.concat(#user.uuid)")})
    public void update(User user) throws CacheException {
        System.out.println("cacheNames = " + Arrays.toString(cacheManager.getCacheNames()));
        Cache cache = cacheManager.getCache("users");

        String name = cache.getName();
        System.out.println("name = " + name);

        Element element = cache.get("user_"+user.getUuid());
        Object key = element.getObjectKey();
        Object body = element.getObjectValue();
        System.out.println("key = " + key + ", body = " + body);

        userMapper.updateUser(user);
    }

    //清除缓存
    @CacheEvict(value = CACHE_NAME, key = "'user_'.concat(#uuid)")
    public void delete(String uuid, String name){
        userMapper.deleteUser(name);
    }

}

package com.me.gacl;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author CH-yfy
 * @date 2019/2/21
 */
public class EhcacheApiDemoTest {

    public static void main(String [] args) {
        //创建缓存管理者
        final CacheManager cacheManager = new CacheManager();
        //引用ehcache.xml定义的缓存创建cache对象
        final Cache cache = cacheManager.getCache("users");
        //新建一个缓存的map
        final String key = "user_phone";
        final Element element = new Element(key, "18290901212");
        //将map放入cache缓存中
        cache.put(element);
        System.out.println("保存缓存-------");
        final Element elementGet = cache.get(key);
        System.out.println("获取缓存的内容:" + elementGet.getObjectValue());
    }
}

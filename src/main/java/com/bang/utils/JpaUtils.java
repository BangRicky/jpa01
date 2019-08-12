package com.bang.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author: jinbang
 * @create: 2019/8/12 13:34
 * jpa的工具类，提供了加载factory方法，以及获取实体管理器
 */
public class JpaUtils {

    //创建某实例，关于static的技术点 见：
    // E:\每日整理\Java\Java基础\关键词\static

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}

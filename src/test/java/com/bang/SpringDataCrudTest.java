package com.bang;

import com.bang.jpa.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author: jinbang
 * @create: 2019/8/12 16:35
 */

public class SpringDataCrudTest {

    @Test
    public void testSave(){
        //创建一个实体管理器工厂EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //从工厂中获取实体类管理器 EntityManager
        EntityManager manager = factory.createEntityManager();
        //获取事务并开启
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        //创建实体
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setEmail("12345@qq.com");
        customer.setLastName("tom");
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());

        //调用manager的方法，保存该对象信息
        manager.persist(customer);

        //提交事务
        tx.commit();
        //关闭manager
        manager.close();
        //关闭factory
        factory.close();
    }

}

package com.bang;

import com.bang.jpa.Customer;
import com.bang.utils.JpaUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

/**
 * @author: jinbang
 * @create: 2019/8/13 13:26
 */

public class JpaCrudTest {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Before
    public void init(){
        //JpaUtils是我自己写好的工具类
        entityManager = JpaUtils.getEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    @After
    public void destroy(){
        entityTransaction.commit();
        entityManager.close();
    }

    //类似于hibernate中Session的load方法
    @Test
    public void testGetReference(){
        Customer customer = entityManager.getReference(Customer.class, 1);
        System.out.println("---------------------------------------------");
        System.out.println(customer);
    }

    //类似于hibernate中session的get方法
    @Test
    public void testFind(){
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println("---------------------------------------------");
        System.out.println(customer);
    }

    //类似于hibernate的delete方法，把对象对应的记录从数据库中移除
    //但注意：该方法只能移除持久化对象，而hibernate的delete方法还可以移除游离对象
    @Test
    public void testRemove(){
     //   Customer customer = new Customer();   这是删除游离对象的操作，不适用于jpa，适用于hibernate
     //   customer.setId(2);

        Customer customer = entityManager.find(Customer.class, 1);
        entityManager.remove(customer);

    }

    /**
     * 总的来说，类似于hibernate Session的saveOrUpdate方法
     */
    //1.若传入的是一个临时对象
    @Test
    public void testMerge(){
        Customer customer = new Customer();
        customer.setAge(15);
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());
        customer.setLastName("Mac");
        customer.setEmail("mac@163.com");

        Customer customer2 = entityManager.merge(customer);
        System.out.println("customer#Id:"+customer.getId());
        System.out.println("customer2#Id:"+customer2.getId());

    }

    //1.若传入的是一个游离对象，数据库中不存在该id为100的数据
    @Test
    public void testMerge2(){
        Customer customer = new Customer();
        customer.setAge(15);
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());
        customer.setLastName("Mac");
        customer.setEmail("mac@163.com");

        customer.setId(100);

        Customer customer2 = entityManager.merge(customer);
        System.out.println("customer#Id:"+customer.getId());
        System.out.println("customer2#Id:"+customer2.getId());

    }

    //1.若传入的是一个游离对象，数据库中存在该id为3的数据
    @Test
    public void testMerge3(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());
        customer.setLastName("abc");
        customer.setEmail("abc@163.com");

        customer.setId(3);

        Customer customer2 = entityManager.merge(customer);
        System.out.println("customer#Id:"+customer.getId());
        System.out.println("customer2#Id:"+customer2.getId());

    }

}

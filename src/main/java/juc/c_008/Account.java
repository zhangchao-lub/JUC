package juc.c_008;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/25 14:19
 * @descrption 面试题：模拟银行帐户
 *                     对业务方法加锁
 *                     对业务读方法不加锁
 *                     这样是否可行？
 *
 *                     容易产生脏读问题（dirtyRead）
 *                     解决方法：都加锁
 */
@Slf4j
public class Account {
    String name;
    BigDecimal balance;

    public synchronized void set(String name,BigDecimal balance){
        this.name=name;

        try{
           Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

        this.balance=balance;
    }

    public /*synchronized*/ BigDecimal getBalance(String name){
        return  balance;
    }

    public static void main(String[] args){
        Account a=new Account();
        new Thread(()->a.set("张超",new BigDecimal(8888888))).start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info(String.valueOf(a.getBalance("张超")));

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info(String.valueOf(a.getBalance("张超")));

    }
}

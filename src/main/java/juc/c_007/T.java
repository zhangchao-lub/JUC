package juc.c_007;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/19 16:15
 * @descrption synchronized 关键字
 * 同步方法和非同步方法是否可以同时调用？ 可以
 */
@Slf4j
public class T {
    public synchronized void m1() {
        log.info(Thread.currentThread().getName() + " m1 start..");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + " m1 end..");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + " this is m2");
    }

    public static void main(String[] args) {
        T t=new T();
        new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();
    }
}


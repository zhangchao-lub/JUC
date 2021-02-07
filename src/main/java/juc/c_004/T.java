package juc.c_004;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/19 16:15
 * @descrption synchronized 关键字
 * 给某个对象加锁
 */
@Slf4j
public class T {
    private static int count = 10;

    public synchronized static void m() {//这里等同于synchronized(T.class)
        count--;
        log.info(Thread.currentThread().getName() + " count = " + count);
    }

    public static void n() {
        synchronized (T.class){ //考虑一下这里写synchronized是否可以
            count--;
        }
    }

}

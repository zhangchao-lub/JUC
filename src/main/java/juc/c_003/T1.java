package juc.c_003;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/19 16:15
 * @descrption synchronized 关键字
 * 给某个对象加锁
 */
@Slf4j
public class T1 {
    private static int count = 10;

    public synchronized void run() {
        count--;
        log.info(Thread.currentThread().getName() + " count = " + count);
    }

    public void n() {
        count++;
    }

}

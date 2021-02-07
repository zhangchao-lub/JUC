package juc.c_002;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/19 16:09
 * @descrption synchronized 关键字
 * 给某个对象加锁
 */
@Slf4j
public class T {
    private int count = 10;

    public void m() {
        synchronized (this) { //任何线程要执行下面的代码，必须先拿到 this 的锁
            count--;
            log.info(Thread.currentThread().getName() + "count =" + count);
        }
    }
}

package juc.c_010;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/25 15:06
 * @descrption 一个同步方法可以调用另外一个同步方法，
 * 一个线程已经拥有了某个对象的锁，
 * 再次申请的时候仍然会得到该对象的锁，
 * 也就是说synchronized 获得的锁是可重入的
 * <p>
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 */
@Slf4j
public class T {
    synchronized void m() {
        log.info("parent m start");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("parent m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

@Slf4j
class TT extends T {
    @Override
    synchronized void m() {
        log.info("child m start");
        super.m();
        log.info("child m end");
    }
}
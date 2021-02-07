package juc.c_019;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/28 11:45
 * @descrption   reentranlock用于替代synchronized
 *               本例子中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 *               这里是复习synchronized最原始的语义
 *
 *               使用reentrantlock可以完成同样的功能
 *               需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 *               使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 */
@Slf4j
public class T02_ReentranLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                log.info(String.valueOf(i));
                if (i == 2) m2();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            log.info("m2...");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_ReentranLock2 rl = new T02_ReentranLock2();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }
}

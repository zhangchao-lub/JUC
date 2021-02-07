package juc.c_019;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/28 11:45
 * @descrption reentranlock用于替代synchronized
 * 本例子中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 * <p>
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必需要必需要必须要手动释放锁（重要的事情说三遍）
 * 使用sync锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放
 * <p>
 * 使用reetrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
@Slf4j
public class T03_ReentranLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                TimeUnit.SECONDS.sleep(1);
                log.info(String.valueOf(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unclock的处理，必须放到finally中
     */

    void m2() {
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            log.info("m2..." + locked);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(locked)lock.unlock();
        }
    }

    public static void main(String[] args) {
        T03_ReentranLock3 rl = new T03_ReentranLock3();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }
}

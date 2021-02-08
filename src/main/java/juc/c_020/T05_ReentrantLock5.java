package juc.c_020;

import lombok.extern.slf4j.Slf4j;

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
 * <p>
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，
 * 在一个线程等待锁的过程中，可以被打断
 * <p>
 * ReentrantLock还可以指定为公平锁
 */
@Slf4j
public class T05_ReentrantLock5 extends Thread{

    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示为公平锁，请对比输出结果

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                log.info(Thread.currentThread().getName() + "获得锁");
            }catch (Exception e ){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 rl=new T05_ReentrantLock5();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);

        th1.start();
        th2.start();

    }
}

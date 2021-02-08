package juc.c_020;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/28 10:53
 * @descrption reentranlock用于替代synchronized
 *             本例子中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 *             这里是复习synchronized最原始的语义
 *
 *             同一个线程 sync（this）同一把锁 锁可以重入
 */
@Slf4j
public class T01_ReentrantLock1 {

    synchronized void m1(){
        for(int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.info(String.valueOf(i));
            if (i==2)m2();
        }
    }
    synchronized void m2(){
      log.info("m2...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 rl=new T01_ReentrantLock1();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(rl::m2).start();
    }
}

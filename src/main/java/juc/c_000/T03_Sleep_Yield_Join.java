package juc.c_000;

import lombok.extern.slf4j.Slf4j;


/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/18 15:41
 * @descrption 线程暂停形式
 */
@Slf4j
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        //睡眠：当前线程暂停运行，让给其他线程运行
//        testSleep();
        //释放一下CPU，重新竞争资源
//        testYield();
        //用来衔接线程的运行顺序
        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("B" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    static void testJoin() {
        Thread t1=new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("A" + i);
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            for (int i = 0; i < 10; i++) {
                log.info("B" + i);
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t2.start();
        t1.start();
    }
}

//请你告诉我启动线程的三种方式 1，Thread 2，Runnable 3，Executors.newCachedThread
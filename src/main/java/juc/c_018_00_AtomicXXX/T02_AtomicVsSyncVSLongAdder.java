package juc.c_018_00_AtomicXXX;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author czhang@mindpointeye.com





 * @Date 2021/1/27 9:40
 * @descrption 比较
 */
@Slf4j
public class T02_AtomicVsSyncVSLongAdder {
    /*volatile*/ int count = 0; //并不能保证原子性
    static AtomicLong count1 = new AtomicLong(0L);
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();//分段锁

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int j = 0; j < 100000; j++) {
                            //count++
                            count1.incrementAndGet();
                        }
                    });
        }

        long startTime = System.currentTimeMillis();

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        long endTime = System.currentTimeMillis();

        log.info("Atomic: " + count1+ " time " + (endTime - startTime));

        Object luck = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int j = 0; j < 100000; j++) {
                            //count++
                            synchronized (luck) {
                                count2++;
                            }
                        }
                    });
        }

        startTime=System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        endTime = System.currentTimeMillis();

        log.info("Sync: " + count2+ " time " + (endTime - startTime));

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int j = 0; j < 100000; j++) {
                            count3.increment();
                        }
                    });
        }

        startTime=System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        endTime = System.currentTimeMillis();

        log.info("LongAdder: " + count3+ " time " + (endTime - startTime));

    }
}

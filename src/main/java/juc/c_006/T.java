package juc.c_006;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/19 16:15
 * @descrption synchronized 关键字
 * 分析: 加sync 保证数据一致性
 */
@Slf4j
public class T implements Runnable{
    private /*volatile*/ int count = 10;

    @Override
    public synchronized void run() {
        count--;
        log.info(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}


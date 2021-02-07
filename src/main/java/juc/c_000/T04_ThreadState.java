package juc.c_000;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/19 14:43
 * @descrption
 */
@Slf4j
public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            for(int i=0;i<100;i++){
                log.info("Thread"+i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t=new MyThread();

        log.info(String.valueOf(t.getState()));

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(t.getState()));
    }
}

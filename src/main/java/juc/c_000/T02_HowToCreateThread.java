package juc.c_000;

import lombok.extern.slf4j.Slf4j;


/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/18 15:41
 * @descrption 如何创建线程。两三种方式
 */
@Slf4j
public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            log.info("Thread");
        }
    }

    static class MyRun implements Runnable {

        @Override
        public void run() {
            log.info("Runnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        //lambda表达式方法
        new Thread(() -> {
            log.info("Hello lambada");
        }).start();
    }
}

//请你告诉我启动线程的三种方式 1，Thread 2，Runnable 3，Executors.newCachedThread
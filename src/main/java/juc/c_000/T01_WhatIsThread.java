package juc.c_000;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2020/12/18 15:41
 * @descrption 什么是线程，进程与线程的区别？
 */
@Slf4j
public class T01_WhatIsThread {
    private static class T1 extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("T1");
            }
        }
    }

    public static void main(String[] args) {
        //先执行run() 再执行main
//        new T1().run();
        //使用不同的执行路径
        new T1().start();
        for(int i=0;i<10;i++){
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("main");
        }
    }
}

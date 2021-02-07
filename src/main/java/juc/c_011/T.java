package juc.c_011;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/25 15:31
 * @descrption 程序在执行过程中，如果出现异常，默认情况锁会被释放，
 *             所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一样的情况。
 *             比如：在一个线程web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 *             在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据，
 *             因此要非常小心的处理同步业务逻辑中的异常
 */

@Slf4j
public class T {
    int count=0;
    synchronized void m(){
      log.info(Thread.currentThread().getName()+" start");
      while (true){
          count++;
          log.info(Thread.currentThread().getName()+" count ="+count);
          try {
              TimeUnit.SECONDS.sleep(1);
          }catch (Exception e){
              e.printStackTrace();
          }

          if(count==5){
//             try {
                  int i=1/0;//此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                  log.error(String.valueOf(i));
//              }catch (Exception e){
//                  e.printStackTrace();
//              }
          }
      }
    }

    public static void main(String[] args) {
        T t=new T();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(r,"t2").start();
    }
}

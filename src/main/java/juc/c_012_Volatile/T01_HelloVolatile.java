package juc.c_012_Volatile;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/26 13:37
 * @descrption volatile 关键字，使一个变量在多个线程间可见
 *
 *             作用：
 *                  1，保证线程可见性
 *                     - MESI
 *                     - 缓存一致性协议
 *                  2，禁止指令重排序
 *                     - DCL单例
 *                     - Double Check Lock
 *                     - loadFence 读屏障
 *                     - storeFence 写屏障
 *
 *
 *             A B 线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 *             使用volatile 关键之，会让所有线程都会读到变量的修改值
 *
 *             在下面的代码中，running是存在于堆内存的t对象中，
 *             当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在工作中直接使用这个copy
 *             并不会每次都去读取内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 *
 *             使用volatile，将会强制所有线程都去堆内存中读取running的值
 *
 *             volatile并不能保证多个线程共同修改running变量所带来的不一致问题，也就是说volatile不能代替synchronized
 */
@Slf4j
public class T01_HelloVolatile {

    /*volatile*/ boolean running=true;//对比一下有无volatile的运行区别

    void m(){
        log.info("m start...");
        while(running){
//            try{
//                TimeUnit.MICROSECONDS.sleep(10);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        }
        log.info("m end...");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t=new T01_HelloVolatile();

        //lambda 表达式 new Thread(new Runnable( run() {m{}}))
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        t.running=false;
    }
}

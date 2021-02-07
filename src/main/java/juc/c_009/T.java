package juc.c_009;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/25 15:06
 * @descrption 一个同步方法可以调用另外一个同步方法，
 *             一个线程已经拥有了某个对象的锁，
 *             再次申请的时候仍然会得到该对象的锁，
 *             也就是说synchronized 获得的锁是可重入的
 */
@Slf4j
public class T {
    synchronized void m1(){
      log.info("m1 start");
      try{
          TimeUnit.SECONDS.sleep(1);
      }catch (Exception e){
          e.printStackTrace();
      }
      m2();
      log.info("m1 end");
    }

    synchronized void m2(){
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("m2");
    }

    public static void main(String[] args) {
        new T().m1();
    }
}

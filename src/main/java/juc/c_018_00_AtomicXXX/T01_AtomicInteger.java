package juc.c_018_00_AtomicXXX;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/27 9:40
 * @descrption 解决同样的问题的更高效的方法，使用AtomXXX类
 *             AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */
@Slf4j
public class T01_AtomicInteger {
//    /*volatile*/ int count=0; //并不能保证原子性
    AtomicInteger count=new AtomicInteger(0);

    /*synchronized*/ void m(){ //加上sync可以保证原子性
        for(int i=0;i<10000;i++){
            //count++
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t=new T01_AtomicInteger();

        List<Thread> threads=new ArrayList<>();

        for(int i=0;i<10;i++){
            threads.add(new Thread(t::m,"thread-"+i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try{
                o.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        log.info(String.valueOf(t.count));
    }
}

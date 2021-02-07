package juc.c_013;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/26 15:21
 * @descrption volatile并不能保证多个线程共同修改running变量所带来的不一致问题，也就是说volatile不能代替synchronized
 *             运行下面的程序，并分析结果
 */
@Slf4j
public class T04_VolatileNotSync {
    volatile int count=0; //并不能保证原子性
    void m(){
        for(int i=0;i<10000;i++){
            count++;
        }
    }

    public static void main(String[] args) {
        T04_VolatileNotSync t=new T04_VolatileNotSync();

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

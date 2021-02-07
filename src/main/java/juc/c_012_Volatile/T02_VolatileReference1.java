package juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/2/7 16:03
 * @descrption volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class T02_VolatileReference1 {
    boolean running = true;

    volatile static T02_VolatileReference1 T = new T02_VolatileReference1();


    void m() {
        System.out.println("m start");
        while(running) {
			/*
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        new Thread(T::m, "t1").start();

        //lambda表达式 new Thread(new Runnable( run() {m()}

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T.running = false;
    }

}

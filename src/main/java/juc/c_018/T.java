package juc.c_018;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/1/26 16:53
 * @descrption 在下面的列子中：m1 m2 其实锁定的是同一个对象
 *             发生诡异的死锁现象
 *             因为你的程序和你用到的类库不经意间使用了统一把锁
 *
 *             jetty
 */
public class T {

    String s1="Hello";
    String s2="Hello";

    void m1(){
        synchronized (s1){

        }
    }

    void m2(){
        synchronized (s2){

        }
    }
}

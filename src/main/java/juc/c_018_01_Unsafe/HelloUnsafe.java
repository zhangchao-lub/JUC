package juc.c_018_01_Unsafe;

import sun.misc.Unsafe;

/**
 * @author czhang@mindpointeye.com
 * @version 1.0
 * @Date 2021/2/7 16:27
 * @descrption
 */
public class HelloUnsafe {
    static class M {
        private M() {}

        int i =0;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }

}

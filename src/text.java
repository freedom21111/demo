import sun.nio.cs.ext.MacRoman;
import sun.util.resources.cldr.kk.CurrencyNames_kk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class text {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        A a = new A();
        Method sum = a.getClass().getMethod("sum", int.class, int.class);
        Object o= sum.invoke(a,1,2);
        System.out.println(o);
    }
}
class A{
    public int sum(int a1,int a2){
        return a1+a2;
    }
}




import sun.nio.cs.ext.MacRoman;
import sun.util.resources.cldr.kk.CurrencyNames_kk;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SellTickets {//万人买一张票
    //拿到火车票的线程名
    public static String getTicketThread;
    //拿到火车票的线程数
    public static volatile AtomicInteger getTicketThreadNum = new AtomicInteger(0);
    //火车票数
    public static volatile int ticketNum = 1;

    public static int poolSize = 10000;
    //CyclicBarrier模拟一万人同时抢票的场景
    public static CyclicBarrier cb = new CyclicBarrier(poolSize);
    //有多少张火车票，就允许多少个线程并发运行
    //public static final Semaphore s = new Semaphore(ticketNum);
    static class Ticket implements Runnable {
        public void run() {
            try {
                Thread.sleep((long)(4000* Math.random()));
                System.out.println(Thread.currentThread().getName()+"-------------------------begin");
                cb.await();
                //s.acquire();
                if(ticketNum <= 0) {
                    return ;
                }
                ticketNum--;
                getTicketThread = Thread.currentThread().getName();
                getTicketThreadNum.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " get ticket................................................");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //s.release();
                System.out.println(Thread.currentThread().getName()+"-------------------------end");
            }
        }


    }
    public static void main(String[] args){
        ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
        for(int i = 0; i < poolSize; i++) {
            threadPool.execute(new Ticket());
        }
        threadPool.shutdown();
        while(!threadPool.isTerminated()) {//没有执行完10000个任务，则循环
        }
        try {
            Thread.sleep(1000);
            System.out.println("getTicketThread:" + getTicketThread + ", getTicketNum:" + getTicketThreadNum.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}






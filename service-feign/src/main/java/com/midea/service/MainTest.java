package com.midea.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;


public class MainTest {
    private static int a=0;

    private static Monitor  m=new Monitor();
    private static  int count=5;
    public static void main(String[] args) {
        int corePoolSize=5;
        int maxPoolSize=20;
        int keepAliveTime=1;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        RejectedExecutionHandler handler =
                new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor
                (corePoolSize, maxPoolSize,
                        keepAliveTime, TimeUnit.SECONDS,
                        queue, handler);

        for (int i=0;i<6;i++){
            int finalI = i;
            poolExecutor.execute(new Runnable() {
                @Override
                public   void  run() {
                    synchronized (m){
                        count--;
                        System.out.println(Thread.currentThread().getName()+"==="+count);
                    }
                 /*   count=count-1;
                    System.out.println(Thread.currentThread().getName()+"==="+count);*/
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        poolExecutor.shutdown();
    }

    static  class Monitor{

    }
}

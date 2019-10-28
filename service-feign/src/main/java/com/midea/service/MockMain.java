package com.midea.service;

import java.util.concurrent.*;

public class MockMain {
    private  static ThreadPoolExecutor poolExecutor;

    private static int trainCount=100;
    static {
        int corePoolSize=5;
        int maxPoolSize=20;
        int keepAliveTime=1;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(100);
        RejectedExecutionHandler handler =
                new ThreadPoolExecutor.CallerRunsPolicy();
        poolExecutor = new ThreadPoolExecutor
                (corePoolSize, maxPoolSize,
                        keepAliveTime, TimeUnit.SECONDS,
                        queue, handler);
    }
    private synchronized static void sale(){
        if(trainCount>0){
            --trainCount;
            System.out.println(Thread.currentThread().getName() + ",出售第" + (100 - trainCount));
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (;;){
                        if(trainCount>0){
                            sale();
                        }else {
                            break;
                        }
                    }
                }
            });
        }
        poolExecutor.shutdown();
    }
}

package com.example.util;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池封装
 *
 * @author zhaolh
 *         created at 2018/2/26 10:21
 */
public class ThreadPoolManager {
    private static ThreadPoolManager mInstance;

    public static ThreadPoolManager getInstance() {
        if (mInstance == null) {
            synchronized (ThreadPoolManager.class) {
                if (mInstance == null) {
                    mInstance = new ThreadPoolManager();
                }
            }
        }
        return mInstance;
    }


    /**
     * 核心线程池的数量，同时能够执行的线程数量
     */
    private int corePoolSize;
    /**
     * 最大线程池数量，表示当缓冲队列满的时候能继续容纳的等待任务的数量
     */
    private int maximumPoolSize;
    /**
     * 存活时间
     */
    private long keepAliveTime = 1;
    @SuppressLint("NewApi")
    private TimeUnit unit = TimeUnit.HOURS;
    private ThreadPoolExecutor executor;

    private ThreadPoolManager() {
        /**
         * 给corePoolSize赋值：当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行（有研究论证的）
         */
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        //虽然maximumPoolSize用不到，但是需要赋值，否则报错
        maximumPoolSize = corePoolSize;
        executor = new ThreadPoolExecutor(
                //当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务
                corePoolSize,
                //5,先corePoolSize,然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的
                maximumPoolSize,
                //表示的是maximumPoolSize当中等待任务的存活时间
                keepAliveTime,
                unit,
                //缓冲队列，用于存放等待任务，Linked的先进先出
                new LinkedBlockingQueue<Runnable>(),
                //创建线程的工厂
                //  Executors.defaultThreadFactory(),
                new DefaultThreadFactory(Thread.NORM_PRIORITY, "cashier-pool-"),
                //用来对超出maximumPoolSize的任务的处理策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
    /**
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if (executor == null) {
            //线程池执行者。
            //参1:核心线程数;参2:最大线程数;参3:线程休眠时间;参4:时间单位;参5:线程队列;参6:生产线程的工厂;参7:线程异常处理策略
            executor = new ThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(),
                    //   Executors.defaultThreadFactory(),
                    new DefaultThreadFactory(Thread.NORM_PRIORITY, "cashier-pool-"),
                    new ThreadPoolExecutor.AbortPolicy());
        }
        if (runnable != null) {
            executor.execute(runnable);
        }
    }

    /**
     * 移除任务
     */
    public void remove(Runnable runnable) {
        if (runnable != null) {
            executor.remove(runnable);
        }
    }

    /**
     * 立即中止线程池
     */
    public void shutdownNow() {
        executor.shutdownNow();
    }
    /**
     * 创建线程的工厂，设置线程的优先级，group，以及命名
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        /**
         * 线程池的计数
         */
        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        /**
         * 线程的计数
         */
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final ThreadGroup group;
        private final String namePrefix;
        private final int threadPriority;

        DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.threadPriority = threadPriority;
            this.group = Thread.currentThread().getThreadGroup();
            namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            t.setPriority(threadPriority);
            return t;
        }
    }


    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; i++) {
                stringBuilder.append("runnable i = ").append(i).append("\r\n");
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("respContent",stringBuilder.toString());
            msg.setData(data);
//            handler.sendMessage(msg);
        }
    };

    static Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; i++) {
                stringBuilder.append("runnable1 i = ").append(i).append("\r\n");
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("respContent",stringBuilder.toString());
            msg.setData(data);
//            handler.sendMessage(msg);
        }
    };

    static Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; i++) {
                stringBuilder.append("runnable2 i = ").append(i).append("\r\n");
            }
            System.out.println(stringBuilder.toString());
            /*Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("respContent",stringBuilder.toString());
            msg.setData(data);
            handler.sendMessage(msg);*/
        }
    };

//    static Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            System.out.println("msg = [" + msg + "]");
//        }
//    };

    public static void main(String[] args) {
//        ThreadPoolManager.getInstance().execute(runnable);
//        System.out.println("================================");
//        ThreadPoolManager.getInstance().execute(runnable1);
//        System.out.println("================================");
        ThreadPoolManager.getInstance().execute(runnable2);
        System.out.println("================================");
    }
}
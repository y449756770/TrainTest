package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.ProductAndConsume;
import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.bean.TestWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 互斥锁test
 * <p>
 * synchronied关键字等待/通知机制：是指一个线程A调用了对象O的wait()方法进入等待状态，
 * 而另一个线程B调用了对象O的notify()或者notifyAll()方法，线程A收到通知后从对象O的wait()方法返回，进而执行后续操作。上述的两个线程通过对象O来完成交互，
 * 而对象上的wait()和notify()/notifyAll()的关系就如同开关信号一样，用来完成等待方和通知方之间的交互工作。
 */

public class LockActivity extends BaseActivity implements View.OnClickListener {
    Printer printer = new Printer();
    Button reentrantLock;
    Button sync;
    Button wait;
    Button consumer;
    Button callable;
    private String flag[] = {"true"};
    Button porduce_consumer;
    Button count_down_latch;
    Button semaphore;
    Button deadLock;


    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static int count = 2; // 线程个数
    //CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
    private static CountDownLatch latch = new CountDownLatch(count);

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };

    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        testMap();

        reentrantLock = (Button) findViewById(R.id.reentrantLock);
        reentrantLock.setOnClickListener(this);
        /**
         * sync同步代码块
         */
        sync = (Button) findViewById(R.id.sync);
        sync.setOnClickListener(this);
        /**
         * wait
         */
        wait = (Button) findViewById(R.id.wait);
        wait.setOnClickListener(this);
        /**
         * 消费者模式
         */
        consumer = (Button) findViewById(R.id.consumer);
        consumer.setOnClickListener(this);
        /**
         * callable
         */
        callable = (Button) findViewById(R.id.callable);
        callable.setOnClickListener(this);

        /**
         * 生产者－消费者
         */
        porduce_consumer = (Button) findViewById(R.id.porduce_consumer);
        porduce_consumer.setOnClickListener(this);

        /**
         * CountDownLatch闭锁
         */
        count_down_latch = (Button) findViewById(R.id.count_down_latch);
        count_down_latch.setOnClickListener(this);


        /**
         * Semaphore互斥
         */
        semaphore = (Button) findViewById(R.id.semaphore);
        semaphore.setOnClickListener(this);

        /**
         * 死锁
         */

        deadLock = (Button) findViewById(R.id.dead_lock);
        deadLock.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reentrantLock:
                implLock1();

                break;
            case R.id.sync:
                Runnable r = new ThreadTest();
                Thread t1 = new Thread(r);
                Thread t2 = new Thread(r);
                t1.start();
                t2.start();


                break;
            case R.id.wait:
//                NotifyThread notifyThread = new NotifyThread("notify01");
//                WaitThread waitThread01 = new WaitThread("waiter01");
//                WaitThread waitThread02 = new WaitThread("waiter02");
//                WaitThread waitThread03 = new WaitThread("waiter03");
//                notifyThread.start();
//                waitThread01.start();
//                waitThread02.start();
//                waitThread03.start();

                testWait();

                break;
            case R.id.consumer: //消费者模式


                // 建立一个装苹果的篮子
                Basket basket = new Basket();

                ExecutorService service = Executors.newCachedThreadPool();
                Producer producer = new Producer("生产者001", basket);
                Producer producer2 = new Producer("生产者002", basket);
                Producer producer3 = new Producer("生产者003", basket);
                Producer producer4 = new Producer("生产者004", basket);
                Producer producer5 = new Producer("生产者005", basket);
                Producer producer6 = new Producer("生产者006", basket);

                Consumer consumer = new Consumer("消费者001", basket);
                service.submit(producer);
                service.submit(producer2);
                service.submit(producer3);
                service.submit(producer4);
                service.submit(producer5);
                service.submit(producer6);

                service.submit(consumer);
                // 程序运行5s后，所有任务停止
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                service.shutdownNow();


                break;
            case R.id.callable:
                implCallable();

                break;
/**
 * ConcurrentLinkedQueue是Queue的一个安全实现．Queue中元素按FIFO原则进行排序．
 * 采用CAS操作，来保证元素的一致性。
 */
            case R.id.porduce_consumer: //消费者模式
//                testProducerConsumer();

                implLock2();

                break;

            case R.id.count_down_latch:
                try {
                    testCountDownLatch();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.semaphore:

                testSemaPhore();

                break;

            case R.id.dead_lock:   //死锁
                while (true) {
                    testDeadLock();

                }


        }

    }

    class Printer {
        private int flag = 1;
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        public void printe1() {
            lock.lock();
            if (flag != 1) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Log.i("print*****", "" + flag);
            flag = 2;
            c2.signal();
            lock.unlock();
        }

        public void printe2() {
            lock.lock();
//            if (flag != 2) {
            try {
                c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            }

            Log.i("print*****", "" + flag);
            flag = 3;
            c3.signal();
            lock.unlock();
        }

        public void printe3() {
            lock.lock();
//            if (flag != 3) {
            try {
                c3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            }

            Log.i("print*****", "" + flag);
            flag = 1;
            c1.signal();
            lock.unlock();
        }


    }

    class ThreadTest implements Runnable {

        public synchronized void run() {
            for (int i = 0; i < 10; i++) {
                Log.i("test", " " + i);
            }
        }
        //也可以这么写

//        public  void run() {
//            synchronized (this){
//                for (int i = 0; i < 10; i++) {
//                    Log.i("test", " " + i);
//                }
//
//            }
//
//        }

    }

    class NotifyThread extends Thread {
        public NotifyThread(String name) {
            super(name);
        }

        public void run() {
            try {
                sleep(3000);// 推迟3秒钟通知
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (flag) {
                flag[0] = "false";
                flag.notify();
            }
        }
    }


    class WaitThread extends Thread {
        public WaitThread(String name) {
            super(name);
        }

        public void run() {
            synchronized (flag) {
                System.out.println(getName() + "  flag:" + flag);
                while (!flag[0].equals("false")) {
                    System.out.println(getName() + " begin waiting!");
                    long waitTime = System.currentTimeMillis();
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitTime = System.currentTimeMillis() - waitTime;
                    System.out.println("wait time :" + waitTime);
                }
                System.out.println(getName() + " end waiting!");
            }

        }
    }

    /**
     * 定义装苹果的篮子
     */
    public class Basket {
        // 篮子，能够容纳3个苹果
        BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);

        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            Thread.sleep(3000);
            basket.put("An apple");

            System.out.println("生产好了");

        }

        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // take方法取出一个苹果，若basket为空，等到basket有苹果为止(获取并移除此队列的头部)
            return basket.take();
        }
    }

    // 定义苹果生产者
    class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 生产苹果
                    System.out.println("++++生产者准备生产苹果：" + instance);
                    basket.produce();
                    System.out.println("++++!生产者生产苹果完毕：" + instance);
                    // 休眠300ms
                    Thread.sleep(300);
                }
            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }

    // 定义苹果消费者
    class Consumer implements Runnable {
        private String instance;
        private Basket basket;

        public Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 消费苹果
                    System.out.println("----消费者准备消费苹果：" + instance);
                    System.out.println(basket.consume());
                    System.out.println("----!消费者消费苹果完毕：" + instance);
                    // 休眠1000ms
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                System.out.println("Consumer Interrupted");
            }
        }
    }

    //带有返回值的
    class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            System.out.println(id + "begin to exec");
            if (id % 2 == 0) {
                Thread.sleep(2000);
                return "result of TaskWithResult＊＊＊＊＊＊＊＊＊＊";

            } else {
                Thread.sleep(2000);
                return "result of TaskWithResult&&&&&&&&&&&&&&&&&";
            }

        }
    }

    public void implCallable() {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<Future<String>>();
//        for (int i = 0; i < 10; i++) {
//            results.add(exec.submit(new TaskWithResult(i)));
//            try {
//                System.out.println(exec.submit(new TaskWithResult(i)).get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        FutureTask futureTask1 = new FutureTask(new TaskWithResult(0));
        Future<?> future = exec.submit(new TaskWithResult(0));
        try {
            Object obj = future.get();
            System.out.println(obj.toString());

            exec.submit(new TaskWithResult(1));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    /**
     * 测试HashMap
     */

    public void testMap() {
        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("1", 1);
        myHashMap.put("2", null);
        myHashMap.put("3", 3);
        myHashMap.put("4", 4);
        myHashMap.put("5", 5);
        Iterator<Map.Entry<String, Integer>> iter = myHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> next = iter.next();
            if (null == next.getValue()) {
                String str = next.getKey();
                iter.remove();  //Iterator删除

            }

        }
        myHashMap.size();
        for (Map.Entry<String, Integer> item : myHashMap.entrySet()) {
            System.out.println(item.getKey());
        }


    }

    final BlockingQueue<Runnable> mTasks = new LinkedBlockingQueue<Runnable>();
    Runnable mActive;

    public void testProducerConsumer() {
//        long timeStart = System.currentTimeMillis();
//        ExecutorService es = Executors.newFixedThreadPool(4);
//        offer();
//        for (int i = 0; i < count; i++) {
//            es.submit(new Poll());
//        }
//        try {
//            latch.await(); //使得主线程(main)阻塞直到latch.countDown()为零才继续执行
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("cost time " + (System.currentTimeMillis() - timeStart) + "ms");
//        es.shutdown();
        SimuRunnable runnable1 = new SimuRunnable("第一个");
        SimuRunnable runnable2 = new SimuRunnable("第二个");
        SimuRunnable runnable3 = new SimuRunnable("第三个");
        SimuRunnable runnable4 = new SimuRunnable("第四个");
        SimuRunnable runnable5 = new SimuRunnable("第五个");
        SimuRunnable runnable6 = new SimuRunnable("第六个");
        SimuRunnable runnable7 = new SimuRunnable("第七个");

        execute(runnable1);
        execute(runnable2);
        execute(runnable3);
        execute(runnable4);
        execute(runnable5);
        execute(runnable6);
        execute(runnable7);

//        THREAD_POOL_EXECUTOR.execute(runnable1);
//        THREAD_POOL_EXECUTOR.execute(runnable2);
//        THREAD_POOL_EXECUTOR.execute(runnable3);
//        THREAD_POOL_EXECUTOR.execute(runnable4);
//        THREAD_POOL_EXECUTOR.execute(runnable5);
//        THREAD_POOL_EXECUTOR.execute(runnable6);
//        THREAD_POOL_EXECUTOR.execute(runnable7);


    }

    protected synchronized void scheduleNext() {
        if ((mActive = mTasks.poll()) != null) {
            THREAD_POOL_EXECUTOR.execute(mActive);
        }
    }

    public synchronized void execute(final Runnable r) {
        mTasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (mActive == null) {
            scheduleNext();
        }
    }

    /**
     * 生产
     */
    public static void offer() {
        for (int i = 0; i < 100000; i++) {
            queue.offer(i);
        }
    }


    /**
     * 消费
     *
     * @author 林计钦
     * @version 1.0 2013-7-25 下午05:32:56
     */
    static class Poll implements Runnable {
        public void run() {
            // while (queue.size()>0) {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            latch.countDown();
        }
    }

    // 定义苹果生产者
    class SimuRunnable implements Runnable {
        private String instance;

        public SimuRunnable(String instance) {
            this.instance = instance;
        }

        public void run() {
            try {

                // 生产苹果
                System.out.println("第几个任务在执行：" + instance);
                // 休眠300ms
                Thread.sleep(300);

            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }

    /**
     * 用CountDownLatch闭锁实现三个异步任务全部完成才执行后续的操作
     *
     * @throws InterruptedException
     */

    private void testCountDownLatch() throws InterruptedException {

        final CountDownLatch mCount = new CountDownLatch(3);
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i("CountDownLatch", "第一个任务完成");
                mCount.countDown();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("CountDownLatch", "第二个任务完成");
                mCount.countDown();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("CountDownLatch", "第三个任务完成");
                mCount.countDown();

            }
        }).start();

        mCount.await();

        Log.i("CountDownLatch", "任务全部完成");


    }

    /**
     * SemaPhore实现简易连接池
     * <p>
     * Semaphore中管理着一组虚拟的许可，许可的初始数量可通过构造函数来指定【new Semaphore(1);】，
     * 执行操作时可以首先获得许可【semaphore.acquire();】，
     * 并在使用后释放许可【semaphore.release();】。
     * 如果没有许可，那么acquire方法将会一直阻塞直到有许可（或者直到被终端或者操作超时）
     */

    private void testSemaPhore() {
        final Semaphore mSemaphore = new Semaphore(3);
        final List<Conn> mConns = new ArrayList<>(3);

        new Thread(new Runnable() {
            @Override
            public void run() {

                /**
                 * 拿到连接
                 */
                try {
                    mSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Conn c = null;
                synchronized (mConns) {
                    c = mConns.remove(0);
                }
                System.out.println(Thread.currentThread().getName() + " get a conn " + c);


                /**
                 * 模拟持有连接3秒
                 */
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * 释放连接
                 */

                mConns.add(c);
                System.out.println(Thread.currentThread().getName() + " release a conn " + c);
                mSemaphore.release();


            }
        }).start();


        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 拿到连接
                     */
                    try {
                        mSemaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (mConns) {
                        mConns.remove(0);
                    }
                    System.out.println(Thread.currentThread().getName() + " get a conn ");


                }
            }).start();


        }


    }

    /**
     * 用于模拟连接
     */
    class Conn {

    }

    public void implLock1() {

        ReentrantLock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printe1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printe2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printe3();
            }
        }).start();

    }

    public void implLock2() {
        ProductAndConsume obj = new ProductAndConsume();
        Product product = new Product(obj);
        Consume consume = new Consume(obj);
        new Thread(product).start();
        new Thread(consume).start();


    }

    class Product implements Runnable {
        ProductAndConsume obj;

        public Product(ProductAndConsume obj) {
            this.obj = obj;
        }

        @Override
        public void run() {

            try {
                for (int i = 0; i < 10; i++) {
                    obj.produce();

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    class Consume implements Runnable {
        ProductAndConsume obj;

        public Consume(ProductAndConsume obj) {
            this.obj = obj;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    obj.consume();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 同步嵌套是产生死锁的常见情景，从上面的代码中我们可以看出，
     * 当t1线程拿到锁A后，睡眠2秒，此时线程t2刚好拿到了B锁，接着要获取A锁，但是此时A锁正好被t1线程持有，因此只能等待t1线程释放锁A，
     * 但遗憾的是在t1线程内又要求获取到B锁，而B锁此时又被t2线程持有，到此结果就是t1线程拿到了锁A同时在等待t2线程释放锁B，而t2线程获取到了锁B也同时在等待t1线程释放锁A，彼此等待也就造成了线程死锁问题。虽然我们现实中一般不会向上面那么写出那样的代码，但是有些更为复杂的场景中，我们可能会遇到这样的问题，比如t1拿了锁之后，因为一些异常情况没有释放锁（死循环），也可能t1拿到一个数据库锁，释放锁的时候抛出了异常，没有释放等等，所以我们应该在写代码的时候多考虑死锁的情况，这样才能有效预防死锁程序的出现。下面我们介绍一下避免死锁的几个常见方法：
     * 1.避免一个线程同时获取多个锁。
     * 2.避免在一个资源内占用多个 资源，尽量保证每个锁只占用一个资源。
     * 3.尝试使用定时锁，使用tryLock(timeout)来代替使用内部锁机制。
     * 4.对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
     */


    public void testDeadLock() {
        final String A = "A";
        final String B = "B";
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                synchronized (B) {
                    Log.i("B", "B");
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        Log.i("A", "A");
                    }
                }

            }
        }).start();
    }


    /**
     * 测试
     * while（true）｛
     * this.wait();
     * ｝
     * 会一直等在wait这句，还是会不断的循环
     */

    public void testWait() {
        TestWait mTestWait = new TestWait();
        TestRunnable mTestRunnable = new TestRunnable(mTestWait);
        new Thread(mTestRunnable).start();
    }

    class TestRunnable implements Runnable {
        TestWait mTestWait;

        public TestRunnable(TestWait mTestWait) {
            this.mTestWait = mTestWait;
        }

        @Override
        public void run() {
            while (true) {
                mTestWait.goWait();
            }

        }
    }


}



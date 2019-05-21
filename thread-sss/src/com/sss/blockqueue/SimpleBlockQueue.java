package com.sss.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/5/21.
 */
public class SimpleBlockQueue {
    private List queue = new ArrayList();
    private ReentrantLock lock = new ReentrantLock();
    private Condition putCondition = lock.newCondition();
    private Condition takeCondition = lock.newCondition();
    private static final int QUEUE_SIZE = 10;

    public void put(Object o) {
        lock.lock();
        try {
            while (queue.size() == QUEUE_SIZE) {
                System.out.println("当前队列已满，"+Thread.currentThread().getName()+"等待put");
                putCondition.await();
            }
            queue.add(o);
            System.out.println(Thread.currentThread().getName()+"put");
            takeCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        Object o = null;
        try {
            while (queue.size() == 0) {
                System.out.println("当前队列已空，"+Thread.currentThread().getName()+"等待take");
                takeCondition.await();
            }
            o = queue.remove(0);
            System.out.println(Thread.currentThread().getName()+"take");
            putCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return o;
    }
}

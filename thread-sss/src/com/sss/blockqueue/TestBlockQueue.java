package com.sss.blockqueue;

/**
 * Created by Administrator on 2019/5/21.
 */
public class TestBlockQueue {
    private static SimpleBlockQueue queue = new SimpleBlockQueue();

    static class PutThread implements Runnable {

        @Override
        public void run() {
            queue.put(this.toString());
        }
    }

    static class  TakeThread implements Runnable {
        @Override
        public void run() {
            queue.take();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<20;i++){
            new Thread(new PutThread(), "PutThread-" + i).start();
        }
        for(int i=0;i<20;i++){
            new Thread(new TakeThread(), "TakeThread-" + i).start();
        }
        Thread.sleep(1000);

    }
}

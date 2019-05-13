package com.sss.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/5/13.
 * 通过路径读取文件中的请求参数，发起http请求，模拟发生数据
 *
 */
public class ThreadSend {

    private static String[] fileAll ={"1.json","2.json","3.json"};

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int taskSize = fileAll.length;
        List<Future> futures = new ArrayList<>();
        for (int i=0; i<taskSize; i++) {
            MyCallBack callBack = new MyCallBack();
            Future f = pool.submit(callBack);
            futures.add(f);
        }

        futures.forEach(future->{
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

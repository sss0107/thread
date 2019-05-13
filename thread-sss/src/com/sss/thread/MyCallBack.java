package com.sss.thread;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2019/5/13.
 */
public class MyCallBack implements Callable<Object>{

    @Override
    public Object call() throws Exception {
        System.out.println("读取文件，获取参数，发起http请求");
        return "返回http请求结果";
    }
}

package com.ybj.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 基于OkHttp的分析使用文档
 * HTTP客户端的工作是接收你的请求并且响应。但是里面的过程缺十分复杂
 * https://github.com/square/okhttp/wiki/Calls
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Calls篇
     *
     * Requests:
     * 包含一个URL，一个方法（POST或者GET），一列响应头。请求也可能包含一个主体：一种特定内容类型的数据流。
     *
     * Responses:
     * 列如：200表示成功 404表示未找到，头文件和可选主体
     *
     *      Rewriting Requests:
     *      目的：为了正确和高效，OkHttp在传输之前重写请求
     *      方式： Content-Length, Transfer-Encoding, User-Agent, Host, Connection, and Content-Type.
     *       Accept-Encoding：将添加一个压缩透明的头文件，除非它已经存在。如果它已经获得Cookies，我们将添加一个Cookies头文件。
     *       If-Modified-Since and If-None-Match：缓存响应
     *
     *       Rewriting Responses：（重定向）
     *       Content-Encoding，Content-Length：不适用于解压缩的响应体
     *       如果条件GET成功，则来自网络和缓存的响应将按照规范的指示进行合并。
     *
     *       Follow-up Requests：（很少用到）
     *       URL被移走，302
     *
     *       Retrying Requests：
     *       连接失败或者断开连接或者服务器本身不能连接，OkHttp将重新请求。
     *
     * Calls：
     * 同步：您的线程阻塞，直到响应可读。
     * 异步：您将请求排入任何线程，并在响应可读时在另一个线程上回调。
     * Calls可以在任何线程取消（重点）
     *
     *       Dispatch
     *       同步：管理多个同步请求
     *       并发了解太浪费资源和产生大量延时
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

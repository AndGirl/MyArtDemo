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


    /**
     * Connections篇
     *
     * OkHttp连接到你的服务器采用三种类型：URL,Address和Route
     *
     * URLS:
     * URLS是HTTP和网络的基础。
     * URLS是抽象的
     * ·它们可能是明文(http)或者密文（https），但是加密算法不应该被使用。
     * ·他们不指定是否使用特殊的代理服务器或者使用代理服务器验证。
     * 他们也是具体的：每一个URL定义一个特殊的路径(like /square/okhttp)和查询 (like ?q=sharks&lang=en)
     *
     * Addresses:
     * Addresses定义了一个webServer(like github.com)连接服务器的所有静态配置：端口号，HTTPS设置
     * 优先网络协议
     * URLS共享同一个地址和底层TCP socket连接。共享连接具有明显的优势：更低的延迟，更高的吞吐量和更低的电量消耗。
     * OkHttp使用ConnectionPool自动重用HTTP/1.X连接和复用HTTP/2和SPDY连接。
     * 在OkHttp一些字段和地址来自URL(scheme, hostname, port)剩余的来自OkHttpClient.
     *
     * Routes:
     * Routes动态连接webServer的信息。这是一个特殊的IP地址（通过DBS查询），要使用的确切代理服务器（如果正在使用ProxySelector）以及要协商哪个版本的TLS（用于HTTPS连接）。
     *
     * Connections:
     * 当你请求一个URL用OkHttp，下面是它的实现。
     * 1.它使用URL并配置OkHttpClient来创建一个地址。这个地址定义了我们如何连接到webServer。
     * 2.它尝试从连接池中检索连接地址
     * 3.如果连接池中没有找到，他尝试选择一个Routes。通常意味着发出一个DNS请求来获取服务的IP地址。
     * 它选择一个TLS版本和代理服务
     * 4.如果是新的Route，则通过构建直接套接字连接，TLS隧道（用于通过HTTP代理的HTTPS）或直接TLS连接进行连接。它根据需要进行TLS握手。
     * 5.发送HTTP请求按响应
     * 如果连接有问题，OkHttp会选择另外一个路径，然后重试。
     * 一旦响应被接收，连接将被存入Pool以便将来复用请求。一段时间不使用后连接将被驱逐。
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

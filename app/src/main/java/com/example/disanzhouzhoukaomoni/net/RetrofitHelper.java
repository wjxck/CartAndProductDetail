package com.example.disanzhouzhoukaomoni.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2018-03-03  09:42
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private static ServiceApi serviceApi;

    static {
        initOkHttpClient();
    }

    /**
     * 创建OkHttpClient
     */
    private static void initOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (okHttpClient == null) {
                    //设置拦截器
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(logging)//添加拦截器
                            .addInterceptor(new MyInterceptor())
                            .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时
                            .build();
                }
            }
        }
    }

    /**
     * 定义一个泛型方法
     *
     * @param tClass
     * @param url
     * @param <T>
     * @return
     */
    public static <T> T createAPi(Class<T> tClass, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);
    }

    /**
     * 初始化ServiceApi
     *
     * @return
     */
    public static ServiceApi getServiceApi() {
        if (serviceApi == null) {
            synchronized (ServiceApi.class) {
                if (serviceApi == null) {
                    serviceApi = createAPi(ServiceApi.class, "https://www.zhaoapi.cn/");
                }
            }
        }
        return serviceApi;
    }

    /**
     * 添加公共参数拦截器
     */
    static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("source", "android")
                    .build();
            Request build = request.newBuilder()
                    .url(httpUrl)
                    .build();
            return chain.proceed(build);
        }
    }
}

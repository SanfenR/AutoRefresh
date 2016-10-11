package com.mz.sanfen.autorefreshdemo.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/10/11.
 */

public class RetrofitHelper {

    Retrofit.Builder reBuild;

    private static RetrofitHelper mInstance;

    private RetrofitHelper() {
        reBuild = new Retrofit.Builder();
        reBuild.baseUrl("http://xxx.com");
    }

    public static RetrofitHelper getInstanse() {
        synchronized (RetrofitHelper.class) {
            if (mInstance == null) {
                synchronized (RetrofitHelper.class) {
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }

    public Call<ResponseBody> downloadFile(String url, final ProgressResponseListener mListener) {
        return reBuild.client(new OkHttpClient.Builder()
//                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body(), mListener)).build();
                    }
                })
                .build())
                .build()
                .create(DownService.class).downloadFile(url);
    }

}

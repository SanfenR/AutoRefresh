package com.mz.sanfen.autorefreshdemo.helper;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sanfen
 * data：16/6/6
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Log.d("--->", "------> " + request.toString() + "\n");

        try{
            Log.d("--->", "------> " + request.method() + "\n"
                    + "------> " + request.isHttps() + "\n"
                    + "------> " + request.url().toString() + "\n"
                    + "------> " + request.headers().toString() + "\n"
                    + "------> " + request.body().contentType() + "\n"
                    + "------> " + request.cacheControl().toString() + "\n"
            );
        } catch (Exception e){
        }
        Response response = chain.proceed(request);
        String content = response.body().string();
        Log.d("--->", content);
        return response;
    }
}

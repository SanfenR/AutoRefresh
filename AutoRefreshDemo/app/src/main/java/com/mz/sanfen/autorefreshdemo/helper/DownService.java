package com.mz.sanfen.autorefreshdemo.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/10/11.
 */

public interface DownService {

    @GET
    Call<ResponseBody> downloadFile(@Url String url);

}

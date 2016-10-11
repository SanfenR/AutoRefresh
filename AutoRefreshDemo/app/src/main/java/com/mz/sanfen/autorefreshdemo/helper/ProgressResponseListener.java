package com.mz.sanfen.autorefreshdemo.helper;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/10/11.
 */

public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}

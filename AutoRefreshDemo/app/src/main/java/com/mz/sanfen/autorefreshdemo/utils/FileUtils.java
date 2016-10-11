package com.mz.sanfen.autorefreshdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/10/11.
 */

public class FileUtils {


    public static void write(String fileName, InputStream in) {
        FileOutputStream fos;
        fos = null;
        try {
            if (in == null) {
                return;
            }
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = in.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {

        } finally {
            close(fos);
        }
    }

    private static void close(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof InputStream) {
                ((InputStream) obj).close();
            } else if (obj instanceof OutputStream) {
                ((OutputStream) obj).close();
            } else if (obj instanceof Writer) {
                ((Writer) obj).close();
            } else if (obj instanceof Reader) {
                ((Reader) obj).close();
            }

        } catch (IOException e) {

        }
    }


}

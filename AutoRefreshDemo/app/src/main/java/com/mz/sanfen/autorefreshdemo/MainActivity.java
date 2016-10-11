package com.mz.sanfen.autorefreshdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_download)
    TextView btn_download;

    final String url = "http://122.228.72.143/down.myapp.com/myapp/smart_ajax/com.tencent.android.qqdownloader/991653_54299305_1475916305307.apk?mkey=57fc8a4b4ec6ae42&f=6606&c=0&fsname=YYB.998886.b47de2d65207a30b417640b632e463c9.apk&p=.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_download})
    public void onClickView(View view){

        switch (view.getId()){

            case R.id.btn_download:
                Intent intent = new Intent(this, DownLoadService.class);
                intent.putExtra("appUrl", url);
                intent.putExtra("filename", this.getExternalCacheDir() + "/" + "xxx.apk");
                startService(intent);
                break;

        }

    }
}

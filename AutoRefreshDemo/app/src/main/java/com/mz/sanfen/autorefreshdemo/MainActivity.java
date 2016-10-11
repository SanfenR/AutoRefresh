package com.mz.sanfen.autorefreshdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_download)
    Button btn_download;

    final String url = "";

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
                intent.putExtra("apkUrl", url);
                startService(intent);
                break;

        }

    }
}

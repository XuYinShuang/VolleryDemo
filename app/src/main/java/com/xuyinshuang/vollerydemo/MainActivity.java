package com.xuyinshuang.vollerydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnShow;

    String url = "http://img.bbs.pchome.net/sjbbs/42/41123.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        btnShow = (Button) findViewById(R.id.btnShow);

        btnShow.setOnClickListener(btnShowListener);
    }

    View.OnClickListener btnShowListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        loadOnVolley();
        }
    };

    private void loadOnVolley(){
        //开始没有图片的时候，显示一张图片
//        imageView.setImageResource();
        //返回一张图片
        VolleyLoadPicture volleyLoadPicture=new VolleyLoadPicture(getApplicationContext(),imageView);
        volleyLoadPicture.getmImageLoader().get(url,volleyLoadPicture.getOne_listener());
    }
}

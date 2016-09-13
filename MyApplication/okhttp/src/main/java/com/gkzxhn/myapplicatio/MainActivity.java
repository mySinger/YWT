package com.gkzxhn.myapplicatio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button load;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.img);
        load = (Button) findViewById(R.id.btn);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImg();
            }
        });
    }

    public void loadImg(){
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://a2.qpic.cn/psb?/V1179pqb2UZG8p/R5THgLvBVwu.mpzsS8XgRWS8Pxkszz8RWHU0ydvcEVw!/b/dOUAAAAAAAAA&ek=1&kp=1&pt=0&bo=7gLnAwAAAAAFACs!&sce=60-2-2&rf=viewer_311")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                InputStream res = response.body().byteStream();
                //将流直接编码为位图对象
                final Bitmap bm = BitmapFactory.decodeStream(res);
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        iv.setImageBitmap(bm);
                    }

                });
            }
        });
    }
}

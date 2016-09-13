package com.gkzxhn.switchbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SlideSwitch slideSwitch = (SlideSwitch) findViewById(R.id.slideSwitch1);
        slideSwitch.setOnSwitchChangedListener(new SlideSwitch.OnSwitchChangedListener() {
            @Override
            public void onSwitchChanged(SlideSwitch obj, int status) {
                switch (status){
                    case 0:
                        Toast.makeText(MainActivity.this,"关闭",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"打开",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}

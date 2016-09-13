package com.gkzxhn.datepickerdialog;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class TestClock extends Activity implements OnDateSetListener, OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button btn = (Button) findViewById(R.id.date);
        btn.setOnClickListener(this);
    }

    //普通按钮事件
    public void onClick(View v) {
        //创建一个日历引用calendar，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //创建一个Date实例
        Date myDate = new Date();
        //设置日历的时间，把一个新建Date实例myDate传入
        calendar.setTime(myDate);
        //获得日历中的 year month day
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
       /* 新建一个DatePickerDialog 构造方法中
        （设备上下文，OnDateSetListener时间设置监听器，默认年，默认月，默认日）*/
        DatePickerDialog dlg = new DatePickerDialog(this, this, year, month, day);
        //让DatePickerDialog显示出来
        dlg.show();
    }

    //DatePickerDialog 中按钮Set按下时自动调用
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //通过id获得TextView对象
        Log.e("nihao","你好！");
        TextView txt = (TextView) findViewById(R.id.text);
        txt.setText(Integer.toString(year) + "-" +
                Integer.toString(monthOfYear+1) + "-" +
                Integer.toString(dayOfMonth));
    //设置text
    }
}

package com.gkzxhn.gridview;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;


public class MainActivity extends Activity implements OnCheckedChangeListener {
    //三个选项卡
    private RadioButton mRBtnFrist;
    private RadioButton mRBtnSecond;
    private RadioButton mRBtnThrid;

    //存放fragment对应的容器
    private FrameLayout mFragmentContain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentContain = (FrameLayout) findViewById(R.id.fl_contain);
        mRBtnFrist = (RadioButton) findViewById(R.id.rb_first);
        mRBtnSecond = (RadioButton) findViewById(R.id.rb_second);
        mRBtnThrid = (RadioButton) findViewById(R.id.rb_thrid);


        mRBtnThrid.setOnCheckedChangeListener(this);

        mRBtnThrid.setOnCheckedChangeListener(this);

        mRBtnThrid.performClick();//此处设置默认第三个选项卡对应的fragment显示

        mRBtnSecond.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //用户当前浏览的选项卡
            int checkedWidgetId = buttonView.getId();
            Log.e("checkedWidgetId:", checkedWidgetId + "");

            mRBtnFrist.setChecked(checkedWidgetId == R.id.rb_first);
            Log.e("1:", R.id.rb_first + "");

            mRBtnSecond.setChecked(checkedWidgetId == R.id.rb_second);
            Log.e("2:", R.id.rb_second + "");

            mRBtnThrid.setChecked(checkedWidgetId == R.id.rb_thrid);
            Log.e("3:", R.id.rb_thrid + "");

            showFragment(checkedWidgetId);
        } else {
            //此处记录了用户上次浏览的选项卡
            String unCheckFragmentTag = getTagById(buttonView.getId());
            Fragment unCheckFragment = getFragmentManager().findFragmentByTag(unCheckFragmentTag);
            if (unCheckFragment != null) {
                //隐藏上次显示到fragment,确保fragment不会重叠
                getFragmentManager()
                        .beginTransaction()
                        .hide(unCheckFragment)
                        .commit();
            }
        }
    }

    /**
     * 显示对应的fragment
     *
     * @param checkedRadioBtnId
     */
    private void showFragment(int checkedRadioBtnId) {
        String tag = getTagById(checkedRadioBtnId);
        Fragment mainFragment = getFragmentManager().findFragmentByTag(tag);
        Log.e("mainFragment", mainFragment + "");
        if (mainFragment == null) {
            if (tag.equals("first")) {
                //如果没有找到对应的fragment则生成一个新的fragment，并添加到容器中
                Fragment newFragment = new FirstFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_contain, newFragment, tag)
                        .commit();
            } else if (tag.equals("second")) {
                Fragment newFragment = new SecondFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_contain, newFragment, tag)
                        .commit();
            } else if (tag.equals("third")) {
                Fragment newFragment = new ThirdFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_contain, newFragment, tag)
                        .commit();
            }
        } else {
            //如果找到了fragment则显示它
            getFragmentManager()
                    .beginTransaction()
                    .show(mainFragment)
                    .commit();
        }
    }

    /**
     * 为三个fragment分别取三个不同到tag名
     *
     * @param widgetId
     * @return
     */
    private String getTagById(int widgetId) {
        if (widgetId == R.id.rb_first) {
            return "first";
        } else if (widgetId == R.id.rb_second) {
            return "second";
        } else {
            return "third";
        }
    }
}

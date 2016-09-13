package com.gkzxhn.switchbutton;

import android.view.View;

/**
 * Created by ZengWenZhi on 2016/8/26 0026.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * SlideSwitch 仿iphone滑动开关组件，仿百度魔图滑动开关组件
 * 组件分为三种状态：打开、关闭、正在滑动<br/>
 * 使用方法：
 * <pre>SlideSwitch slideSwitch = new SlideSwitch(this);
 *slideSwitch.setOnSwitchChangedListener(onSwitchChangedListener);
 *linearLayout.addView(slideSwitch);
 </pre>
 注：也可以加载在xml里面使用
 * @author scott
 *
 */
public class SlideSwitch extends View
{
    public static final String TAG = "SlideSwitch";
    public static final int SWITCH_OFF = 0;//关闭状态
    public static final int SWITCH_ON = 1;//打开状态
    public static final int SWITCH_SCROLING = 2;//滚动状态

    //用于显示的文本
    private String mOnText = "打开";
    private String mOffText = "关闭";

    private int mSwitchStatus = SWITCH_OFF;

    private boolean mHasScrolled = false;//表示是否发生过滚动

    private int mSrcX = 0, mDstX = 0;

    private int mBmpWidth = 0;
    private int mBmpHeight = 0;
    private int mThumbWidth = 0;
    //ANTI_ALIAS_FLAG 这个标志位意指抗锯齿的,创建画笔
    private     Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private OnSwitchChangedListener mOnSwitchChangedListener = null;

    //开关状态图
    Bitmap mSwitch_off, mSwitch_on, mSwitch_thumb;

    public SlideSwitch(Context context)
    {
        this(context, null);
    }

    public SlideSwitch(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SlideSwitch(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    //初始化三幅图片,将三幅图转化成bitmap类型
    private void init()
    {
        Resources res = getResources();
        mSwitch_off = BitmapFactory.decodeResource(res, R.drawable.bg_switch_off);
        mSwitch_on = BitmapFactory.decodeResource(res, R.drawable.bg_switch_on);
        mSwitch_thumb = BitmapFactory.decodeResource(res, R.drawable.switch_thumb);
        //获取组件的宽、高。
        mBmpWidth = mSwitch_on.getWidth();
        mBmpHeight = mSwitch_on.getHeight();
        mThumbWidth = mSwitch_thumb.getWidth();
    }

    @Override
    public void setLayoutParams(LayoutParams params)
    {
        params.width = mBmpWidth;
        params.height = mBmpHeight;
        super.setLayoutParams(params);
    }

    /**
     * 为开关控件设置状态改变监听函数
     * @param onSwitchChangedListener 参见 {@link OnSwitchChangedListener}
     */
    public void setOnSwitchChangedListener(OnSwitchChangedListener onSwitchChangedListener)
    {
        mOnSwitchChangedListener = onSwitchChangedListener;
    }

    /**
     * 设置开关上面的文本
     * @param onText  控件打开时要显示的文本
     * @param offText  控件关闭时要显示的文本
     */
    public void setText(final String onText, final String offText)
    {
        mOnText = onText;
        mOffText =offText;
        invalidate();
    }

    /**
     * 设置开关的状态
     * @param on 是否打开开关 打开为true 关闭为false
     */
    public void setStatus(boolean on)
    {
        mSwitchStatus = ( on ? SWITCH_ON : SWITCH_OFF);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //获取当前的点击事件类型
        int action = event.getAction();
        Log.d(TAG, "onTouchEvent  x="  + event.getX());
        switch (action) {
            //按下
            case MotionEvent.ACTION_DOWN:
                mSrcX = (int) event.getX();
                break;
            //滑动
            case MotionEvent.ACTION_MOVE:
                mDstX = Math.max( (int) event.getX(), 10);
                mDstX = Math.min( mDstX, 62);
                if(mSrcX == mDstX)
                    return true;

                //代表是否发生过滚动
                mHasScrolled = true;
                AnimationTransRunnable aTransRunnable = new AnimationTransRunnable(mSrcX, mDstX, 0);
                new Thread(aTransRunnable).start();
                mSrcX = mDstX;
                break;
            //抬起
            case MotionEvent.ACTION_UP:
                if(mHasScrolled == false)//如果没有发生过滑动，就意味着这是一次单击过程
                {
                    //返回绝对值
                    mSwitchStatus = Math.abs(mSwitchStatus-1);
                    int xFrom = 10, xTo = 62;
                    if(mSwitchStatus == SWITCH_OFF)
                    {
                        xFrom = 62;
                        xTo = 10;
                    }
                    //滑动动画
                    AnimationTransRunnable runnable = new AnimationTransRunnable(xFrom, xTo, 1);
                    new Thread(runnable).start();
                }
                else
                {
                    /**
                     * invalidate()是用来刷新View的，必须是在UI线程中进行工作。
                     * 比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。
                     * invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。
                     * 在Activity中显式地调用View对象中的invalidate()方法的时候，
                     * 系统会自动调用 View的onDraw()方法。
                     */
                    invalidate();
                    mHasScrolled = false;
                }
                //状态改变的时候 回调事件函数
                if(mOnSwitchChangedListener != null)
                {
                    mOnSwitchChangedListener.onSwitchChanged(this, mSwitchStatus);
                }
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //绘图的时候 内部用到了一些数值的硬编码，其实不太好，
        //主要是考虑到图片的原因，图片周围有透明边界，所以要有一定的偏移
        //硬编码的数值只要看懂了代码，其实可以理解其含义，可以做相应改进。
        mPaint.setTextSize(50);
        /**
         * Typeface(字型)，这个 API是用来设置字体以及字体风格。
         * DEFAULT_BOLD：默认的字体对象。
         */
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        if(mSwitchStatus == SWITCH_OFF)
        {
            drawBitmap(canvas, null, null, mSwitch_off);
            drawBitmap(canvas, null, null, mSwitch_thumb);
            mPaint.setColor(Color.rgb(105, 105, 105));
            //把当前画布的原点移到(mSwitch_thumb.getWidth(),0),后面的操作都以(mSwitch_thumb.getWidth(),0)作为参照点，默认原点为(0,0)
            canvas.translate(mSwitch_thumb.getWidth(), 0);
            /**
             * canvas.drawText(text, x, y, paint)，第一个参数是我们需要绘制的文本，第四个参数是我们的画笔，
             * 这两个不用多说，主要是第二和第三个参数的含义，这两个参数在不同的情况下的值还是不一样的，
             * x默认是这个字符串的左边在屏幕的位置，
             * 如果设置了paint.setTextAlign(Paint.Align.CENTER);那就是字符的中心，
             * y是指定这个字符baseline在屏幕上的位置，大家记住了，不要混淆，y不是这个字符中心在屏幕上的位置，
             * 而是baseline在屏幕上的位置。
             */
            canvas.drawText(mOffText, 50, 100, mPaint);
        }
        else if(mSwitchStatus == SWITCH_ON)
        {
            drawBitmap(canvas, null, null, mSwitch_on);
            //锁画布(为了保存之前的画布状态)
            int count = canvas.save();
            canvas.translate((mSwitch_on.getWidth() - mSwitch_thumb.getWidth()), 0);
            drawBitmap(canvas, null, null, mSwitch_thumb);
            mPaint.setColor(Color.WHITE);

            //把当前画布返回（调整）到上一个save()状态之前
            canvas.restoreToCount(count);
            canvas.drawText(mOnText, 80, 100, mPaint);
        }
        else //SWITCH_SCROLING
        {
            mSwitchStatus = mDstX > 35 ? SWITCH_ON : SWITCH_OFF;
            drawBitmap(canvas, new Rect(0, 0, mDstX, mBmpHeight), new Rect(0, 0, (int)mDstX, mBmpHeight), mSwitch_on);
            mPaint.setColor(Color.WHITE);
            canvas.drawText(mOnText, 17, 20, mPaint);

            int count = canvas.save();
            canvas.translate(mDstX, 0);
            drawBitmap(canvas, new Rect(mDstX, 0, mBmpWidth, mBmpHeight),
                    new Rect(0, 0, mBmpWidth - mDstX, mBmpHeight), mSwitch_off);
            canvas.restoreToCount(count);

            count = canvas.save();
            canvas.clipRect(mDstX, 0, mBmpWidth, mBmpHeight);
            canvas.translate(mThumbWidth, 0);
            mPaint.setColor(Color.rgb(105, 105, 105));
            canvas.drawText(mOffText, 0, 20, mPaint);
            canvas.restoreToCount(count);

            count = canvas.save();
            canvas.translate(mDstX - mThumbWidth / 2, 0);
            drawBitmap(canvas, null, null, mSwitch_thumb);
            canvas.restoreToCount(count);
        }

    }

    public void drawBitmap(Canvas canvas, Rect src, Rect dst, Bitmap bitmap)
    {
        dst = (dst == null ? new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()) : dst);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, src, dst, paint);
    }

    /**
     * AnimationTransRunnable 做滑动动画所使用的线程
     */
    private class AnimationTransRunnable implements Runnable
    {
        private int srcX, dstX;
        private int duration;

        /**
         * 滑动动画
         * @param srcX 滑动起始点
         * @param dstX 滑动终止点
         * @param duration 是否采用动画，1采用，0不采用
         */
        public AnimationTransRunnable(float srcX, float dstX, final int duration)
        {
            this.srcX = (int)srcX;
            this.dstX = (int)dstX;
            this.duration = duration;
        }

        @Override
        public void run()
        {
            final int patch = (dstX > srcX ? 5 : -5);
            if(duration == 0)
            {
                SlideSwitch.this.mSwitchStatus = SWITCH_SCROLING;
                SlideSwitch.this.postInvalidate();
            }
            else
            {
                Log.d(TAG, "start Animation: [ " + srcX + " , " + dstX + " ]");
                int x = srcX + patch;
                while (Math.abs(x-dstX) > 5)
                {
                    mDstX = x;
                    SlideSwitch.this.mSwitchStatus = SWITCH_SCROLING;
                    SlideSwitch.this.postInvalidate();
                    x += patch;
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                mDstX = dstX;
                SlideSwitch.this.mSwitchStatus = mDstX > 35 ? SWITCH_ON : SWITCH_OFF;
                SlideSwitch.this.postInvalidate();
            }
        }

    }

    public static interface OnSwitchChangedListener
    {
        /**
         * 状态改变 回调函数
         * @param status  SWITCH_ON表示打开 SWITCH_OFF表示关闭
         */
        public abstract void onSwitchChanged(SlideSwitch obj, int status);
    }

}

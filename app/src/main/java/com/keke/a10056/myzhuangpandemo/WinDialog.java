package com.keke.a10056.myzhuangpandemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class WinDialog extends Dialog {


    private ImageView imageView;
    private Bitmap bitmap;
    private Activity context;//上下文

    public WinDialog(@NonNull Activity context, Bitmap bitmap) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.bitmap = bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
//        dialogWindow.setWindowAnimations();设置动画效果
        setContentView(R.layout.dialog_win_layout);


        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5;// 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失


        imageView = (ImageView) findViewById(R.id.img_face);
        imageView.setImageBitmap(bitmap);
    }



    //    private static final int MIN_DELAY_TIME = 3000;  // 两次点击间隔不能少于1000ms
//    private static long lastClickTime;
//
//
//    public static boolean isFastClick() {
//        boolean flag = true;
//        long currentClickTime = System.currentTimeMillis();
//        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
//            flag = false;
//        }
//        lastClickTime = currentClickTime;
//        return flag;
//    }
}

package com.keke.a10056.myzhuangpandemo;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.keke.a10056.myzhuangpandemo.listener.RotateListener;
import com.keke.a10056.myzhuangpandemo.view.WheelSurfView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private WheelSurfView.Builder build;
    private List<Integer> colors;
    private List<Integer> color;
    private List<Bitmap> mListBitmap;
    private List<Bitmap> mListBitmaps;
    private int typeNum = 7;
    private WheelSurfView wheelSurfView2;
    private TextView tv_qd;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imageView.setVisibility(View.GONE);

            build = new WheelSurfView.Builder()
                    .setmColors(color)
                    .setmIcons(mListBitmaps)
                    .setmTypeNum(typeNum)
                    .build();
            wheelSurfView2.setConfig(build);

//            int position = new Random().nextInt(typeNum) + 1;
//            wheelSurfView2.startRotate(position);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img_face);
        tv_qd = findViewById(R.id.tv_qidong);

        tv_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeNum = 7;
                mListBitmaps = new ArrayList<>();
                for (int i = 0; i < mListBitmap.size(); i++) {
                    mListBitmaps.add(mListBitmap.get(i));
                }
                color = new ArrayList<>();
                for (int i = 0; i < colors.size(); i++) {
                    color.add(colors.get(i));
                }

                build = new WheelSurfView.Builder()
                        .setmColors(color)
                        .setmIcons(mListBitmaps)
                        .setmTypeNum(typeNum)
                        .build();
                wheelSurfView2.setConfig(build);
            }
        });

        /**
         * 新增使用代码设置属性的方式
         *
         * 请注意：
         *  使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
         *  使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
         *  使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
         *
         *  重要的事情说三遍
         *
         *  例如
         *  <com.cretin.www.wheelsruflibrary.view.WheelSurfView
         *      android:id="@+id/wheelSurfView2"
         *      android:layout_width="match_parent"
         *      android:layout_height="match_parent"
         *      wheelSurfView:typenum="-1"
         *      android:layout_margin="20dp">
         *
         *  然后调用setConfig()方法来设置你的属性
         *
         * 请注意：
         *  你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
         *  你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
         *  你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
         *
         *  重要的事情说三遍
         *
         * 请注意：
         *  .setmColors(\)
         *  .setmDeses(des)
         *  .setmIcons(mListBitmap)
         *  这三个方法中的参数长度必须一致 否则会报运行时异常
         */

        colors = new ArrayList<>();
        colors.add(Color.parseColor("#F6829F"));
        colors.add(Color.parseColor("#E83030"));
        colors.add(Color.parseColor("#464AE1"));
        colors.add(Color.parseColor("#4394C5"));
        colors.add(Color.parseColor("#D47EFF"));
        colors.add(Color.parseColor("#DEDC88"));
        colors.add(Color.parseColor("#C5CCD1"));

        color = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            color.add(colors.get(i));
        }


        mListBitmap = new ArrayList<>();
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.iphone));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.node));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.meizu));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.liwu));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.dianjiluyin));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.all));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.yuanhuan));


        mListBitmaps = new ArrayList<>();
        for (int i = 0; i < mListBitmap.size(); i++) {
            mListBitmaps.add(mListBitmap.get(i));
        }


        mListBitmap = WheelSurfView.rotateBitmaps(mListBitmap);

        wheelSurfView2 = findViewById(R.id.wheelSurfView2);
        build = new WheelSurfView.Builder()
                .setmColors(color)
                .setmIcons(mListBitmaps)
                .setmTypeNum(typeNum)
                .build();
        wheelSurfView2.setConfig(build);


        //添加滚动监听
        wheelSurfView2.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des, Bitmap bitmap) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);


                //判断重新加载转盘   每一次减一
                if (typeNum == 2) {
                    mListBitmaps.remove((typeNum - position + 1) %
                            typeNum);
                    Bitmap bitmap1 = mListBitmaps.get(0);

                    WinDialog winDialog = new WinDialog(MainActivity.this, bitmap1);
                    winDialog.show();

                } else {
                    //                //根据算法删除删除选中的那一栏
                    color.remove((typeNum - position + 1) %
                            typeNum);
                    mListBitmaps.remove((typeNum - position + 1) %
                            typeNum);

                    typeNum = typeNum - 1;
                    handler.sendEmptyMessageDelayed(2, 3 * 1000);
                }
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {

                //可以指定转盘转到哪里逆时针
//                goImg.setEnabled(false);
                //模拟位置
                int position = new Random().nextInt(typeNum) + 1;
                wheelSurfView2.startRotate(position);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}

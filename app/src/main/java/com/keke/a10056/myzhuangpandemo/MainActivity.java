package com.keke.a10056.myzhuangpandemo;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.keke.a10056.myzhuangpandemo.listener.RotateListener;
import com.keke.a10056.myzhuangpandemo.view.WheelSurfView;


import java.util.ArrayList;

import java.util.List;



/**
 * 2019/5/9
 * zhangyu
 * 转盘抽奖
 **/


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private WheelSurfView.Builder build;
    private List<Integer> colors;
    private List<Bitmap> mListBitmap;
    private WheelSurfView wheelSurfView2;
    private EditText tv_qd;
    private ImageView imgGo;
    private Button btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListBitmap = new ArrayList<>();

        initView();
        initData();
    }


    private void initView() {
        imageView = findViewById(R.id.img_face);
        tv_qd = findViewById(R.id.ev_qidong);
        btn = findViewById(R.id.btn_tianjia);
        wheelSurfView2 = findViewById(R.id.wheelSurfView2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListBitmap.size() == 8) {
                    Toast.makeText(MainActivity.this, "人数已到达游戏上线", Toast.LENGTH_LONG).show();
                    return;
                }

                initChuShiHua();
            }
        });

        //添加滚动监听
        wheelSurfView2.setRotateListener(new RotateListener() {
            //动画结束回调
            @Override
            public void rotateEnd(int position, String des) {
                //判断重新加载转盘   每一次减一
                if (colors.size() == 2 || colors.size() == 1) {
                    initEnd(position);
                } else {

                    //删除制定位置
                    initClear(position);
                }
            }

            //动画中回调
            @Override
            public void rotating(ValueAnimator valueAnimator) {
            }

            //动画初始化点击回调
            @Override
            public void rotateBefore(ImageView goImg) {
                //对按键
                imgGo = (ImageView) goImg;
                //启动转盘
                initQiDong();
            }
        });
    }

    private void initData() {

        colors = new ArrayList<>();
        colors.add(Color.parseColor("#F6829F"));
        colors.add(Color.parseColor("#E83030"));
        colors.add(Color.parseColor("#464AE1"));
        colors.add(Color.parseColor("#4394C5"));
        colors.add(Color.parseColor("#D47EFF"));
        colors.add(Color.parseColor("#DEDC88"));
        colors.add(Color.parseColor("#C5CCD1"));
        colors.add(Color.parseColor("#DEDC88"));


        initChuShiHua();
    }



    /**
     * 结束方法
     **/
    private void initEnd(int position) {

        //对数据显示图片进行处理
        //加载数据是顺时针加载数据，选择数据itemID是逆时针获取所以用(mTypeNum - pos + 1) %mTypeNum);
        if (colors.size() == 2) {
            colors.remove((colors.size() - position + 1) %
                    colors.size());
        }

        initChuShiHua();
    }


    /**
     * 删除人数
     **/
    private void initClear(int position) {
        //根据算法删除删除选中的那一栏
        //加载数据是顺时针加载数据，选择数据itemID是逆时针获取所以用(mTypeNum - pos + 1) %mTypeNum);s
        colors.remove((colors.size() - position + 1) %
                colors.size());

        initChuShiHua();
    }


    /**
     * 初始化转盘
     **/
    private void initChuShiHua() {
        build = new WheelSurfView.Builder()
                .setmColors(colors)
                .setmTypeNum(colors.size())
                .build();

        wheelSurfView2.setConfig(build);

    }


    /**
     * 启动转盘方法
     **/
    private void initQiDong() {
        wheelSurfView2.startRotate(Integer.parseInt("1"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

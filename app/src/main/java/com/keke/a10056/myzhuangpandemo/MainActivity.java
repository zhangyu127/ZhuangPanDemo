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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.keke.a10056.myzhuangpandemo.listener.RotateListener;
import com.keke.a10056.myzhuangpandemo.view.WheelSurfView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.*;


/**
 * 2019/5/9
 * zhangyu
 * 转盘抽奖
 **/

/**
 * 新增使用代码设置属性的方式
 * <p>
 * 请注意：
 * 使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
 * 使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
 * 使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
 * <p>
 * 重要的事情说三遍
 * <p>
 * 例如
 * <com.cretin.www.wheelsruflibrary.view.WheelSurfView
 * android:id="@+id/wheelSurfView2"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * wheelSurfView:typenum="-1"
 * android:layout_margin="20dp">
 * <p>
 * 然后调用setConfig()方法来设置你的属性
 * <p>
 * 请注意：
 * 你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
 * 你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
 * 你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
 * <p>
 * 重要的事情说三遍
 * <p>
 * 请注意：
 * .setmColors(\)
 * .setmDeses(des)
 * .setmIcons(mListBitmap)
 * 这三个方法中的参数长度必须一致 否则会报运行时异常
 */


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private WheelSurfView.Builder build;
    private List<Integer> colors;
    private List<Integer> color;
    private List<Bitmap> mListBitmap;
    private WheelSurfView wheelSurfView2;
    private EditText tv_qd;
    private ImageView imgGo;
    private Button btn;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            imageView.setVisibility(View.GONE);

            //初始化方法
            initChuShiHua();
        }
    };
    private List<Bitmap> bitmap;


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

                //添加人数
                initAdd();
                initChuShiHua();
            }
        });

        //添加滚动监听
        wheelSurfView2.setRotateListener(new RotateListener() {
            //动画结束回调
            @Override
            public void rotateEnd(int position, String des, Bitmap bitmap) {
                //判断重新加载转盘   每一次减一
                if (mListBitmap.size() == 2 || mListBitmap.size() == 1) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(bitmap);
                    initEnd(position);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(bitmap);
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
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.iphone));


        colors = new ArrayList<>();
        colors.add(Color.parseColor("#F6829F"));
        colors.add(Color.parseColor("#E83030"));
        colors.add(Color.parseColor("#464AE1"));
        colors.add(Color.parseColor("#4394C5"));
        colors.add(Color.parseColor("#D47EFF"));
        colors.add(Color.parseColor("#DEDC88"));
        colors.add(Color.parseColor("#C5CCD1"));
        colors.add(Color.parseColor("#DEDC88"));
        colors.add(Color.parseColor("#D47EFF"));


        //对颜色进行遍历    根据人物bitmap集合来遍历颜色
        color = new ArrayList<>();
        for (int i = 0; i < mListBitmap.size(); i++) {
            color.add(colors.get(i));
        }

        initChuShiHua();
    }


    /**
     * 添加人数
     **/
    private void initAdd() {

        bitmap = mListBitmap;
        mListBitmap = new ArrayList<>();
        for (Bitmap bitmap1 : bitmap) {
            mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.liwu));
        }
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.liwu));

        color.clear();
        for (int i = 0; i < mListBitmap.size(); i++) {
            color.add(colors.get(i));
        }
    }

    /**
     * 结束方法
     **/
    private void initEnd(int position) {

        //对数据显示图片进行处理
        //加载数据是顺时针加载数据，选择数据itemID是逆时针获取所以用(mTypeNum - pos + 1) %mTypeNum);
        if (mListBitmap.size() == 2) {
            mListBitmap.remove((mListBitmap.size() - position + 1) %
                    mListBitmap.size());
            color.remove((mListBitmap.size() - position + 1) %
                    mListBitmap.size());
        }
        Bitmap bitmap1 = mListBitmap.get(0);

        WinDialog winDialog = new WinDialog(MainActivity.this, bitmap1);
        winDialog.show();
        initChuShiHua();
    }


    /**
     * 删除人数
     **/
    private void initClear(int position) {
        //根据算法删除删除选中的那一栏
        //加载数据是顺时针加载数据，选择数据itemID是逆时针获取所以用(mTypeNum - pos + 1) %mTypeNum);s
        color.remove((mListBitmap.size() - position + 1) %
                mListBitmap.size());
        mListBitmap.remove((mListBitmap.size() - position + 1) %
                mListBitmap.size());

        bitmap.remove((mListBitmap.size() - position + 1) %
                mListBitmap.size());

        handler.sendEmptyMessageDelayed(2, 3 * 1000);
    }


    /**
     * 初始化转盘
     **/
    private void initChuShiHua() {

        mListBitmap = WheelSurfView.rotateBitmaps((ArrayList<Bitmap>) mListBitmap);

        build = new WheelSurfView.Builder()
                .setmColors(color)
                .setmIcons(mListBitmap)
                .setmTypeNum(mListBitmap.size())
                .build();

        wheelSurfView2.setConfig(build);

        if (bitmap == null) {
            return;
        }
        Iterator<Bitmap> iterator = bitmap.iterator();
        while (iterator.hasNext()) {
            Bitmap next = iterator.next();
            next.recycle();
            iterator.remove();
        }
        bitmap = null;
    }


    /**
     * 启动转盘方法
     **/
    private void initQiDong() {

        if ("".equals(tv_qd.getText().toString())) {
            Toast.makeText(MainActivity.this, "你输入为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (mListBitmap.size() == 1) {
            Toast.makeText(MainActivity.this, "目前仅一人，无法进行游戏", Toast.LENGTH_LONG).show();
            return;
        }

        wheelSurfView2.startRotate(Integer.parseInt(tv_qd.getText().toString().trim()));

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

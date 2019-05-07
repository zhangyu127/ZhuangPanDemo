package com.keke.a10056.myzhuangpandemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.keke.a10056.myzhuangpandemo.R;


/**
 * 2019/5/6
 * 张宇
 * 1.我们让ZiDingYiView集成view
 * 2.他有4个构造方法，但是我们只需要两个，我们全部添加进去
 **/
public class ZiDingYiView extends View {

    //声明一个画笔的对象
    private Paint paint;

    public ZiDingYiView(Context context) {
        super(context);
    }

    public ZiDingYiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /**
         * TypedArray：对象描述类似数组的一个潜在的二进制数据的缓冲区(官方描述)
         * 就是系统在默认的资源文件R.styleable中去获取相关的配置。
         * 如果appearance不为空，它就会去寻找获取相关属性
         * 也就是冲我们自定属性样式中，来引用你需要的某条属性
         */
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Myview);
//        int colors = typedArray.getColor(R.styleable.Myview_rect_color, 0xffff0000);
//        setBackgroundColor(colors);
//        typedArray.recycle();
    }

    public ZiDingYiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ZiDingYiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //声明一个画笔，设置一个白色的画布，这样笔和画布都有了
        paint = new Paint();
        canvas.drawColor(Color.WHITE);

        //我们给画笔设置一些属性，

        paint.setAntiAlias(true);//取消锯齿
        paint.setColor(Color.BLUE);//画笔的颜色
        paint.setStyle(Paint.Style.STROKE);//画笔的粗细
        paint.setStrokeWidth(4);//画笔的宽度
        int viewWidth = this.getWidth();//获取控件屏幕


//-------------------------------------------------------------------------
        /*
         * 给我们绘制的图形进行填充，看效果打开代码即可//        Shader mShader = new LinearGradient(0, 0, 40, 60
//                ,new int[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }
//                , null , Shader.TileMode.REPEAT);
//        paint.setShader(mShader);
//        //设置阴影
//        paint.setShadowLayer(25 , 20 , 20 , Color.GRAY);
         * */
//        paint.setStyle(Paint.Style.FILL);//充满填充
//        paint.setColor(Color.RED);//填充颜色

//-------------------------------------------------------------------------
        /*
         * 设置图形渐变，看效果打开代码即可
         * */


//-------------------------------------------------------------------------
        /*
         * 绘制圆形
         * drawCirecle(cx,xy,radius，paint)
         * radius: 半径
         * paint:画笔
         * */
        canvas.drawCircle(viewWidth / 10 + 10, viewWidth / 10 + 10, viewWidth / 10, paint);
//-------------------------------------------------------------------------
        /*
         * 绘制正方形
         * drawRect(左，上，右，下，画笔)
         * */
        canvas.drawRect(20 , viewWidth / 5 + 40 , viewWidth / 5 + 10,viewWidth * 2 / 5 + 20 , paint);

//-------------------------------------------------------------------------
        /*
         * 绘制矩形
         * drawRect(左，上，右，下，画笔)
         * */
        canvas.drawRect(10, viewWidth * 2 / 5 + 30, viewWidth / 5 + 10, viewWidth / 2 + 30, paint);

//-------------------------------------------------------------------------

        /*
         * 绘制椭圆
         *1、我们先来一个矩形，
         *
         *
         * */
        RectF re1 = new RectF(10, viewWidth / 2 + 40, 10 + viewWidth / 5 ,viewWidth * 3 / 5 + 40);
        // 绘制圆角矩形
        canvas.drawRoundRect(re1, 15, 15, paint);

//-------------------------------------------------------------------------
        /*
         * 定义一个Path对象，封闭成一个三角形
         * 三角形的绘制，和上面不一样，这里面需要有3个坐标点
         * 连接三个坐标点即可（左、右、上下）
         * */

        Path path1 = new Path();
        path1.moveTo(10, viewWidth * 9 / 10 + 60);
        path1.lineTo(viewWidth / 5 + 10, viewWidth * 9 / 10 + 60);
        path1.lineTo(viewWidth / 10 + 10, viewWidth * 7 / 10 + 60);
        path1.close();
        canvas.drawPath(path1, paint);
//-------------------------------------------------------------------------
        /*
         * 定义一个Path对象，封闭成一个五角形
         * 连接五个坐标点即可（顺时针开始绘制点）
         * */

        Path path2 = new Path();
        path2.moveTo(10 + viewWidth / 15, viewWidth * 9 / 10 + 70);
        path2.lineTo(10 + viewWidth * 2 / 15, viewWidth * 9 / 10 + 70);
        path2.lineTo(10 + viewWidth / 5, viewWidth + 70);
        path2.lineTo(10 + viewWidth / 10, viewWidth * 11/10 + 70);
        path2.lineTo(10 , viewWidth + 70);
        path2.close();
        canvas.drawPath(path2, paint);

//-------------------------------------------------------------------------
        /*
         * 文字的添加
         *  paint.setTextSize(textSize);//设置字体大小
         *  paint.setTypeface(typeface);//设置字体类型搜索
         *  canvas.drawText(text, x, y, paint);//使用画笔paint
         * */
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setTextSize(36);
        canvas.drawText("圆形", 5 + viewWidth * 3 / 5, viewWidth / 10 + 10, paint);
        canvas.drawText("正方形", 60 + viewWidth * 3 / 5, viewWidth * 3 / 10 + 20, paint);
        canvas.drawText("长方形", 60 + viewWidth * 3 / 5, viewWidth * 1 / 2 + 20, paint);
        canvas.drawText("圆角矩形" , 60 + viewWidth * 3 / 5, viewWidth * 3 / 5 + 30, paint);
        canvas.drawText("椭圆", 60 + viewWidth * 3 / 5, viewWidth * 7 / 10 + 30, paint);
        canvas.drawText("三角", 60 + viewWidth * 3 / 5, viewWidth * 9 / 10 + 30, paint);
        canvas.drawText("五角星", 60 + viewWidth * 3 / 5, viewWidth * 11 / 10 + 30, paint);

    }
}

package com.example.marina.userauthentication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;
import java.util.Random;

public class EnterActivity extends AppCompatActivity implements DataView {
    private DataPresenter dataPresenter;
    float touchX, touchY, cx, cy;
    float radius = 60;
    Random rdm = new Random();
    Display display;
    Point size;
    int start, stop, startPress, stopPress;
    Calendar c;
    int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        GraphicsView myView = new GraphicsView(this);
        setContentView(myView);
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        dataPresenter = new DataPresenterImpl(this);
    }

    @Override
    public int getTimePress() {
        return stopPress - startPress;
    }

    @Override
    public int getTimeTouch() {
        return stop - start;

    }

    public class GraphicsView extends View {
        public GraphicsView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint mPaint = new Paint();
            // стиль Заливка
            mPaint.setStyle(Paint.Style.FILL);
            // закрашиваем холст белым цветом
            mPaint.setColor(Color.WHITE);
            canvas.drawPaint(mPaint);
            mPaint.setAntiAlias(true);
            if (count <= 10) {
                cx = radius + 10 + rdm.nextInt(size.x - (int) radius * 2);
                cy = radius + 10 + rdm.nextInt(size.y - (int) radius * 5);
                mPaint.setColor(Color.GREEN);
                canvas.drawCircle(cx, cy, radius, mPaint);
                c = Calendar.getInstance();
                start = c.get(Calendar.MILLISECOND) + c.get(Calendar.SECOND) * 1000 + c.get(Calendar.MINUTE) * 60000 + c.get(Calendar.HOUR) * 3600000;
            } else {
                // Рисуем текст
                mPaint.setColor(Color.BLACK);
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setAntiAlias(true);
                mPaint.setTextSize(48);
                String text = "Данные успешно\n собраны!";
                canvas.drawText(text, 20,  100, mPaint);
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && count <= 10) {
                touchX = event.getX();
                touchY = event.getY();
                if (Math.sqrt(Math.pow(touchX - cx, 2.0) + Math.pow(touchY - cy, 2.0)) <= radius) {
                    c = Calendar.getInstance();
                    stop = c.get(Calendar.MILLISECOND) + c.get(Calendar.SECOND) * 1000 + c.get(Calendar.MINUTE) * 60000 + c.get(Calendar.HOUR) * 3600000;
                    startPress = stop;
                    dataPresenter.addTimeTouch();

                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (Math.sqrt(Math.pow(touchX - cx, 2.0) + Math.pow(touchY - cy, 2.0)) <= radius && count <= 10) {
                    c = Calendar.getInstance();
                    stopPress = c.get(Calendar.MILLISECOND) + c.get(Calendar.SECOND) * 1000 + c.get(Calendar.MINUTE) * 60000 + c.get(Calendar.HOUR) * 3600000;
                    dataPresenter.addTimePress();
                    count++;
                    invalidate();
                }
            }
            return true;
        }

    }
}

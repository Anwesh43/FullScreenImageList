package com.anwesome.ui.fullscreenimagelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 30/04/17.
 */
public class FullScreenImageView extends View {
    private int render = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float finalW,finalH,currX,currY,initW,initH,deg = 0;
    private FullScreenButton fullScreenButton = new FullScreenButton();
    private Bitmap bitmap;
    public FullScreenImageView(Context context, Bitmap bitmap,float finalH, float finalW) {
        super(context);
        this.finalH = finalH;
        this.finalW = finalW;
        this.bitmap = bitmap;
    }
    private void setXY(float x,float y) {
        super.setX(x);
        super.setY(y);
    }
    public void setInitXY(float x,float y){
        setXY(x,y);
        currX = x;
        currY = y;
    }
    public void update(float factor) {
        setXY(currX-currX*factor,currY-currY*factor);
        setScaleX((initW+(finalW-initH)*factor)/initW);
        setScaleY((initH+(finalH-initW)*factor)/initH);
        int bitmapW = (int)(initW+(finalH-initW)*factor);
        int bitmapH = (int)(initH+(finalW-initH)*factor);
        bitmap = Bitmap.createScaledBitmap(bitmap,bitmapW,bitmapH,true);
        deg = 90*factor;
        fullScreenButton.move(factor,finalW,finalH);
        postInvalidate();
    }

    public void onDraw(Canvas canvas) {
        if(render == 0) {
            initW = canvas.getWidth();
            initH = canvas.getHeight();
            fullScreenButton.setDimension(initW/2,initH/2,initW/8);
            bitmap = Bitmap.createScaledBitmap(bitmap,canvas.getWidth(),canvas.getHeight(),true);
        }
        float x = getX(),y = getY(),w = canvas.getWidth(),h = canvas.getHeight();
        canvas.save();
        canvas.translate(x+w/2,y+h/2);
        canvas.rotate(deg);
        canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,-bitmap.getHeight()/2,paint);
        fullScreenButton.draw(canvas,paint);
        canvas.restore();
        render++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            fullScreenButton.handleTap(event.getX(),event.getY());
        }
        return true;
    }
}

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
    private Bitmap bitmap,origBitmap;
    private AnimationHandler animationHandler;
    public FullScreenImageView(Context context, Bitmap bitmap,float finalH, float finalW) {
        super(context);
        this.finalH = finalH;
        this.finalW = finalW;
        this.bitmap = bitmap;
        this.origBitmap = bitmap;
        animationHandler = new AnimationHandler(this);
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
        int bitmapW = (int)(initW+(initH-initW)*factor);
        int bitmapH = (int)(initH+(initW-initH)*factor);
        bitmap = Bitmap.createScaledBitmap(origBitmap,bitmapW,bitmapH,true);
        deg = 90*factor;
        fullScreenButton.move(factor,bitmapW);
        setXY(currX*(1-factor)+(finalW/2-initW/2)*factor,currY*(1-factor)+(finalH/2-initH/2)*factor);
        setScaleX((initW+(finalW-initW)*factor)/(initW));
        setScaleY((initH+(finalH-initH)*factor)/(initH));
        postInvalidate();
    }

    public void onDraw(Canvas canvas) {
        if(render == 0) {
            initW = canvas.getWidth();
            initH = canvas.getHeight();
            fullScreenButton.setDimension(initW/2,initH/2,initW/8);
            bitmap = Bitmap.createScaledBitmap(bitmap,canvas.getWidth(),canvas.getHeight(),true);
            fullScreenButton.setOnTapListener(new FullScreenButton.OnTapListener() {
                @Override
                public void onTapToExpand() {
                    bringToFront();
                    animationHandler.start();
                }

                @Override
                public void onTapToShrink() {
                    animationHandler.end();
                }
            });
        }
        canvas.save();
        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.rotate(deg);
        canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,-bitmap.getHeight()/2,paint);
        canvas.restore();
        fullScreenButton.draw(canvas,paint);
        render++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            fullScreenButton.handleTap(event.getX(),event.getY());
        }
        return true;
    }
}

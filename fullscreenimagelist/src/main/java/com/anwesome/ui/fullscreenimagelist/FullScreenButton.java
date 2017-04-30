package com.anwesome.ui.fullscreenimagelist;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by anweshmishra on 30/04/17.
 */
public class FullScreenButton {
    private OnTapListener onTapListener;
    private float x,y,size,deg = 0,initX,initY;
    public void setDimension(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.initX = x;
        this.initY = y;
        this.size = size;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        for(int i=0;i<4;i++) {
            canvas.save();
            canvas.rotate(i*90);
            canvas.save();
            canvas.translate(-size/3,-size/3);
            canvas.rotate(deg);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(size/30);
            for(int j=0;j<2;j++) {
                canvas.save();
                canvas.translate(-size / 6, -size / 6);
                canvas.rotate(j*90);
                canvas.drawLine(0,0,size/6,0,paint);
                canvas.restore();
            }
            canvas.restore();
            canvas.restore();
        }
        canvas.restore();
    }
    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }
    public void move(float factor,float w,float h) {
        deg = 90*factor;
        x = initX+(w/2-initX)*factor;
        y = initY+(h/2-initY)*factor;
    }
    public int hashCode() {
        return (int)(x+y);
    }
    public boolean handleTap(float x,float y) {
        boolean condition = x>=this.x -size/2 && x<=this.x+size/2 && y>=this.y - size/2 && y<=this.y +size/2;
        if(condition && onTapListener != null) {
            onTapListener.onTap();
        }
        return condition;
    }
    public interface OnTapListener {
        void onTap();
    }
}

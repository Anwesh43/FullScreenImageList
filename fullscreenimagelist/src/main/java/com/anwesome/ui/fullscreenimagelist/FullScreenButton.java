package com.anwesome.ui.fullscreenimagelist;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by anweshmishra on 30/04/17.
 */
public class FullScreenButton {
    private float x,y,size,deg = 0,dir = 0;
    public void setDimension(float x,float y,float size) {
        this.x = x;
        this.y = y;
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
    public void move() {
        deg+=18*dir;
        if(deg>90 || deg<0) {
            dir = 0;
            deg = (deg>90)?90:0;
        }
    }
    public void startMoving() {
        dir = deg<=0 ? 1 : -1;
    }
    public int hashCode() {
        return (int)(x+y);
    }
}

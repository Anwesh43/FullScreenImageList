package com.anwesome.ui.fullscreenimagelist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 30/04/17.
 */
public class FullScreenImageList {
    private Activity activity;
    private RelativeLayout relativeLayout = null;
    private int w,h;
    private List<FullScreenImageView> fullScreenImageViews = new ArrayList<>();
    public FullScreenImageList(Activity activity) {
        this.activity = activity;
        initDimension();
    }
    private void initDimension() {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void addImage(Bitmap bitmap) {
        if(relativeLayout == null) {
            FullScreenImageView fullScreenImageView = new FullScreenImageView(activity,bitmap,h,w);
            fullScreenImageViews.add(fullScreenImageView);
        }
    }
    public void show() {
        if(relativeLayout == null) {
            relativeLayout = new RelativeLayout(activity);
            float x = 0,y = 0,viewH = h/6;
            for(FullScreenImageView fullScreenImageView:fullScreenImageViews) {
                fullScreenImageView.setInitXY(x,y);
                relativeLayout.addView(fullScreenImageView,new WindowManager.LayoutParams(w,(int)viewH));
                y+=viewH;
            }
            activity.setContentView(relativeLayout);
        }
    }
}

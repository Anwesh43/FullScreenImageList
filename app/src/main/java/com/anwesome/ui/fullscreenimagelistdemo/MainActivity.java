package com.anwesome.ui.fullscreenimagelistdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.fullscreenimagelist.FullScreenImageList;

public class MainActivity extends AppCompatActivity {
    private FullScreenImageList fullScreenImageList;
    private int images[] = {R.drawable.back1,R.drawable.back2};
    private Bitmap bitmaps[] = new Bitmap[images.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<images.length;i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(),images[i]);
        }
        fullScreenImageList = new FullScreenImageList(this);
        for(int i=0;i<images.length;i++) {
            fullScreenImageList.addImage(bitmaps[i]);
        }
        fullScreenImageList.show();



    }
}

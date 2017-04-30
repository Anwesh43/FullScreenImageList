package com.anwesome.ui.fullscreenimagelist;

import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 30/04/17.
 */
public class AnimationHandler implements ValueAnimator.AnimatorUpdateListener{
    private FullScreenImageView fullScreenImageView;
    private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
    {{
        startAnim.setDuration(500);
        startAnim.addUpdateListener(this);
        endAnim.setDuration(500);
        endAnim.addUpdateListener(this);
    }}
    public AnimationHandler(FullScreenImageView fullScreenImageView) {
        this.fullScreenImageView = fullScreenImageView;
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        fullScreenImageView.update((float)valueAnimator.getAnimatedValue());
    }
    public void start() {
        startAnim.start();
    }
    public void end() {
        endAnim.start();
    }
}

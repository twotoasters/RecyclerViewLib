package com.twotoasters.recycled.animator;

import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.twotoasters.anim.PendingItemAnimator;
import com.twotoasters.recycled.ReViewHolder;
import com.twotoasters.utils.DisplayUtils;

public class FromTopItemAnimator extends PendingItemAnimator<ReViewHolder> {
    public FromTopItemAnimator() {
        setMoveDuration(200);
        setRemoveDuration(500);
        setAddDuration(300);
    }

    @Override
    protected boolean prepHolderForAnimateRemove(ReViewHolder holder) {
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateRemoveImpl(ReViewHolder holder) {
        Point screen = DisplayUtils.getScreenDimensions(holder.itemView.getContext());
        int top = holder.itemView.getTop();
        return ViewCompat.animate(holder.itemView)
                .rotation(80)
                .translationY(screen.y - top)
                .setInterpolator(new AccelerateInterpolator());
    }

    @Override
    protected void onRemoveCanceled(ReViewHolder holder) {
        ViewCompat.setTranslationY(holder.itemView, 0);
    }

    @Override
    protected boolean prepHolderForAnimateAdd(ReViewHolder holder) {
        int bottom = holder.itemView.getBottom();
        ViewCompat.setTranslationY(holder.itemView, - bottom);
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateAddImpl(ReViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .translationY(0)
                .setInterpolator(new OvershootInterpolator());
    }

    @Override
    protected void onAddCanceled(ReViewHolder holder) {
        ViewCompat.setTranslationY(holder.itemView, 0);
    }
}

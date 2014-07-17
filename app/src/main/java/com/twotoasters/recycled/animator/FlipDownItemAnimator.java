package com.twotoasters.recycled.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.twotoasters.recycled.ReViewHolder;

public class FlipDownItemAnimator extends PendingItemAnimator<ReViewHolder> {
    public FlipDownItemAnimator() {
        setAddDuration(1000);
        setRemoveDuration(500);
    }

    @Override
    protected boolean prepHolderForAnimateRemove(ReViewHolder holder) {
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateRemoveImpl(ReViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .rotationY(90)
                .translationX( - (holder.itemView.getMeasuredWidth() / 4))
                .scaleX(0.5F)
                .scaleY(0.5F)
                .setInterpolator(new AccelerateInterpolator());
    }

    @Override
    protected void onRemoveCanceled(ReViewHolder holder) {
        ViewCompat.setRotationY(holder.itemView, 0);
        ViewCompat.setTranslationX(holder.itemView, 0);
        ViewCompat.setScaleX(holder.itemView, 1);
        ViewCompat.setScaleY(holder.itemView, 1);
    }

    @Override
    protected boolean prepHolderForAnimateAdd(ReViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, - (holder.itemView.getMeasuredWidth() / 2));
        ViewCompat.setRotationY(holder.itemView, -90);
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateAddImpl(ReViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .rotationY(0)
                .translationX(0)
                .setInterpolator(new BounceInterpolator());
    }

    @Override
    protected void onAddCanceled(ReViewHolder holder) {
        ViewCompat.setRotationY(holder.itemView, 0);
        ViewCompat.setTranslationX(holder.itemView, 0);
    }
}

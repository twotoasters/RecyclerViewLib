package com.twotoasters.recycled.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;

import com.twotoasters.anim.PendingItemAnimator;
import com.twotoasters.recycled.ReViewHolder;

public class GarageDoorItemAnimator extends PendingItemAnimator<ReViewHolder> {
    public GarageDoorItemAnimator() {
        setAddDuration(300);
        setRemoveDuration(300);
    }

    @Override
    protected boolean prepHolderForAnimateRemove(ReViewHolder holder) {
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateRemoveImpl(ReViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .rotationX(90)
                .translationY( - (holder.itemView.getMeasuredHeight() / 2));
    }

    @Override
    protected void onRemoveCanceled(ReViewHolder holder) {
        ViewCompat.setRotationX(holder.itemView, 0);
        ViewCompat.setTranslationY(holder.itemView, 0);
    }

    @Override
    protected boolean prepHolderForAnimateAdd(ReViewHolder holder) {
        ViewCompat.setRotationX(holder.itemView, 90);
        ViewCompat.setTranslationY(holder.itemView, - (holder.itemView.getMeasuredHeight() / 2));
        return true;
    }

    @Override
    protected ViewPropertyAnimatorCompat animateAddImpl(ReViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .rotationX(0)
                .translationY(0);
    }

    @Override
    protected void onAddCanceled(ReViewHolder holder) {
        ViewCompat.setRotationX(holder.itemView, 0);
        ViewCompat.setTranslationY(holder.itemView, 0);
    }
}

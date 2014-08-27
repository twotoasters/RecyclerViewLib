package com.twotoasters.recycled.factory;

import com.twotoasters.android.support.v7.widget.DefaultItemAnimator;
import com.twotoasters.anim.PendingItemAnimator;
import com.twotoasters.recycled.animator.FlipDownItemAnimator;
import com.twotoasters.recycled.animator.FlyItemAnimator;
import com.twotoasters.recycled.animator.FromTopItemAnimator;
import com.twotoasters.recycled.animator.GarageDoorItemAnimator;

public final class ItemAnimationFactory {
    public static final Class<? extends PendingItemAnimator>[] ANIMATOR_ARRAY = new Class[] { DefaultItemAnimator.class
            , FlipDownItemAnimator.class, FlyItemAnimator.class, FromTopItemAnimator.class, GarageDoorItemAnimator.class };

    private ItemAnimationFactory() { }

    public static PendingItemAnimator getAnimator(int index) {
        return getAnimator(ANIMATOR_ARRAY[index]);
    }

    public static <T extends PendingItemAnimator>  T getAnimator(Class<T> animatorClass) {
        try {
            return animatorClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

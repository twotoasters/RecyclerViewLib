package com.twotoasters.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public final class DisplayUtils {
    private DisplayUtils() { }

    public static Point getScreenDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        Point point = new Point();
        point.set(dm.widthPixels, dm.heightPixels);
        return point;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static int dpToPx(Context context, int dp) {
        final float scale = getDisplayMetrics(context).density;
        return (int) (dp * scale + 0.5f);
    }

    public static float pxToDp(Context context, int px) {
        float pixel = px;
        return (pixel / getDisplayMetrics(context).density);
    }
}

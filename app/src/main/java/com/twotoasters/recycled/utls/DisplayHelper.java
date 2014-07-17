package com.twotoasters.recycled.utls;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public final class DisplayHelper {
    private DisplayHelper() { }

    public static Point getScreenDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }
}

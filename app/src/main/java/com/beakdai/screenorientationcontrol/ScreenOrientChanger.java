package com.beakdai.screenorientationcontrol;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;

public class ScreenOrientChanger {
    private final View mView;
    private final WindowManager mManager;
    private int orient;

    public ScreenOrientChanger(Context pContext, int pOrient) {
        mManager = (WindowManager) pContext.getSystemService(Context.WINDOW_SERVICE);
        mView = new View(pContext);
        orient = pOrient;
    }

    public void start() {
        WindowManager.LayoutParams params = generateLayout();
        mManager.addView(mView, params);
        mView.setVisibility(View.VISIBLE);
    }

    public void stop() {
        mManager.removeView(mView);
    }

    private WindowManager.LayoutParams generateLayout() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.width = 0;
        params.height = 0;
        params.format = PixelFormat.TRANSPARENT;
        params.alpha = 0f;
        params.screenOrientation = orient;
        return params;
    }

}

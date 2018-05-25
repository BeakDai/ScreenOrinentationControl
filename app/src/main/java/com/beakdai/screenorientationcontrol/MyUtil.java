package com.beakdai.screenorientationcontrol;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MyUtil {

    /**
     * @param pContext context
     * @param orient   {@link ActivityInfo#SCREEN_ORIENTATION_LANDSCAPE}
     */
    public void changeOrient(Context pContext, int orient) {
        LinearLayout orientationChanger = new LinearLayout(pContext);
        // Using TYPE_SYSTEM_OVERLAY is crucial to make your window appear on top
        // You'll need the permission android.permission.SYSTEM_ALERT_WINDOW
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, 0, PixelFormat.RGBA_8888);
        params.screenOrientation = orient;
        WindowManager wm = (WindowManager) pContext.getSystemService(Service.WINDOW_SERVICE);
        wm.addView(orientationChanger, params);
        orientationChanger.setVisibility(View.VISIBLE);
    }

    public int getRequestOrientation(Activity pActivity) {
        return pActivity.getRequestedOrientation();
    }

    public void setRequestOrientation(Activity pActivity, int orient) {
        pActivity.setRequestedOrientation(orient);
    }

    /**
     * @param pContext context
     * @param rotation {@link Surface#ROTATION_0}|{@link Surface#ROTATION_90}
     *                 |{@link Surface#ROTATION_180}|{@link Surface#ROTATION_270}
     */
    public void setSettingsOrient(Context pContext, int rotation) {
        Settings.System.putInt(pContext.getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0);
        Settings.System.putInt(pContext.getContentResolver(),
                Settings.System.USER_ROTATION, rotation);
    }

    public boolean getOrient(Activity pActivity) {
        return (pActivity.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE);
    }

}

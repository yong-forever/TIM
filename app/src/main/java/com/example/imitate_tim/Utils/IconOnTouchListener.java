package com.example.imitate_tim.Utils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Create by 2020/2/8.
 * 类描述:
 */
public class IconOnTouchListener implements View.OnTouchListener {
    private static final String TAG = "IconOnTouchListener";
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "onTouch: "+event.getAction());
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            v.setAlpha(0.5f);
        }else{
            v.setAlpha(1);
        }
        return false;
    }
}

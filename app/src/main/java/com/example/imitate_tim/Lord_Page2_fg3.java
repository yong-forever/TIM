package com.example.imitate_tim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;

import com.example.imitate_tim.Utils.IconOnTouchListener;

/**
 * Create by 2020/2/7.
 * 类描述:
 */
public class Lord_Page2_fg3 extends Fragment {
    private static final String TAG = "Lord_Page2_fg3";
    private ImageView ivMyComputer;
    private ImageView ivAdd;
    private ImageView ivQRcode;
    private TextView tvUsername;
    private TextView tvUserid;
    private ImageView ivSetting;
    private ImageView ivHead;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: sdas");
        View view = inflater.inflate(R.layout.activity_lord_page2_fg3, null);
        ivQRcode = (ImageView) view.findViewById(R.id.iv_QRcode);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        tvUserid = (TextView) view.findViewById(R.id.tv_userid);
        ivSetting = (ImageView) view.findViewById(R.id.iv_setting);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);

        ivQRcode.setOnTouchListener(new IconOnTouchListener());
        ivSetting.setOnTouchListener(new IconOnTouchListener());
        return view;
    }
}

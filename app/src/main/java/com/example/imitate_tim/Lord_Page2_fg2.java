package com.example.imitate_tim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.imitate_tim.Utils.IconOnTouchListener;
import com.example.imitate_tim.Utils.RefreshListView;
import com.example.imitate_tim.Utils.ScreenUtils;
import com.example.imitate_tim.widget.SlidingDeleteView;

/**
 * Create by 2020/2/7.
 * 类描述:
 */
public class Lord_Page2_fg2 extends Fragment {
    private static final String TAG = "Lord_Page2_fg2";
    private ImageView ivMyComputer;
    private ImageView ivAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: sdas");
        View view = inflater.inflate(R.layout.activity_lord_page2_fg2, null);
        ivMyComputer = (ImageView) view.findViewById(R.id.iv_myComputer);
        ivAdd = (ImageView) view.findViewById(R.id.iv_add);

        ivMyComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ivMyComputer.setOnTouchListener(new IconOnTouchListener());
        ivAdd.setOnTouchListener(new IconOnTouchListener());
        return view;
    }
}

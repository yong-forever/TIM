package com.example.imitate_tim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Create by 2020/2/7.
 * 类描述:
 */
public class Lord_Page2 extends Fragment implements View.OnClickListener {
    private static final String TAG = "Lord_Page2";
    private FrameLayout flContent;
    private ImageView ivPage1;
    private ImageView ivPage2;
    private ImageView ivPage3;
    private FragmentManager fragmentManager;
    private Lord_Page2_fg1 fg1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lord_page2, null);
        fragmentManager=getChildFragmentManager();
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        ivPage1 = (ImageView) view.findViewById(R.id.iv_page1);
        ivPage2 = (ImageView) view.findViewById(R.id.iv_page2);
        ivPage3 = (ImageView) view.findViewById(R.id.iv_page3);
        ivPage1.setOnClickListener(this);
        ivPage2.setOnClickListener(this);
        ivPage3.setOnClickListener(this);
        ivPage1.performClick();
        return view;
    }


    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: "+fragmentManager.getFragments().size());
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.iv_page1:
                if(fg1 == null){
                    fg1 = new Lord_Page2_fg1();
                    fTransaction.add(R.id.fl_content,fg1);
                    Log.i(TAG, "onClick: ");
                }else{
                    Log.i(TAG, "onClick: 2");
                    fTransaction.show(fg1);
                }
                break;
            case R.id.iv_page2:
                break;
            case R.id.iv_page3:
                break;
        }
        fTransaction.commit();
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        //if(fg2 != null)fragmentTransaction.hide(fg2);
        //if(fg3 != null)fragmentTransaction.hide(fg3);
    }
}

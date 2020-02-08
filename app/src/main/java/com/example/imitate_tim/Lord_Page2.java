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
    private Lord_Page2_fg2 fg2;
    private Lord_Page2_fg3 fg3;

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
        setIvGray();
        switch (v.getId()){
            case R.id.iv_page1:
                if(fg1 == null){
                    fg1 = new Lord_Page2_fg1();
                    fTransaction.add(R.id.fl_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                ivPage1.setImageResource(R.drawable.pvt);
                break;
            case R.id.iv_page2:
                if(fg2 == null){
                    fg2 = new Lord_Page2_fg2();
                    fTransaction.add(R.id.fl_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                ivPage2.setImageResource(R.drawable.pvv);
                break;
            case R.id.iv_page3:
                if(fg3 == null){
                    fg3 = new Lord_Page2_fg3();
                    fTransaction.add(R.id.fl_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                ivPage3.setImageResource(R.drawable.pvr);
                break;
        }
        fTransaction.commit();
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }
    private void setIvGray(){
        ivPage1.setImageResource(R.drawable.pvs);
        ivPage2.setImageResource(R.drawable.pvu);
        ivPage3.setImageResource(R.drawable.pvq);
    }
}

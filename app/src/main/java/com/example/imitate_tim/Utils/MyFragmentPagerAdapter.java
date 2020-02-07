package com.example.imitate_tim.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.imitate_tim.Lord_Page1;
import com.example.imitate_tim.Lord_Page2;

/**
 * Create by 2020/2/7.
 * 类描述:
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private Lord_Page1 page1;
    private Lord_Page2 page2;
    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        page1=new Lord_Page1();
        page2=new Lord_Page2();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:fragment=page1;
                break;
            case 1:fragment=page2;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

package com.example.imitate_tim;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.imitate_tim.Utils.MyFragmentPagerAdapter;

public class Lord_Activity extends AppCompatActivity {
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager vpLord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord);
        initView();
    }

    private void initView() {
        vpLord = (ViewPager) findViewById(R.id.vp_lord);
        fragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(), PagerAdapter.POSITION_NONE);
        vpLord.setAdapter(fragmentPagerAdapter);
        vpLord.setCurrentItem(1);
    }
}

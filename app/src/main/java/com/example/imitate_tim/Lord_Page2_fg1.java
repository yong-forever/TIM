package com.example.imitate_tim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class Lord_Page2_fg1 extends Fragment {
    private static final String TAG = "Lord_Page2_fg1";
    private ImageView ivContact;
    private ImageView ivAdd;
    private RefreshListView rlvContact;
    private RLV_Adapter rlvAdapter;

    public Lord_Page2_fg1() {
        Log.i(TAG, "Lord_Page2_fg1: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: sdas");
        View view = inflater.inflate(R.layout.activity_lord_page2_fg1, null);
        ivContact = (ImageView) view.findViewById(R.id.iv_contact);
        ivAdd = (ImageView) view.findViewById(R.id.iv_add);
        rlvContact = (RefreshListView) view.findViewById(R.id.rlv_contact);

        ivContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lord_Activity.setCurrentItem(0);
            }
        });
        ivContact.setOnTouchListener(new IconOnTouchListener());
        ivAdd.setOnTouchListener(new IconOnTouchListener());

        rlvAdapter = new RLV_Adapter();
        rlvContact.setAdapter(rlvAdapter);
        rlvContact.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //myAdapter.notifyDataSetChanged();
                                rlvContact.onRefreshConmm();
                            }
                        });
                    }
                }.start();
            }
        });
        return view;
    }
    class RLV_Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.activity_lord_2_1_item,parent,false);
            }
            SlidingDeleteView Myslidingview = (SlidingDeleteView) convertView.findViewById(R.id.Myslidingview);
            ConstraintLayout layContainer = (ConstraintLayout) convertView.findViewById(R.id.lay_container);
            ImageView ivHead = (ImageView) convertView.findViewById(R.id.iv_head);
            TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tvInfo = (TextView) convertView.findViewById(R.id.tv_info);
            TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            TextView tvCount = (TextView) convertView.findViewById(R.id.tv_count);
            LinearLayout laySliding = (LinearLayout) convertView.findViewById(R.id.lay_sliding);
            TextView UseritemSave = (TextView) convertView.findViewById(R.id.Useritem_save);
            TextView UseritemDelete = (TextView) convertView.findViewById(R.id.Useritem_delete);

            layContainer.getLayoutParams().width = ScreenUtils.getScreenWidth(getContext());
            Myslidingview.setEnable(true);


            return convertView;
        }
    }
}

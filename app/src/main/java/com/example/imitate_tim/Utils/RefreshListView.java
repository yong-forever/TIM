package com.example.imitate_tim.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imitate_tim.R;

public class RefreshListView extends ListView {
    private static final String TAG = "RefreshListView";
    private View mHeaderView;
    private int mHeaderViewheight;
    private float downY=0;
    private float moveY=0;
    private static final int pull_refresh=0;//下拉刷新
    private static final int release_refresh=1;//释放刷新
    private static final int refreshing=2;//刷新中
    private int cMode=pull_refresh;
    private RotateAnimation upAnimation;
    private RotateAnimation downAnimation;
    private ImageView iv_load;
    private LinearLayout ll_find;
    private int pandingTop;
    private OnRefreshListener onRefreshListener;
    private LinearLayout ll_refresh;

    public RefreshListView(Context context) {
        super(context);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化布局
     */
    private void init(){
        initTopView();
        
        initAnime();
    }

    private void initAnime() {
        //向上转
        upAnimation = new RotateAnimation(0,-180f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        upAnimation.setDuration(3000);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);

        //向下转
        downAnimation = new RotateAnimation(-180,-360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        downAnimation.setDuration(3000);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);
    }

    /**
     * 初始化头布局
     */
    private void initTopView() {
        mHeaderView=inflate(getContext(),R.layout.acitivty_listview_topview,null);

        ll_refresh = (LinearLayout) mHeaderView.findViewById(R.id.ll_refresh);
        iv_load = (ImageView) mHeaderView.findViewById(R.id.iv_load);
        ll_find = (LinearLayout) mHeaderView.findViewById(R.id.ll_find);

        mHeaderView.measure(0,0);
        mHeaderViewheight=ll_refresh.getMeasuredHeight();
        Log.i(TAG, "initTopView: "+mHeaderViewheight);
        mHeaderView.setPadding(0,-mHeaderViewheight,0,0);

        addHeaderView(mHeaderView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //判断滑动距离,设置Padding
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY=ev.getY();

                //如果正在刷新中，执行父类方法
                if(cMode==refreshing){
                    return super.onTouchEvent(ev);
                }

                float Offset=moveY-downY;//偏移量
                if(Offset>0&&getFirstVisiblePosition()==0){
                    pandingTop = (int) (-mHeaderViewheight+Offset);
                    mHeaderView.setPadding(0, pandingTop,0,0);
                    Log.i(TAG, "onTouchEvent: "+pandingTop);
                    if(pandingTop >=0&&cMode != release_refresh){
                        //完全显示头布局
                        Log.i(TAG, "onTouchEvent: ??");
                        cMode = release_refresh;
                        updatemHeaderView();
                    }else if(pandingTop <0&&cMode != pull_refresh){
                        cMode = pull_refresh;
                        Log.i(TAG, "onTouchEvent: ?????");
                        updatemHeaderView();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(cMode == pull_refresh){
                    mHeaderView.setPadding(0, -mHeaderViewheight,0,0);
                }else if(cMode == release_refresh){
                    mHeaderView.setPadding(0, 0,0,0);
                    cMode=refreshing;
                    updatemHeaderView();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 更新头布局
     */
    private void updatemHeaderView() {
        switch (cMode){
            case pull_refresh:
                /*imgArrow.setVisibility(View.VISIBLE);
                imgArrow.startAnimation(downAnimation);
                tvTopview.setText("下拉刷新");
                pbTopview.setVisibility(View.INVISIBLE);*/
                break;
            case release_refresh:
                /*imgArrow.setVisibility(View.VISIBLE);
                imgArrow.startAnimation(upAnimation);
                tvTopview.setText("释放刷新");
                pbTopview.setVisibility(View.INVISIBLE);*/
                break;
            case refreshing:
                /*imgArrow.clearAnimation();
                imgArrow.setVisibility(View.INVISIBLE);
                tvTopview.setText("正在刷新...");
                pbTopview.setVisibility(View.VISIBLE);*/
                if(onRefreshListener!=null){
                    onRefreshListener.OnRefresh();
                }
                break;
        }
    }

    /**
     *
     * 刷新结束
     */
    public void onRefreshConmm() {
        cMode=pull_refresh;
        mHeaderView.setPadding(0, -mHeaderViewheight,0,0);
        /*tvTopview.setText("下拉刷新");
        imgArrow.setVisibility(View.VISIBLE);
        pbTopview.setVisibility(View.INVISIBLE);*/
    }

    public interface OnRefreshListener{
        void OnRefresh();
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener){
        this.onRefreshListener=onRefreshListener;
    }
}

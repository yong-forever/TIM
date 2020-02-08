package com.example.imitate_tim.Utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.imitate_tim.R;

public class RefreshListView extends ListView implements AbsListView.OnScrollListener {
    private static final String TAG = "RefreshListView";
    private View mHeaderView;
    private int mHeaderViewheight;
    private float downY=0;
    private float moveY=0;
    private static final int pull_refresh=0;//下拉刷新
    private static final int release_refresh=1;//释放刷新
    private static final int refreshing=2;//刷新中
    private static final int push_refresh=3;//上拉
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

        setOnScrollListener(this);
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
        float Offset=0;
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

                Offset=moveY-downY;//偏移量
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
                if(Offset<0){
                    pandingTop = (int) (-mHeaderViewheight+Offset);
                    //mHeaderView.setPadding(0, pandingTop,0,0);
                    cMode=push_refresh;
                    setPadding(0,(int)Offset,0,0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(cMode == pull_refresh){
                    mHeaderView.setPadding(0, -mHeaderViewheight,0,0);
                }else if(cMode == release_refresh){
                    mHeaderView.setPadding(0, 0,0,0);
                    cMode=refreshing;
                    updatemHeaderView();
                }else if(cMode==push_refresh){
                    ValueAnimator mAnimator = ValueAnimator.ofInt((int)Offset, 0);
                    mAnimator.setInterpolator(new DecelerateInterpolator(2.0f));//加速插值器
                    mAnimator.setDuration(1000);
                    mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int curValue = (int) animation.getAnimatedValue();
                            setPadding(0, curValue, 0, 0);
                        }
                    });
                    mAnimator.start();
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
        //mHeaderView.setPadding(0, -mHeaderViewheight,0,0);
        ValueAnimator mAnimator = ValueAnimator.ofInt(0, -mHeaderViewheight);
        mAnimator.setInterpolator(new DecelerateInterpolator(2.0f));//加速插值器
        mAnimator.setDuration(500);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                mHeaderView.setPadding(0, curValue, 0, 0);
            }
        });
        mAnimator.start();

        /*tvTopview.setText("下拉刷新");
        imgArrow.setVisibility(View.VISIBLE);
        pbTopview.setVisibility(View.INVISIBLE);*/
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //状态更新的时候
    }
    //public static int SCROLL_STATE_IDLE = 0; //空闲
    //public static int SCROLL_STATE_TOUCH_SCROLL = 1; //触摸滑动
    //public static int SCROLL_STATE_FLING = 2; //惯性滑动
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //滑动过程中
    }

    public interface OnRefreshListener{
        void OnRefresh();
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener){
        this.onRefreshListener=onRefreshListener;
    }
}

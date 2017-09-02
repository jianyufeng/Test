package com.fl.test.utils.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.fl.test.R;

/**
 * Created by Administrator on 2016/3/29.
 * SwipeRefreshLayout自定义带有上拉功能的，
 */
public class PullSwipeRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    private int touchSlop;//滚动的标准最小值
    private float downY;
    private float lastY;
    private boolean isUpPull, isLoading, isToBottom;
    private ListView lv;
    private OnLoadListener loadListener;
    private View loadingView;


    public PullSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public PullSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取滚动值
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //加载上拉时的底部视图
        loadingView = LayoutInflater.from(context).inflate(R.layout.footer_loading, null);
    }

    /**
     * {@inheritDoc}
     *
     * @param ev
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                isUpPull = (downY - lastY) >= touchSlop;
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (lv == null) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i) instanceof ListView) {
                    lv = (ListView) getChildAt(i);
                    lv.setOnScrollListener(this);
                    return;
                }
            }
        }

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isToBottom && isUpPull && !isLoading && scrollState == SCROLL_STATE_IDLE) {
            //加载下页数据
            if (loadListener != null) {
                setLoading(true);
                loadListener.onLoad();
            }
        }
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading) {
            lv.addFooterView(loadingView);//开始加载
        } else {
            lv.removeFooterView(loadingView);//加载完成
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isToBottom = true;
        } else {
            isToBottom = false;
        }
    }

    //多外提供加载方法 使用接口
    public void setOnLoadListener(OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public interface OnLoadListener {
        void onLoad();
    }
}

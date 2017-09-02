package com.fl.test.utils.baseActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.fl.test.R;
import com.fl.test.utils.baseApplication.BaseApplication;

/**
 * Created by Administrator on 2016/4/5.
 */
public abstract class BaseActivity extends Activity {
    protected Resources resources;
    protected BaseApplication application;
    protected Context mContext;
    protected static final String TAG = BaseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        resources = getApplicationContext().getResources();
        application = (BaseApplication) getApplication();
        mContext = getApplicationContext();
        findById();
        setListener();
        logic();
        application.addActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application.removeActivity(this);
    }

    /**
     * 获取全局的Context
     *
     * @return {@link #mContext = this.getApplicationContext();}
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 布局文件ID
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化组件
     */
    protected abstract void findById();

    //setListener
    protected abstract void setListener();

    //Logic
    protected abstract void logic();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent();
        startActivity(cls, null);

    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }
    /**
     * 带有右进  退出保持的动画的退出
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }
    /**
     * 默认退出
     */
    public void defaultFinish() {
        super.finish();
    }
    /**
     * 长时间显示Toast提示(来自String)
     *
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();

    }

    /**
     * 短暂显示Toast提示(来自String)
     *
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}

package com.fl.test.utils.baseApplication;

import android.app.Activity;
import android.app.Application;

import com.fl.test.utils.baseCrashHandler.BaseCrashHandler;

import java.util.Stack;

/**
 * Created by Administrator on 2016/4/1.
 */
public class BaseApplication extends Application {
    /**
     * activity栈保存
     */
    public Stack<Activity> activityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // activity管理
        activityStack = new Stack<>();
        // 异常处理
        BaseCrashHandler handler = BaseCrashHandler.getInstance();
        handler.init(this);
        // 程序异常关闭1s之后重新启动
//        new RebootThreadExceptionHandler(getBaseContext());//????????????????????????????

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    // 在内存低时,发送广播可以释放一些内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void addActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(curAT);
    }

    public void removeActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(curAT);
    }
    //获取最后一个Activity
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //返回栈内Activity的总数
    public int howManyActivities() {
        return activityStack.size();
    }

    //关闭所有Activity
    public void finishAllActivities() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}

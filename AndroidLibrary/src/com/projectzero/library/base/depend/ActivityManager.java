package com.projectzero.library.base.depend;

import com.projectzero.library.base.BaseActivity;

import java.util.Stack;

public class ActivityManager {
    private Stack<BaseActivity>        activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
        activityStack = new Stack<BaseActivity>();
    }

    public synchronized static ActivityManager getInstance() {
        if (null == instance)
            instance = new ActivityManager();
        return instance;
    }

    /**
     * 添加Activity到堆栈中
     * 
     * @param activity
     * @return
     */
    public boolean addActivity(BaseActivity activity) {
        if (null == activity)
            return false;
        return activityStack.add(activity);
    }

    /**
     * 获取当前activity
     * 
     * @return
     */
    public BaseActivity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 查询栈内是否有指定Activity
     *
     * @return boolean
     */
    public boolean findActivityForCls(Class<? extends BaseActivity> cls) {
        int stackSize = activityStack.size();
        for (int index = stackSize - 1; index > -1; index--) {
            BaseActivity activity = activityStack.get(index);
            if (null != activity && activity.getClass().equals(cls)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 结束指定activity
     * 
     * @param activity
     */
    public void finishActivity(BaseActivity activity) {
        if (null != activity) {
            activity.finish();
            activityStack.removeElement(activity);
            activity = null;
        }
    }

    /**
     * 结束指定的一组activity
     * 
     * @param activities
     */
    public void finishActivity(Class<? extends BaseActivity>[] activities) {
        if (null == activities || 0 == activities.length)
            return;
        for (Class<? extends BaseActivity> activity : activities) {
            finishActivity(activity);
        }
    }

    /**
     * 结束指定类型的activity
     * 
     * @param cls
     */
    public void finishActivity(Class<? extends BaseActivity> cls) {
        int stackSize = activityStack.size();
        for (int index = stackSize - 1; index > -1; index--) {
            BaseActivity activity = activityStack.get(index);
            if (null != activity && activity.getClass().equals(cls)) {
                activity.finish();
                activityStack.remove(index);
                activity = null;
            }

        }
    }

    /**
     * 结束所有的activity
     */
    public void finishAllActivity() {
        int acSize = activityStack.size();
        for (int i = 0; i < acSize; i++) {
            BaseActivity activity = activityStack.get(i);
            if (null != activity) {
                activity.finish();
                activity = null;
            }
        }
        activityStack.clear();
    }
}

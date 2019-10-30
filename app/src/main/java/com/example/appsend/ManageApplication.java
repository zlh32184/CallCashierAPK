package com.example.appsend;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ManageApplication extends Application {
    private HashMap<String, Boolean> mapActivity = new HashMap<String, Boolean>();// 用于存储activity对应的激活状态
    private static ManageApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    /**
     * 把Activity和状态放到List中管理
     *
     * @param activity
     * @param isAlive
     */
    public void addActivityStatus(Activity activity, boolean isAlive) {

        if (mapActivity.containsKey(activity.getClass().getName())) {
            mapActivity.remove(activity.getClass().getName());
            mapActivity.put(activity.getClass().getName(), isAlive);
        } else {
            mapActivity.put(activity.getClass().getName(), isAlive);
        }

    }

    /**
     * 判断是否有Activity是活动状态
     *
     * @return
     */
    public boolean isAllActivityAlive() {
        boolean res = false;
        Iterator iter = mapActivity.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            boolean value = (Boolean) entry.getValue();
            if (value) {
                return true;
            }
        }

        return res;
    }

    public static ManageApplication getInstance() {
        return application;
    }
}

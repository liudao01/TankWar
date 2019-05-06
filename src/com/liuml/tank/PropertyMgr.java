package com.liuml.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author liuml
 * @explain 读取配置文件
 * @time 2019/5/6 10:48
 */
public class PropertyMgr {

    private Properties mProperties;

    private PropertyMgr() {
        initData();
    }

    private void initData() {
        if (mProperties == null) {
            mProperties = new Properties();
        }

        try {
            mProperties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PropertyHolder {
        private static final PropertyMgr mPropertyMgr = new PropertyMgr();
    }


    public static PropertyMgr getInstance() {
        return PropertyHolder.mPropertyMgr;
    }

    public Object get(String key) {
        if (mProperties != null) {
            return mProperties.get(key);
        }
        return null;
    }


    public int getInt(String key) {
        if (mProperties != null) {
            return Integer.parseInt((String)mProperties.get(key));
        }
        return 0;
    }


}

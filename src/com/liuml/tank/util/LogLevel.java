package com.liuml.tank.util;

/**
 * desc
 * Created by liuml.
 * Created time 2019/4/25.
 */
public enum LogLevel {
    ALL("[]", 0), INFO("[INFO]", 1), WARN("[WARN]", 2), ERROR("[ERROR]", 3) ,DEBUG("[DEBUG]", 1),;

    private String name;
    private int level;

    LogLevel(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
    /**
     * <pre>
     * 判断是否允许打印
     * </pre>
     * @author ygr
     * @date 2018年4月9日 上午9:36:47
     * @param minLevel 最低日志级别
     * @return
     */
    public boolean isAllow(LogLevel minLevel){
        if(level >= minLevel.getLevel()){
            return true;
        }

        return false;
    }


}

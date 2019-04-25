package com.liuml.tank.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc
 * Created by liuml.
 * Created time 2019/4/25.
 */
public class LogUtils {
    /** 默认可以打印 */
    private static boolean enable = true;
    /** 默认打印所有级别日志 */
    private static LogLevel minLevel = LogLevel.ALL;
    /** 默认保存到磁盘 */
    private static boolean persistent = true;
    /** 日期显示格式 */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static Date date = null;

    /**
     * <pre>
     * 设置是否开启打印
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:54:46
     * @param enable
     */
    public static void setEnable(boolean enable) {
        LogUtils.enable = enable;
    }

    /**
     * <pre>
     * 设置是否持久化
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午12:47:50
     * @param persistent
     */
    public static void setPersistent(boolean persistent) {
        LogUtils.persistent = persistent;
    }

    /**
     * <pre>
     * 设置日志打印级别
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:42:09
     * @param level
     */
    public static void setLogLevel(LogLevel level) {
        LogUtils.minLevel = level;
    }

    /**
     * <pre>
     * 打印消息级别日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:42:59
     * @param msg
     *            待打印消息
     */
    public static void info(String msg) {
        finalPrint(LogLevel.INFO, msg);
    }

    /**
     * <pre>
     * 打印消息级别日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午3:54:56
     * @param msg
     *            待打印消息
     * @param tag
     *            特殊标记
     */
    public static void info(String msg, String tag) {
        finalPrint(LogLevel.INFO, msg, tag);
    }

    /**
     * debug 信息
     * @param msg
     * @param tag
     */
    public static void debug(String msg, String tag) {
        finalPrint(LogLevel.DEBUG, msg, tag);
    }

    /**
     * debug 信息
     * @param msg
     */
    public static void debug(String msg) {
        finalPrint(LogLevel.DEBUG, msg);
    }
    /**
     * <pre>
     * 打印警告级别日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:42:59
     * @param msg
     *            待打印消息
     */
    public static void warn(String msg) {
        finalPrint(LogLevel.WARN, msg);
    }

    /**
     * <pre>
     * 打印警告级别日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午3:54:56
     * @param msg
     *            待打印消息
     * @param tag
     *            特殊标记
     */
    public static void warn(String msg, String tag) {
        finalPrint(LogLevel.WARN, msg, tag);
    }

    /**
     * <pre>
     * 打印错误级别日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:42:59
     * @param msg
     *            待打印消息
     */
    public static void error(String msg) {
        finalPrint(LogLevel.ERROR, msg);
    }

    /**
     * <pre>
     * 打印错误级别日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午3:54:56
     * @param msg
     *            待打印消息
     * @param tag
     *            特殊标记
     */
    public static void error(String msg, String tag) {
        finalPrint(LogLevel.ERROR, msg, tag);
    }

    /**
     * <pre>
     * 最终打印日志
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:50:21
     * @param logLevel
     *            日志级别
     * @param msg
     *            待打印消息
     */
    private static void finalPrint(LogLevel logLevel, String msg) {
        finalPrint(logLevel, msg, null);
    }

    /**
     * <pre>
     * 多一个tag标记
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午3:52:07
     * @param logLevel
     * @param msg
     * @param tag
     */
    private static void finalPrint(LogLevel logLevel, String msg, String tag) {
        // 不允许打印
        if (!enable) {
            return;
        }
        // 允许打印该级别日志
        if (logLevel.isAllow(minLevel)) {

            StringBuilder builder = new StringBuilder();
            builder.append(formatCurrentTime()).append(" ");
            // 属性信息
            StackTraceElement traceElement = getStackTraceElement();
            if (traceElement != null) {
                builder.append("(").append(traceElement.getFileName()).append(":").append(traceElement.getLineNumber())
                        .append(")").append("  ");
            }
            // 有特殊标记
            if (tag != null && tag.length() != 0) {
                builder.append("[").append(tag).append("]").append("  ");
            }
            // 级别和最终消息
            builder.append(logLevel.getName()).append(" ").append(msg);

            String message = builder.toString();

            System.out.println(message);
            // 持久化
            if (persistent) {
                //PersistentUtils.save(message);
            }
        }
    }

    /**
     * <pre>
     * 获取当前时间
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 上午9:49:00
     * @return
     */
    private static String formatCurrentTime() {
        date = new Date();
        return sdf.format(date);
    }

    /**
     * <pre>
     * 获取打印日志的所属文件、所在行数等信息
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午3:41:10
     * @return
     */
    private static StackTraceElement getStackTraceElement() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        int methodCount = 1;
        int stackOffset = getStackOffset(trace);

        if (methodCount + stackOffset > trace.length) {
            methodCount = trace.length - stackOffset - 1;
        }

        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + stackOffset;
            if (stackIndex >= trace.length) {
                continue;
            }
            StackTraceElement element = trace[stackIndex];
            return element;
        }

        return null;
    }

    /**
     * <pre>
     * 获取栈的偏移位置
     * </pre>
     *
     * @author ygr
     * @date 2018年4月9日 下午3:36:30
     * @param trace
     * @return
     */
    private static int getStackOffset(StackTraceElement[] trace) {
        for (int i = 2; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(LogUtils.class.getName())) {
                return --i;
            }
        }
        return -1;
    }


}

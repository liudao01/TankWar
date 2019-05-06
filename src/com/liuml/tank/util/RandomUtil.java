package com.liuml.tank.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.liuml.tank.TankFrame;

/**
 * @ClassName: RandomUtil
 * @Description: 随机数工具类
 * (分别使用java.util.Random、Apache Common Math3、Apache Common Lang3、TreadLocalRandom)
 */
public class RandomUtil {
    /**
     * 随机数Int的生成
     */
    // 随机数生成无边界的Int
    public static int getRandomForIntegerUnbounded() {
        int intUnbounded = new Random().nextInt();
//        System.out.println(intUnbounded);
        return intUnbounded;
    }

    public static int getRandomHeight() {
        return getRandomForIntegerBounded(0, TankFrame.GAME_HEIGHT);
    }
    public static int getRandomWidth() {
        return getRandomForIntegerBounded(0, TankFrame.GAME_WIDTH);
    }
    // 生成有边界的Int
    public static int getRandomForIntegerBounded(int min, int max) {
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
//        System.out.println(intBounded);
        return intBounded;
    }


    // 使用TreadLocalRandom来生成有边界的Int,包含min而不包含max
    public static int getRandomForIntegerBounded4(int min, int max) {
        int threadIntBound = ThreadLocalRandom.current().nextInt(min, max);
        System.out.println(threadIntBound);
        return threadIntBound;
    }

    /**
     * 随机数Long的生成
     */
    // 随机数生成无边界的Long
    public static long getRandomForLongUnbounded() {
        long unboundedLong = new Random().nextLong();
        System.out.println(unboundedLong);
        return unboundedLong;
    }

    // 因为Random类使用的种子是48bits，所以nextLong不能返回所有可能的long值，long是64bits。
    // 使用Random生成有边界的Long
    public static long getRandomForLongBounded(long min, long max) {
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        System.out.println(rangeLong);
        return rangeLong;
    }


    // 使用ThreadLocalRandom生成有边界的Long
    public static long getRandomForLongBounded4(long min, long max) {
        long threadLongBound = ThreadLocalRandom.current().nextLong(min, max);
//        System.out.println(threadLongBound);
        return threadLongBound;
    }

    /**
     * 随机数Float的生成
     */
    // 随机数Float的生成生成0.0-1.0之间的Float随机数
    public static float getRandomForFloat0To1() {
        float floatUnbounded = new Random().nextFloat();
//        System.out.println(floatUnbounded);
        return floatUnbounded;
    }

    // 以上只会生成包含0.0而不包括1.0的float类型随机数生成有边界的Float随机数
    public static float getRandomForFloatBounded(float min, float max) {
        float floatBounded = min + new Random().nextFloat() * (max - min);
//        System.out.println(floatBounded);
        return floatBounded;
    }


    // 使用ThreadLocalRandom生成有边界的Float随机数
    // ThreadLocalRandom类没有提供

    /**
     * 随机数Double的生成
     */
    // 生成0.0d-1.0d之间的Double随机数
    public static double getRandomForDouble0To1() {
        double generatorDouble = new Random().nextDouble();
        System.out.println(generatorDouble);
        return generatorDouble;
    }

    // 与Float相同，以上方法只会生成包含0.0d而不包含1.0d的随机数生成带有边界的Double随机数
    public static double getRandomForDoubleBounded(double min, double max) {
        double boundedDouble = min + new Random().nextDouble() * (max - min);
        System.out.println(boundedDouble);
        return boundedDouble;
    }

    // 使用ThreadLocalRandom生成有边界的Double随机数
    public static double getRandomForDoubleBounded4(double min, double max) {
        double generatedDouble = ThreadLocalRandom.current().nextDouble(min, max);
        System.out.println(generatedDouble);
        return generatedDouble;
    }
}
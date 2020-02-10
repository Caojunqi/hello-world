package chessai.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数相关工具类
 */
public final class RandomUtils {

    private RandomUtils() {
        // 此工具类不允许被实例化
    }

    /**
     * 获取两个整数之间的随机数
     *
     * @param min     最小值
     * @param max     最大值
     * @param include 是否包含边界值
     * @return 整数随机数
     */
    public static int betweenInt(int min, int max, boolean include) {
        return (int) betweenLong(min, max, include);
    }

    /**
     * 获取两个整数之间的随机数
     *
     * @param min     最小值
     * @param max     最大值
     * @param include 是否包含边界
     * @return 整数随机数
     */
    public static long betweenLong(long min, long max, boolean include) {
        // 参数检查
        if (min > max) {
            throw new IllegalArgumentException("最小值[" + min + "]不能大于最大值[" + max + "]");
        } else if (!include && min == max) {
            throw new IllegalArgumentException("不包括边界值时最小值[" + min + "]不能等于最大值[" + max + "]");
        }
        // 修正边界值
        if (include) {
            max++;
        } else {
            min++;
        }
        return min + ThreadLocalRandom.current().nextLong(max - min);
    }

    public static double betweenDouble(double min, double max) {
        // 参数检查
        if (min > max) {
            throw new IllegalArgumentException("最小值[" + min + "]不能大于最大值[" + max + "]");
        }

        if (min == max) {
            return min;
        }
        return min + ThreadLocalRandom.current().nextDouble(max - min);
    }
}

package xyz.mxue.printing.service.common.utils;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
public class PageUtils {

    // 当前页计算
    public static int current(int start, int length) {
        return (start + length) / length;
    }

}

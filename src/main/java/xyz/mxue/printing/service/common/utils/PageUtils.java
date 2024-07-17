package xyz.mxue.printing.service.common.utils;

/**
 * @author mxuexxmy
 * @date 3/25/2021$ 10:14 PM$
 */
public class PageUtils {

    // 当前页计算
    public static int current(int start, int length) {
        return (start + length) / length;
    }

}

package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.entity.TbStatistics;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 统计盈亏 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
public interface TbStatisticsService extends IService<TbStatistics> {

    /**
     * 获取指定天的收入
     * @return BigDecimal
     */
    BigDecimal getDayOfIncome(Date startDate, Date endDate);

    /**
     * 获取指定他的支出
     * @return BigDecimal
     */
    BigDecimal getDayOfPayOut(Date startDate, Date endDate);
}

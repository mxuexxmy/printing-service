package xyz.mxue.printing.service.service;

import xyz.mxue.printing.service.entity.dto.CategoriesDetailsDTO;
import xyz.mxue.printing.service.entity.vo.StatisticsDateVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 类别消费统计 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-20
 */
public interface TbStatisticsDetailsService {

    /**
     * 每月统计
     *
     * @param statisticsTime 时间
     * @return List<StatisticsDateVO>
     */
    List<StatisticsDateVO> statisticsTimeShowOfMonth(Date statisticsTime);

    /**
     * 根据时间查询类别详情
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    List<CategoriesDetailsDTO> categoriesDetailsByTime(Date startTime, Date endTime);

    /**
     * 每年统计
     *
     * @param statisticsTime 时间
     * @return List<StatisticsDateVO>
     */
    List<StatisticsDateVO> statisticsTimeShowOfYear(Date statisticsTime);
}

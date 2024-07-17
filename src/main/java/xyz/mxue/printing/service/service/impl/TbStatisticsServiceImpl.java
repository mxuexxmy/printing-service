package xyz.mxue.printing.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.mxue.printing.service.entity.TbStatistics;
import xyz.mxue.printing.service.mapper.TbStatisticsMapper;
import xyz.mxue.printing.service.service.TbAccountBookService;
import xyz.mxue.printing.service.service.TbStatisticsService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 统计盈亏 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
@Service
public class TbStatisticsServiceImpl extends ServiceImpl<TbStatisticsMapper, TbStatistics> implements TbStatisticsService {

    @Resource
    private TbAccountBookService accountBookService;

    @Override
    public BigDecimal getDayOfIncome(Date startDate, Date endDate) {
        return accountBookService.getDayOfIncome(startDate, endDate);
    }

    @Override
    public BigDecimal getDayOfPayOut(Date startDate, Date endDate) {
        return accountBookService.getDayOfPayOut(startDate,  endDate);
    }
}

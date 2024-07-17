package xyz.mxue.printing.service.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.utils.PageUtils;
import xyz.mxue.printing.service.entity.TbOrderMonth;
import xyz.mxue.printing.service.mapper.TbOrderMonthMapper;
import xyz.mxue.printing.service.service.TbOrderMonthService;
import xyz.mxue.printing.service.service.TbPrintOrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 月记录 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@Service
public class TbOrderMonthServiceImpl extends ServiceImpl<TbOrderMonthMapper, TbOrderMonth> implements TbOrderMonthService {

    @Resource
    private TbOrderMonthMapper monthMapper;

    @Resource
    private TbPrintOrderService orderService;

    @Override
    public PageInfo<TbOrderMonth> page(int start, int length, int draw, TbOrderMonth tbOrderMonth) {
        Page<TbOrderMonth> monthPage = new Page<>(PageUtils.current(start, length), length);

        QueryWrapper<TbOrderMonth> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(tbOrderMonth.getFlagPermDate()), "date_format(stats_month,'%Y-%m-%d')",
                tbOrderMonth.getFlagPermDate()).orderByDesc("stats_month");

        Page<TbOrderMonth> monthPage1 = monthMapper.selectPage(monthPage, queryWrapper);

        PageInfo<TbOrderMonth> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(monthPage1.getTotal());
        pageInfo.setRecordsFiltered(monthPage1.getTotal());
        pageInfo.setData(monthPage1.getRecords());

        return pageInfo;
    }

    @Override
    public String monthRecord(Date date) {

        // 这个月开始时间
        Date startDate = DateUtil.beginOfMonth(date);
        // 这个月结束时间
        Date endDate = DateUtil.endOfMonth(date);

        // 计算每月的份数
        Integer printNumber = orderService.sumPrintNumber(startDate, endDate);
        // 计算每月的金额
        BigDecimal totalAmount = orderService.getPrintfIncomeByDate(startDate, endDate);
        // 查询是否有记录
        QueryWrapper<TbOrderMonth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stats_month", startDate);
        TbOrderMonth tbOrderMonth = monthMapper.selectOne(queryWrapper);

        if (printNumber == null) {
            printNumber = 0;
        }
        if (totalAmount == null) {
            totalAmount = BigDecimal.valueOf(0D);
        }

        if (tbOrderMonth != null) {
            tbOrderMonth.setTotalAmount(totalAmount);
            tbOrderMonth.setPrintfNumber(printNumber);
            tbOrderMonth.setUpdateTime(new Date());
            int i = monthMapper.updateById(tbOrderMonth);
            if (i > 0) {
                return "月记录更新成功";
            }
            return "月记录更新失败";
        }
        TbOrderMonth newOrderMonth = new TbOrderMonth();
        newOrderMonth.setPrintfNumber(printNumber);
        newOrderMonth.setTotalAmount(totalAmount);
        newOrderMonth.setStatsMonth(startDate);
        newOrderMonth.setCreateTime(new Date());
        newOrderMonth.setUpdateTime(new Date());
        int insert = monthMapper.insert(newOrderMonth);
        if (insert > 0) {
            return "月记录插入成功";
        }
        return "月记录插入失败";
    }
}

package xyz.mxue.printing.service.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.utils.PageUtils;
import xyz.mxue.printing.service.entity.TbOrderDay;
import xyz.mxue.printing.service.mapper.TbOrderDayMapper;
import xyz.mxue.printing.service.service.TbOrderDayService;
import xyz.mxue.printing.service.service.TbPrintOrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 日记录 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@Service
public class TbOrderDayServiceImpl extends ServiceImpl<TbOrderDayMapper, TbOrderDay> implements TbOrderDayService {

    @Resource
    private TbOrderDayMapper dayMapper;

    @Resource
    private TbPrintOrderService orderService;

    @Override
    public PageInfo<TbOrderDay> page(int start, int length, int draw, TbOrderDay tbOrderDay) {
        Page<TbOrderDay> tbOrderDayPage = new Page<>(PageUtils.current(start, length), length);

        QueryWrapper<TbOrderDay> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(tbOrderDay.getFlagPermDate()), "date_format(stats_day,'%Y-%m-%d')", tbOrderDay.getFlagPermDate())
                    .orderByDesc("stats_day");

        Page<TbOrderDay> tbPrintOrderPage1 = dayMapper.selectPage(tbOrderDayPage, queryWrapper);

        PageInfo<TbOrderDay> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(tbPrintOrderPage1.getTotal());
        pageInfo.setRecordsFiltered(tbPrintOrderPage1.getTotal());
        pageInfo.setData(tbPrintOrderPage1.getRecords());

        return pageInfo;
    }

    @Override
    public String dayRecord(Date date)  {
        // 今日开始时间
        Date startDate = DateUtil.beginOfDay(date);
        // 今日结束时间
        Date endDate = DateUtil.endOfDay(date);

        // 计算每一日的份数
        Integer printNumber  = orderService.sumPrintNumber(startDate, endDate);
        // 计算每一次的金额
        BigDecimal totalAmount = orderService.getPrintfIncomeByDate(startDate, endDate);
        // 查询是否有记录
        QueryWrapper<TbOrderDay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stats_day", startDate);
        TbOrderDay tbOrderDay = dayMapper.selectOne(queryWrapper);

        if (printNumber == null) {
            printNumber = 0;
        }
        if (totalAmount == null) {
            totalAmount = BigDecimal.valueOf(0D);
        }

        if (tbOrderDay != null) {
            tbOrderDay.setTotalAmount(totalAmount);
            tbOrderDay.setPrintfNumber(printNumber);
            tbOrderDay.setUpdateTime(new Date());
            int i = dayMapper.updateById(tbOrderDay);
            if (i > 0) {
                return "日记录更新成功";
            }
            return "日记录更新失败";
        }
        TbOrderDay newOrderDay =  new TbOrderDay();
        newOrderDay.setPrintfNumber(printNumber);
        newOrderDay.setTotalAmount(totalAmount);
        newOrderDay.setStatsDay(startDate);
        newOrderDay.setCreateTime(new Date());
        newOrderDay.setUpdateTime(new Date());
        int insert = dayMapper.insert(newOrderDay);
        if (insert > 0) {
            return "日记录插入成功";
        }
        return "日记录插入失败";
    }
}

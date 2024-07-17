package xyz.mxue.printing.service.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.utils.PageUtils;
import xyz.mxue.printing.service.entity.TbOrderYear;
import xyz.mxue.printing.service.mapper.TbOrderYearMapper;
import xyz.mxue.printing.service.service.TbOrderYearService;
import xyz.mxue.printing.service.service.TbPrintOrderService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 年记录 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@Service
public class TbOrderYearServiceImpl extends ServiceImpl<TbOrderYearMapper, TbOrderYear> implements TbOrderYearService {

    @Resource
    private TbOrderYearMapper yearMapper;

    @Resource
    private TbPrintOrderService orderService;

    @Override
    public PageInfo<TbOrderYear> page(int start, int length, int draw, TbOrderYear tbOrderYear) {
        Page<TbOrderYear> tbOrderDayPage = new Page<>(PageUtils.current(start, length), length);

        QueryWrapper<TbOrderYear> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(tbOrderYear.getFlagPermDate()), "date_format(stats_day,'%Y-%m-%d')",
                tbOrderYear.getFlagPermDate()).orderByDesc("stats_year");

        Page<TbOrderYear> tbPrintOrderPage1 = yearMapper.selectPage(tbOrderDayPage, queryWrapper);

        PageInfo<TbOrderYear> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(tbPrintOrderPage1.getTotal());
        pageInfo.setRecordsFiltered(tbPrintOrderPage1.getTotal());
        pageInfo.setData(tbPrintOrderPage1.getRecords());

        return pageInfo;
    }

    @Override
    public String yearRecord(Date date) {
        // 这年开始时间
        Date startDate = DateUtil.beginOfYear(date);
        // 这年结束时间
        Date endDate = DateUtil.endOfYear(date);


        // 计算每月的份数
        Integer printNumber  = orderService.sumPrintNumber(startDate, endDate);
        // 计算每月的金额
        BigDecimal totalAmount = orderService.getPrintfIncomeByDate(startDate, endDate);
        // 查询是否有记录
        QueryWrapper<TbOrderYear> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stats_year", startDate);
        TbOrderYear tbOrderYear = yearMapper.selectOne(queryWrapper);

        if (printNumber == null) {
            printNumber = 0;
        }
        if (totalAmount == null) {
            totalAmount = BigDecimal.valueOf(0D);
        }

        if (tbOrderYear != null) {
            tbOrderYear.setTotalAmount(totalAmount);
            tbOrderYear.setPrintfNumber(printNumber);
            tbOrderYear.setUpdateTime(new Date());
            int i = yearMapper.updateById(tbOrderYear);
            if (i > 0) {
                return "年记录更新成功";
            }
            return "年记录更新失败";
        }
        TbOrderYear newTbOrderYear =  new TbOrderYear();
        newTbOrderYear.setPrintfNumber(printNumber);
        newTbOrderYear.setTotalAmount(totalAmount);
        newTbOrderYear.setStatsYear(startDate);
        newTbOrderYear.setCreateTime(new Date());
        newTbOrderYear.setUpdateTime(new Date());
        int insert = yearMapper.insert(newTbOrderYear);
        if (insert > 0) {
            return "年记录插入成功";
        }
        return "年记录插入失败";
    }

}

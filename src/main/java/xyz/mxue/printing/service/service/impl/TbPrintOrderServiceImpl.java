package xyz.mxue.printing.service.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.mxue.printing.service.common.enums.OrderStatusEnum;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.utils.PageUtils;
import xyz.mxue.printing.service.entity.TbPrintOrder;
import xyz.mxue.printing.service.entity.TbPrintfInfo;
import xyz.mxue.printing.service.mapper.TbPrintOrderMapper;
import xyz.mxue.printing.service.service.TbPrintOrderService;
import xyz.mxue.printing.service.service.TbPrintfInfoService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 打印订单 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-06
 */
@Service
public class TbPrintOrderServiceImpl extends ServiceImpl<TbPrintOrderMapper, TbPrintOrder> implements TbPrintOrderService {

    @Resource
    private TbPrintOrderMapper orderMapper;

    @Resource
    private TbPrintfInfoService printfInfoService;

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @param draw
     * @param tbPrintOrder
     * @return
     */
    @Override
    public PageInfo<TbPrintOrder> page(int start, int length, int draw, TbPrintOrder tbPrintOrder)  {
        Page<TbPrintOrder> printOrderPage = new Page<>(PageUtils.current(start, length), length);

        QueryWrapper<TbPrintOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(tbPrintOrder.getUserName()), "user_name", tbPrintOrder.getUserName())
                .eq(StrUtil.isNotBlank(tbPrintOrder.getOrderStatus()), "order_status", tbPrintOrder.getOrderStatus())
                .like(StrUtil.isNotBlank(tbPrintOrder.getFlagPermDate()), "date_format(update_time,'%Y-%m-%d')", tbPrintOrder.getFlagPermDate())
                .orderByAsc("order_status")
                 .orderByDesc("update_time");

        IPage<TbPrintOrder> tbPrintOrderPage1 = orderMapper.selectPage(printOrderPage, queryWrapper);

        PageInfo<TbPrintOrder> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(tbPrintOrderPage1.getTotal());
        pageInfo.setRecordsFiltered(tbPrintOrderPage1.getTotal());
        pageInfo.setData(tbPrintOrderPage1.getRecords());

        return pageInfo;
    }

    @Override
    public Integer sumPrintNumber(Date startDate, Date endDate) {
        QueryWrapper<TbPrintOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("a.order_status", OrderStatusEnum.COMPLETE.getDesc())
                .between(Objects.nonNull(startDate) && Objects.nonNull(endDate), "a.create_time", startDate, endDate);
        Integer result =  orderMapper.sumPrintNumber(queryWrapper);
        return result != null ? result : 0;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrderInfoAndPrintfInfo(TbPrintOrder tbPrintOrder, List<TbPrintfInfo> printfInfoList) {
        int insert = orderMapper.insert(tbPrintOrder);

        if (insert > 0) {
            // 添加 打印订单 id
            addPrintfOrderID(printfInfoList, tbPrintOrder.getId());
            boolean b = printfInfoService.saveBatch(printfInfoList);
            return b;
        }

        return false;
    }

    @Override
    public Long getDayOfPrintfNumber(Date startDay, Date endDay) {
        QueryWrapper<TbPrintOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.between(Objects.nonNull(startDay) && Objects.nonNull(endDay), "create_time", startDay, endDay);
        Long queryResult =  orderMapper.selectCount(queryWrapper);
        return queryResult != null ? queryResult : 0;
    }

    @Override
    public BigDecimal getPrintfIncomeByDate(Date startDate, Date endDate) {
        QueryWrapper<TbPrintOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", OrderStatusEnum.COMPLETE.getDesc())
                    .between(Objects.nonNull(startDate) && Objects.nonNull(endDate), "update_time", startDate, endDate);
        BigDecimal queryResult = orderMapper.getPrintfIncomeByDate(queryWrapper);
        return queryResult != null ? queryResult : BigDecimal.valueOf(0D);
    }

    private void addPrintfOrderID(List<TbPrintfInfo> printfInfoList, Long orderId) {
        for (TbPrintfInfo info : printfInfoList) {
            info.setOrderId(orderId);
        }
    }

}

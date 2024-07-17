package xyz.mxue.printing.service.controller;


import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbOrderMonth;
import xyz.mxue.printing.service.service.TbOrderMonthService;

import javax.annotation.Resource;

/**
 * <p>
 * 月记录 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/printing/tb-order-month")
public class TbOrderMonthController {

    @Resource
    private TbOrderMonthService monthService;

    @GetMapping("update/{id}")
    public Result update(@PathVariable Long id) {
        TbOrderMonth orderMonth = monthService.getById(id);
        String message = monthService.monthRecord(orderMonth.getStatsMonth());
        return Result.success(DateUtil.format(orderMonth.getStatsMonth(), "yyyy-MM") + message);
    }

    /**
     * 分页查询
     *
     * @param tbOrderMonth
     * @return
     */
    @GetMapping("/page")
    public PageInfo<TbOrderMonth> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                       @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                       @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                       TbOrderMonth tbOrderMonth) {
        return monthService.page(start, length, draw, tbOrderMonth);
    }

}


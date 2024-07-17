package xyz.mxue.printing.service.controller;


import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbOrderYear;
import xyz.mxue.printing.service.service.TbOrderYearService;

import javax.annotation.Resource;


/**
 * <p>
 * 年记录 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/printing/tb-order-year")
public class TbOrderYearController {

    @Resource
    private TbOrderYearService yearService;

    @GetMapping("update/{id}")
    public Result update(@PathVariable Long id) {
        TbOrderYear orderYear = yearService.getById(id);
        String message = yearService.yearRecord(orderYear.getStatsYear());
        return Result.success(DateUtil.format(orderYear.getStatsYear(), "yyyy")  + message);
    }

    @GetMapping("/page")
    public PageInfo<TbOrderYear> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                      @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                      @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                      TbOrderYear tbOrderYear) {

        return yearService.page(start, length, draw, tbOrderYear);
    }


}


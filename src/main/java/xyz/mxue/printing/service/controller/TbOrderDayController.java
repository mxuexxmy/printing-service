package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbOrderDay;
import xyz.mxue.printing.service.service.TbOrderDayService;

import javax.annotation.Resource;

/**
 * <p>
 * 日记录 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@Api(tags = "日记录管理")
@RestController
@RequestMapping("/printing/tb-order-day")
public class TbOrderDayController {

    @Resource
    private TbOrderDayService dayService;

    @ApiOperation(value = "更新日记录")
    @GetMapping("update/{id}")
    @SaCheckLogin
    public Result<Boolean> update(@PathVariable Long id) {
        TbOrderDay orderDay = dayService.getById(id);
        String message = dayService.dayRecord(orderDay.getStatsDay());
        return Result.success(DateUtil.format(orderDay.getStatsDay(), "yyyy-MM-dd") + "的" + message);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @SaCheckLogin
    public PageInfo<TbOrderDay> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                     @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                     @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                     TbOrderDay tbOrderDay) {

        // 封装 Datatables 需要的结果

        return dayService.page(start, length, draw, tbOrderDay);
    }

}


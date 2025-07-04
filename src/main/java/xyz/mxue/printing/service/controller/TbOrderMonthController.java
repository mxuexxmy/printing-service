package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "月记录管理")
@RestController
@RequestMapping("/printing/tb-order-month")
public class TbOrderMonthController {

    @Resource
    private TbOrderMonthService monthService;

    @ApiOperation(value = "更新月记录")
    @GetMapping("update/{id}")
    @SaCheckLogin
    public Result<Boolean> update(@PathVariable Long id) {
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
    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @SaCheckLogin
    public Result<PageInfo<TbOrderMonth>> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                       @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                       @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                       TbOrderMonth tbOrderMonth) {
        return Result.success(monthService.page(start, length, draw, tbOrderMonth));
    }

}


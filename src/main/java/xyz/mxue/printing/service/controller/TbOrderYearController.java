package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "年记录管理")
@RestController
@RequestMapping("/printing/tb-order-year")
public class TbOrderYearController {

    @Resource
    private TbOrderYearService yearService;

    @ApiOperation(value = "更新年记录")
    @GetMapping("update/{id}")
    @SaCheckLogin
    public Result<Boolean> update(@PathVariable Long id) {
        TbOrderYear orderYear = yearService.getById(id);
        String message = yearService.yearRecord(orderYear.getStatsYear());
        return Result.success(DateUtil.format(orderYear.getStatsYear(), "yyyy")  + message);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @SaCheckLogin
    public PageInfo<TbOrderYear> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                      @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                      @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                      TbOrderYear tbOrderYear) {

        return yearService.page(start, length, draw, tbOrderYear);
    }


}


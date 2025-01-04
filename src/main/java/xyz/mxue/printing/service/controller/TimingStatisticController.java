package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.service.TbOrderDayService;
import xyz.mxue.printing.service.service.TbOrderMonthService;
import xyz.mxue.printing.service.service.TbOrderYearService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@Api(tags = "归集管理")
@RestController
@RequestMapping("time")
public class TimingStatisticController {

    @Resource
    private TbOrderDayService dayService;

    @Resource
    private TbOrderMonthService monthService;

    @Resource
    private TbOrderYearService yearService;

    @ApiOperation(value = "归集管理")
    @GetMapping("/day-record")
    @SaCheckLogin
    public Result<Boolean> dayRecord() {
        Date date = new Date();
        String message = dayService.dayRecord(date);
        return Result.success(DateUtil.format(date, "yyyy-MM-dd") + message);
    }

    @ApiOperation(value = "月归集")
    @GetMapping("/month-record")
    @SaCheckLogin
    public Result<Boolean> monthRecord() {
        Date date = new Date();
        String message = monthService.monthRecord(date);
        return Result.success(DateUtil.format(date, "yyyy-MM") + message);
    }

    @ApiOperation(value = "年归集")
    @GetMapping("/year-record")
    @SaCheckLogin
    public Result<Boolean> yearRecord() {
        Date date = new Date();
        String message = yearService.yearRecord(date);
        return Result.success(DateUtil.format(date, "yyyy") + message);
    }
}

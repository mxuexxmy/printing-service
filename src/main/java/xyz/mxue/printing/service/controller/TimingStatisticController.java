package xyz.mxue.printing.service.controller;

import cn.hutool.core.date.DateUtil;
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
 * @date 12/7/2020$ 1:11 AM$
 */
@RestController
@RequestMapping("time")
public class TimingStatisticController {

    @Resource
    private TbOrderDayService dayService;

    @Resource
    private TbOrderMonthService monthService;

    @Resource
    private TbOrderYearService yearService;

    @GetMapping("/day-record")
    public Result dayRecord() {
        Date date = new Date();
        String message = dayService.dayRecord(date);
        return Result.success(DateUtil.format(date, "yyyy-MM-dd") + message);
    }

    @GetMapping("/month-record")
    public Result monthRecord() {
        Date date = new Date();
        String message = monthService.monthRecord(date);
        return Result.success(DateUtil.format(date, "yyyy-MM") + message);
    }

    @GetMapping("/year-record")
    public Result yearRecord() {
        Date date = new Date();
        String message = yearService.yearRecord(date);
        return Result.success(DateUtil.format(date, "yyyy") + message);
    }
}

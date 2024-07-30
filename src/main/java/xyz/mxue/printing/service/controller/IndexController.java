package xyz.mxue.printing.service.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.vo.IndexInfoVO;
import xyz.mxue.printing.service.service.TbPrintOrderService;
import xyz.mxue.printing.service.service.TbStatisticsService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@Api(tags = "首页信息")
@RestController
@RequestMapping
public class IndexController {

    @Resource
    private TbPrintOrderService orderService;

    @Resource
    private TbStatisticsService statisticsService;

    @ApiOperation(value = "首页汇总")
    @GetMapping("/index")
    public Result<IndexInfoVO> indexView() {
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        Date nowDate = new Date();
        Date startDate = DateUtil.beginOfDay(nowDate);
        Date endDate = DateUtil.endOfDay(nowDate);
        // 今日打印单数
        indexInfoVO.setDayPrintfNumber(orderService.getDayOfPrintfNumber(startDate, endDate));
        // 今日打印收入
        indexInfoVO.setDayPrintfIncome(orderService.getPrintfIncomeByDate(startDate, endDate));
        // 今日收入
        BigDecimal dayIncome = statisticsService.getDayOfIncome(startDate, endDate);
        indexInfoVO.setDayIncome(dayIncome.add(indexInfoVO.getDayPrintfIncome()));
        // 今日支出
        indexInfoVO.setDayPayOut(statisticsService.getDayOfPayOut(startDate, endDate));
        return Result.success(indexInfoVO);
    }

}

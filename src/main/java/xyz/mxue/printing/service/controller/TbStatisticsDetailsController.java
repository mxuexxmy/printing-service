package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.vo.StatisticsDateVO;
import xyz.mxue.printing.service.entity.vo.StatisticsDetailsMothVO;
import xyz.mxue.printing.service.entity.vo.StatisticsDetailsYearVO;
import xyz.mxue.printing.service.service.TbStatisticsDetailsService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类别消费统计 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-20
 */
@Api(tags = "类别消费统计")
@RestController
@RequestMapping("/printing/tb-statistics-details")
public class TbStatisticsDetailsController {

    @Resource
    private TbStatisticsDetailsService statisticsDetailsService;

    @ApiOperation(value = "月统计")
    @GetMapping("/month")
    @SaCheckLogin
    private Result<StatisticsDetailsMothVO> month() {
        StatisticsDetailsMothVO statisticsDetailsMothVO = new StatisticsDetailsMothVO();
        statisticsDetailsMothVO.setStatisticsTimes(statisticsDetailsService.statisticsTimeShowOfMonth(new Date()));
        return Result.success(statisticsDetailsMothVO);
    }

    @ApiOperation(value = "年统计")
    @GetMapping("/year")
    @SaCheckLogin
    private Result<StatisticsDetailsYearVO> year() {
        StatisticsDetailsYearVO statisticsDetailsYearVO = new StatisticsDetailsYearVO();
        statisticsDetailsYearVO.setStatisticsTimes(statisticsDetailsService.statisticsTimeShowOfYear(new Date()));
        return Result.success(statisticsDetailsYearVO);
    }
}


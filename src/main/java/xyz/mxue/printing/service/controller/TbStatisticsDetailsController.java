package xyz.mxue.printing.service.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mxue.printing.service.entity.vo.StatisticsDateVO;
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
@RestController
@RequestMapping("/printing/tb-statistics-details")
public class TbStatisticsDetailsController {

    @Resource
    private TbStatisticsDetailsService statisticsDetailsService;

    @GetMapping("/month")
    private Map<String, List<StatisticsDateVO>> month() {
        Map<String, List<StatisticsDateVO>> map = new HashMap<>();
        map.put("statisticsTimes", statisticsDetailsService.statisticsTimeShowOfMonth(new Date()));
        return map;
    }

    @GetMapping("/year")
    private Map<String, List<StatisticsDateVO>> year() {
        Map<String, List<StatisticsDateVO>> map = new HashMap<>();
        map.put("statisticsTimes", statisticsDetailsService.statisticsTimeShowOfYear(new Date()));
        return map;
    }
}


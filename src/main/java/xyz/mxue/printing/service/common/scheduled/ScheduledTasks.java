package xyz.mxue.printing.service.common.scheduled;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.mxue.printing.service.service.TbOrderDayService;
import xyz.mxue.printing.service.service.TbOrderMonthService;
import xyz.mxue.printing.service.service.TbOrderYearService;
import xyz.mxue.printing.service.service.TbStatisticsDetailsService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mxuexxmy
 * @date 12/9/2020$ 1:42 AM$
 */
@Component
public class ScheduledTasks {

    @Resource
    private TbOrderDayService dayService;

    @Resource
    private TbOrderMonthService monthService;

    @Resource
    private TbOrderYearService yearService;

    @Resource
    private TbStatisticsDetailsService statisticsDetailsService;

    @Scheduled(cron = "0 0,10 0,0 ? * ? ")
    public void reportCurrentTime() {
        Date date = new Date();
        Date yesterday = DateUtil.yesterday();
        System.out.println( yesterday + "日记录定时更新：" + dayService.dayRecord(yesterday));
        System.out.println(DateUtil.today() + "日记录定时更新：" + dayService.dayRecord(date));
        System.out.println(DateUtil.beginOfMonth(date) + "月记录定时更新：" + monthService.monthRecord(date));
        System.out.println(DateUtil.beginOfYear(date) + "年记录定时更新：" + yearService.yearRecord(date));
        System.out.println("记账管家-每月统计：" + statisticsDetailsService.statisticsTimeShowOfMonth(date));
        System.out.println("记账管家-每年统计：" + statisticsDetailsService.statisticsTimeShowOfYear(date));
    }
}

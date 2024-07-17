package xyz.mxue.printing.service.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import xyz.mxue.printing.service.entity.TbStatistics;
import xyz.mxue.printing.service.entity.TbStatisticsYear;
import xyz.mxue.printing.service.entity.dto.CategoriesDetailsDTO;
import xyz.mxue.printing.service.entity.dto.MoneyAndSpendTypeDTO;
import xyz.mxue.printing.service.entity.vo.StatisticsDateVO;
import xyz.mxue.printing.service.service.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 类别消费统计 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-20
 */
@Service
public class TbStatisticsDetailsServiceImpl implements TbStatisticsDetailsService {

    // 支出
    private static final Integer PAY_OUT = 0;

    // 收入
    private static final Integer INCOME = 1;

    @Resource
    private TbStatisticsService statisticsService;

    @Resource
    private TbPrintOrderService printOrderService;

    @Resource
    private TbAccountBookService accountBookService;

    @Resource
    private TbCategoriesService categoriesService;

    @Resource
    private TbStatisticsYearService tbStatisticsYearService;

    @Override
    public List<StatisticsDateVO> statisticsTimeShowOfMonth(Date statisticsTime) {
        if (statisticsTime == null) {
            statisticsTime = new Date();
        }
        // 统计时间调整为传进来的时间的当月
        Date nowDate = DateUtil.beginOfMonth(statisticsTime);

        // 查询是否有当月的记录
        QueryWrapper<TbStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("statistics_time", nowDate);
        TbStatistics tbStatistics = new TbStatistics();
        TbStatistics queryTbStatistics = statisticsService.getOne(queryWrapper);

        if (queryTbStatistics == null) {
            tbStatistics.setStatisticsTime(nowDate);
            tbStatistics.setCreateTime(new Date());
            tbStatistics.setUpdateTime(new Date());
            statisticsService.save(tbStatistics);
        } else {
            tbStatistics = queryTbStatistics;
        }

        // 查询所有的记录
        List<TbStatistics> statisticsList = statisticsService.list();
        List<StatisticsDateVO> statisticsDateVOS = new ArrayList<>();

        for (TbStatistics tbStatistics2 : statisticsList) {
            StatisticsDateVO statisticsDateVO = new StatisticsDateVO();
            statisticsDateVO.setStatisticsTime(tbStatistics2.getStatisticsTime());
            statisticsDateVO.setTimeString(Integer.toString(DateUtil.year(tbStatistics2.getStatisticsTime())) + "年" +
                    Integer.toString(DateUtil.month(tbStatistics2.getStatisticsTime()) + 1) + "月");
            statisticsDateVO.setCategoriesDetails(categoriesDetailsByTime(DateUtil.beginOfMonth(tbStatistics2.getStatisticsTime()),
                    DateUtil.endOfMonth(tbStatistics2.getStatisticsTime())));
            statisticsDateVOS.add(statisticsDateVO);
        }
        return statisticsDateVOS;
    }

    @Override
    public List<StatisticsDateVO> statisticsTimeShowOfYear(Date statisticsTime) {
        if (statisticsTime == null) {
            statisticsTime = new Date();
        }
        // 进行时间处理
        // 统计时间调整为传进来的时间的当年
        Date nowDate = DateUtil.beginOfYear(statisticsTime);
        // 查询是否有当年的记录
        QueryWrapper<TbStatisticsYear> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("statistics_time", nowDate);
        TbStatisticsYear statisticsYear = new TbStatisticsYear();
        TbStatisticsYear queryTbStatisticYear = tbStatisticsYearService.getOne(queryWrapper);

        if (queryTbStatisticYear == null) {
            statisticsYear.setStatisticsTime(nowDate);
            statisticsYear.setCreateTime(new Date());
            statisticsYear.setUpdateTime(new Date());
            tbStatisticsYearService.save(statisticsYear);
        } else {
            statisticsYear = queryTbStatisticYear;
        }

        // 查询所有的记录
        List<TbStatisticsYear> statisticsYears = tbStatisticsYearService.list();
        List<StatisticsDateVO> statisticsDateVOS = new ArrayList<>();

        for (TbStatisticsYear statisticsYear1 : statisticsYears) {
            StatisticsDateVO statisticsDateVO = new StatisticsDateVO();
            statisticsDateVO.setStatisticsTime(statisticsYear1.getStatisticsTime());
            statisticsDateVO.setTimeString(Integer.toString(DateUtil.year(statisticsYear1.getStatisticsTime())) + "年");
            statisticsDateVO.setCategoriesDetails(categoriesDetailsByTime(DateUtil.beginOfYear(statisticsYear1.getStatisticsTime()),
                    DateUtil.endOfYear(statisticsYear1.getStatisticsTime())));
            statisticsDateVOS.add(statisticsDateVO);
        }

        return statisticsDateVOS;
    }


    @Override
    public List<CategoriesDetailsDTO> categoriesDetailsByTime(Date startTime, Date endTime) {
        List<CategoriesDetailsDTO> categoriesDetailsDTOS = new ArrayList<>();

        // 打印金额
        BigDecimal printfSumAmount = printOrderService.getPrintfIncomeByDate(startTime, endTime);
        if (Objects.isNull(printfSumAmount)) {
            printfSumAmount = BigDecimal.valueOf(0D);
        }
        // 查询账单记录，统计支出和收入
        List<MoneyAndSpendTypeDTO> moneyAndSpendTypeDTOS = accountBookService.queryMoneyAndSpendType(startTime, endTime);


        // 盈利情况
        BigDecimal profits = printfSumAmount;

        // 收入为空和支出为空的情况
        if (moneyAndSpendTypeDTOS.isEmpty()) {
            CategoriesDetailsDTO categoriesDetailsDTOTemp = new CategoriesDetailsDTO();
            categoriesDetailsDTOTemp.setCategoriesName("支出");
            categoriesDetailsDTOTemp.setMoney(BigDecimal.valueOf(0D));
            categoriesDetailsDTOS.add(categoriesDetailsDTOTemp);
            CategoriesDetailsDTO categoriesDetailsDTOTemp1 = new CategoriesDetailsDTO();
            categoriesDetailsDTOTemp1.setCategoriesName("收入");
            categoriesDetailsDTOTemp1.setMoney(printfSumAmount);
            categoriesDetailsDTOS.add(categoriesDetailsDTOTemp1);
        }

        // 收入为空或者支出为空
        if (!moneyAndSpendTypeDTOS.isEmpty() && moneyAndSpendTypeDTOS.size() == 1) {
            for (MoneyAndSpendTypeDTO moneyAndSpendTypeDTO : moneyAndSpendTypeDTOS) {
                CategoriesDetailsDTO categoriesDetailsDTOTemp = new CategoriesDetailsDTO();
                // 如果有支出，那么收入为 0
                if (moneyAndSpendTypeDTO.getSpendType().equals(PAY_OUT)) {
                    categoriesDetailsDTOTemp.setMoney(printfSumAmount);
                    categoriesDetailsDTOTemp.setCategoriesName("收入");
                }
                // 如果有收入，那么支出为 0
                if (moneyAndSpendTypeDTO.getSpendType().equals(INCOME)) {
                    categoriesDetailsDTOTemp.setMoney(BigDecimal.valueOf(0D));
                    categoriesDetailsDTOTemp.setCategoriesName("支出");
                }
                categoriesDetailsDTOS.add(categoriesDetailsDTOTemp);
            }
        }

        for (MoneyAndSpendTypeDTO moneyAndSpendTypeDTO : moneyAndSpendTypeDTOS) {
            CategoriesDetailsDTO categoriesDetailsDTOTemp = new CategoriesDetailsDTO();
            if (moneyAndSpendTypeDTO.getSpendType().equals(PAY_OUT)) {
                categoriesDetailsDTOTemp.setMoney(moneyAndSpendTypeDTO.getMoney());
                profits = profits.subtract(moneyAndSpendTypeDTO.getMoney());
                categoriesDetailsDTOTemp.setCategoriesName("支出");
            }
            if (moneyAndSpendTypeDTO.getSpendType().equals(INCOME)) {
                categoriesDetailsDTOTemp.setMoney(moneyAndSpendTypeDTO.getMoney().add(printfSumAmount));
                profits = profits.add(moneyAndSpendTypeDTO.getMoney());

                categoriesDetailsDTOTemp.setCategoriesName("收入");
            }
            categoriesDetailsDTOS.add(categoriesDetailsDTOTemp);
        }
        // 盈利情况
        CategoriesDetailsDTO categoriesDetailsDTOProfits = new CategoriesDetailsDTO();
        categoriesDetailsDTOProfits.setMoney(profits);
        categoriesDetailsDTOProfits.setCategoriesName("盈利");
        categoriesDetailsDTOS.add(categoriesDetailsDTOProfits);
        // 打印统计
        CategoriesDetailsDTO categoriesDetailsDTO = new CategoriesDetailsDTO();
        categoriesDetailsDTO.setMoney(printfSumAmount);
        categoriesDetailsDTO.setCategoriesName("打印");
        categoriesDetailsDTOS.add(categoriesDetailsDTO);

        // 按类别统计
        List<CategoriesDetailsDTO> categoriesDetailsDTOS1 = categoriesService.queryMoneyAndCategoriesByTime(startTime, endTime);
        for (CategoriesDetailsDTO categoriesDetailsDTO1 : categoriesDetailsDTOS1) {
            if (categoriesDetailsDTO1.getSpendType().equals(PAY_OUT)) {
                categoriesDetailsDTO1.setMoney(categoriesDetailsDTO1.getMoney().negate());
            }
            categoriesDetailsDTOS.add(categoriesDetailsDTO1);
        }
        return categoriesDetailsDTOS;
    }

}

package xyz.mxue.printing.service.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import xyz.mxue.printing.service.entity.TbPrintOrder;

import java.math.BigDecimal;

/**
 * <p>
 * 打印订单 Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-06
 */
public interface TbPrintOrderMapper extends BaseMapper<TbPrintOrder> {

    Integer sumPrintNumber( @Param(Constants.WRAPPER) QueryWrapper<TbPrintOrder> queryWrapper);


    BigDecimal getPrintfIncomeByDate(@Param(Constants.WRAPPER) QueryWrapper<TbPrintOrder> queryWrapper);

}

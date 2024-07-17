package xyz.mxue.printing.service.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.mxue.printing.service.entity.TbAccountBook;
import xyz.mxue.printing.service.entity.dto.CategoriesNameDTO;
import xyz.mxue.printing.service.entity.dto.MoneyAndSpendTypeDTO;
import xyz.mxue.printing.service.entity.vo.AccountVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
public interface TbAccountBookMapper extends BaseMapper<TbAccountBook> {

    @Select("SELECT id categoriesId, name categoriesName FROM tb_categories")
    List<CategoriesNameDTO> categoriesNames();

    @Select("SELECT SUM(money) money, spend_type FROM tb_account_book WHERE\n" +
            "\tcreate_time BETWEEN #{startTime} \n" +
            "\tAND #{endTime} GROUP BY spend_type")
    List<MoneyAndSpendTypeDTO> queryMoneyAndSpendType(Map<String, Object> params);

    BigDecimal getDayOfIncomeOrPayOut(@Param(Constants.WRAPPER) QueryWrapper<TbAccountBook> queryWrapper);

    Page<AccountVO> queryAccountBookInfos(Page<AccountVO> accountVOPage, @Param(Constants.WRAPPER) QueryWrapper<AccountVO> queryWrapper);
}

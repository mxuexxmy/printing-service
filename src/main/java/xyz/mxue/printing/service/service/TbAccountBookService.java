package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.entity.TbAccountBook;
import xyz.mxue.printing.service.entity.dto.AccountUpdateDTO;
import xyz.mxue.printing.service.entity.dto.CategoriesNameDTO;
import xyz.mxue.printing.service.entity.dto.MoneyAndSpendTypeDTO;
import xyz.mxue.printing.service.entity.vo.AccountVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
public interface TbAccountBookService extends IService<TbAccountBook> {

   PageInfo<AccountVO> page(int start, int length, int draw, TbAccountBook tbAccountBook);

   List<CategoriesNameDTO> categoriesNames();

    AccountUpdateDTO accountUpdate(Long id);

    List<MoneyAndSpendTypeDTO> queryMoneyAndSpendType(Date startTime, Date endTime);

    BigDecimal getDayOfIncome(Date startDate, Date endDate);

    BigDecimal getDayOfPayOut(Date startDate, Date endDate);
}

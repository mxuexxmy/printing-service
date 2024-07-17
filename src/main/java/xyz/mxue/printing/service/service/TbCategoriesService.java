package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.entity.TbCategories;
import xyz.mxue.printing.service.entity.dto.CategoriesDetailsDTO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 账单分类类型 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
public interface TbCategoriesService extends IService<TbCategories> {

    PageInfo<TbCategories> page(int start, int length, int draw, TbCategories tbCategories);

    List<CategoriesDetailsDTO> queryMoneyAndCategoriesByTime(Date startTime, Date endTime);
}

package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.entity.TbOrderMonth;

import java.util.Date;

/**
 * <p>
 * 月记录 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
public interface TbOrderMonthService extends IService<TbOrderMonth> {

    PageInfo<TbOrderMonth> page(int start, int length, int draw, TbOrderMonth tbOrderMonth);

    String monthRecord(Date date);
}

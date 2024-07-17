package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.entity.TbOrderYear;

import java.util.Date;

/**
 * <p>
 * 年记录 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
public interface TbOrderYearService extends IService<TbOrderYear> {

    PageInfo<TbOrderYear> page(int start, int length, int draw, TbOrderYear tbOrderYear);

    String yearRecord(Date date);
}

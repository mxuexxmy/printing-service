package xyz.mxue.printing.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.mxue.printing.service.entity.TbPrintfInfo;

import java.util.List;

/**
 * <p>
 * 打印信息 Mapper 接口
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-23
 */
public interface TbPrintfInfoMapper extends BaseMapper<TbPrintfInfo> {

    List<TbPrintfInfo> queryPrintfInfos(@Param(value = "orderId") Long orderId);
}

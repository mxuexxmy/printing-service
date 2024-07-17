package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.entity.TbPrintfInfo;

import java.util.List;

/**
 * <p>
 * 打印信息 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-23
 */
public interface TbPrintfInfoService extends IService<TbPrintfInfo> {

    List<TbPrintfInfo> queryPrintfInfos(Long id);
}

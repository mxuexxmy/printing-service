package xyz.mxue.printing.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.mxue.printing.service.entity.TbPrintfInfo;
import xyz.mxue.printing.service.mapper.TbPrintfInfoMapper;
import xyz.mxue.printing.service.service.TbPrintfInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 打印信息 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-23
 */
@Service
public class TbPrintfInfoServiceImpl extends ServiceImpl<TbPrintfInfoMapper, TbPrintfInfo> implements TbPrintfInfoService {

    @Resource
    private TbPrintfInfoMapper printfInfoMapper;

    @Override
    public List<TbPrintfInfo> queryPrintfInfos(Long orderId) {
        return printfInfoMapper.queryPrintfInfos(orderId);
    }
}

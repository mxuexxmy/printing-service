package xyz.mxue.printing.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.printing.service.entity.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-08
 */
public interface TbUserService extends IService<TbUser> {

    TbUser getByUsername(String userPhone, String password);
}

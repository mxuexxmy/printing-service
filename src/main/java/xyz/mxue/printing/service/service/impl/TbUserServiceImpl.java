package xyz.mxue.printing.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.mxue.printing.service.entity.TbUser;
import xyz.mxue.printing.service.mapper.TbUserMapper;
import xyz.mxue.printing.service.service.TbUserService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-08
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Resource
    private TbUserMapper userMapper;

    @Override
    public TbUser getByUsername(String userPhone, String password) {
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", userPhone);
        TbUser user = userMapper.selectOne(wrapper);
        if (user != null) {
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            // 判断密码是否相等
            if (md5Password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}

package org.xuxi.codex.db.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.xuxi.codex.common.constant.CommonConstant;
import org.xuxi.codex.common.exceptions.RException;
import org.xuxi.codex.common.utils.DateUtil;
import org.xuxi.codex.common.utils.ShiroUtils;
import org.xuxi.codex.common.utils.SnowflakeIdWorker;
import org.xuxi.codex.db.entity.UserEntity;
import org.xuxi.codex.db.mapper.UserMapper;
import org.xuxi.codex.db.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {


    @Override
    public UserEntity getUserByName(String userName) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);

        return baseMapper.selectOne(userEntity);
    }


    /**
     * 创建用户
     *
     * @param entity
     * @return
     */
    @Override
    public boolean insert(UserEntity entity) {
        entity.setId(SnowflakeIdWorker.getId());
        entity.setSalt(RandomStringUtils.randomAlphanumeric(20));
        entity.setPassword(ShiroUtils.sha256(entity.getPassword(), entity.getSalt()));
        entity.setCreateTime(DateUtil.getTime());
        entity.setType(CommonConstant.UserType.Ordinary.getValue());
        return super.insert(entity);
    }

    @Override
    public boolean updateById(UserEntity entity) {
        entity.setUpdateTime(DateUtil.getTime());
        return super.updateById(entity);
    }

    @Override
    public void modifyPasswd(String userId, String oldPasswd, String passwd) {

        UserEntity userEntity = baseMapper.selectById(userId);

        if (!userEntity.getPassword().equals(ShiroUtils.sha256(oldPasswd, userEntity.getSalt()))) {
            throw new RException("原密码不正确");
        }

        userEntity.setSalt(RandomStringUtils.randomAlphanumeric(20));
        userEntity.setPassword(ShiroUtils.sha256(passwd, userEntity.getSalt()));
        userEntity.setType(CommonConstant.UserType.Ordinary.getValue());
        baseMapper.updateById(userEntity);
    }
}

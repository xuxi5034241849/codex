package org.xuxi.codex.db.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
}

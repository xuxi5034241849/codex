package org.xuxi.codex.db.service;

import com.baomidou.mybatisplus.service.IService;
import org.xuxi.codex.db.entity.UserEntity;


/**
 * 用户信息
 *
 * @author xuxi
 * @email 461720498@qq.com
 * @date 2018-09-03 16:54:09
 */
public interface UserService extends IService<UserEntity> {


    /**
     * 根据用户名查找用户
     * 无论是否查询都正常返回
     *
     * @param userName
     * @return
     */
    UserEntity getUserByName(String userName);


}


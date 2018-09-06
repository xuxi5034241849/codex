package org.xuxi.codex.shiro.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xuxi.codex.db.entity.UserEntity;
import org.xuxi.codex.shiro.utils.TokenGenerator;

import java.util.concurrent.TimeUnit;


/**
 * Token 登录集合
 */
@Service
public class TokenService {

    private final String PREFIX = "TOKEN-ID:";

    @Autowired
    private RedisTemplate redisTemplate;


    //2小时后过期
    private final static int EXPIRE = 3600 * 2;


    /**
     * 创建 Token
     *
     * @param user
     * @return
     */
    public String createToken(UserEntity user) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //判断是否生成过token
        UserContextInfo userContextInfo = getReidsToken(token);
        if (userContextInfo == null) {
            //保存token
            userContextInfo = new UserContextInfo();
            userContextInfo.setUserId(user.getId());
            userContextInfo.setEmail(user.getEmail());
            userContextInfo.setName(user.getName());
            userContextInfo.setTelephone(user.getTelephone());
            userContextInfo.setUserName(user.getUserName());
            userContextInfo.setToken(token);
            setReidsToken(token, userContextInfo);
        } else {
            //更新token
            userContextInfo.setToken(token);
            setReidsToken(token, userContextInfo);
        }


        return token;
    }


    /**
     * Token 根据token获取 UserContextInfo
     *
     * @param token
     * @return
     */
    public UserContextInfo getTokenEntity(String token) {
        UserContextInfo userContextInfo = getReidsToken(token);
        return userContextInfo;
    }


    /**
     * 删除TOKEN
     *
     * @param token
     */
    public void cleanToken(String token) {
        redisTemplate.delete(PREFIX + token);
    }

    /**
     * redis获取token
     *
     * @param token
     * @return
     */
    private UserContextInfo getReidsToken(String token) {
        return (UserContextInfo) redisTemplate.opsForValue().get(PREFIX + token);
    }


    /**
     * redise设置token
     *
     * @param token
     * @param userContextInfo
     */
    private void setReidsToken(String token, UserContextInfo userContextInfo) {
        redisTemplate.opsForValue().set(PREFIX + token, userContextInfo);
        //60分钟过期
        redisTemplate.expire(PREFIX + token, EXPIRE, TimeUnit.MINUTES);
    }

}

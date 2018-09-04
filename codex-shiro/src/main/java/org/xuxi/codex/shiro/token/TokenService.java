package org.xuxi.codex.shiro.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xuxi.codex.shiro.utils.TokenGenerator;

import java.util.concurrent.TimeUnit;


@Service
public class TokenService {

    private final String PREFIX = "TOKEN-ID:";

    @Autowired
    private RedisTemplate redisTemplate;


    //2小时后过期
    private final static int EXPIRE = 3600 * 2;


    /**
     * 创建 Token
     * @param userId
     * @return
     */
    public String createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //判断是否生成过token
        TokenEntity tokenEntity = getReidsToken(userId);
        if (tokenEntity == null) {
            //保存token
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            setReidsToken(userId, tokenEntity);
        } else {
            //更新token
            tokenEntity.setToken(token);
            setReidsToken(userId, tokenEntity);
        }


        return token;
    }


    /**
     * redis获取token
     *
     * @param userId
     * @return
     */
    private TokenEntity getReidsToken(long userId) {
        return (TokenEntity) redisTemplate.opsForValue().get(PREFIX + userId);
    }


    /**
     * redise设置token
     *
     * @param userId
     * @param tokenEntity
     */
    private void setReidsToken(long userId, TokenEntity tokenEntity) {
        redisTemplate.opsForValue().set(PREFIX + userId, tokenEntity);
        //60分钟过期
        redisTemplate.expire(PREFIX + userId, EXPIRE, TimeUnit.MINUTES);
    }


}

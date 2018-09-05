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
     *
     * @param userId
     * @return
     */
    public String createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //判断是否生成过token
        TokenEntity tokenEntity = getReidsToken(token);
        if (tokenEntity == null) {
            //保存token
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            setReidsToken(token, tokenEntity);
        } else {
            //更新token
            tokenEntity.setToken(token);
            setReidsToken(token, tokenEntity);
        }


        return token;
    }


    /**
     * Token 根据token获取 TokenEntity
     *
     * @param token
     * @return
     */
    public TokenEntity getTokenEntity(String token) {
        TokenEntity tokenEntity = getReidsToken(token);
        return tokenEntity;
    }


    /**
     * redis获取token
     *
     * @param token
     * @return
     */
    private TokenEntity getReidsToken(String token) {
        return (TokenEntity) redisTemplate.opsForValue().get(PREFIX + token);
    }


    /**
     * redise设置token
     *
     * @param token
     * @param tokenEntity
     */
    private void setReidsToken(String token, TokenEntity tokenEntity) {
        redisTemplate.opsForValue().set(PREFIX + token, tokenEntity);
        //60分钟过期
        redisTemplate.expire(PREFIX + token, EXPIRE, TimeUnit.MINUTES);
    }


}

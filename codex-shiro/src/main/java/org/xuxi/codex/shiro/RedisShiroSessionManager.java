package org.xuxi.codex.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * shiro session 管理
 */
public class RedisShiroSessionManager extends EnterpriseCacheSessionDAO {

    private final String PREFIX = "SESSION-ID:";


    @Autowired
    private RedisTemplate redisTemplate;

    //创建session
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        final String key = PREFIX + sessionId.toString();
        setShiroSession(key, session);
        return sessionId;
    }

    //获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            final String key = PREFIX + sessionId.toString();
            session = getShiroSession(key);
        }
        return session;
    }

    //更新session
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        final String key = PREFIX + session.getId().toString();
        setShiroSession(key, session);
    }

    //删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        final String key = PREFIX + session.getId().toString();
        redisTemplate.delete(key);
    }


    private Session getShiroSession(String key) {
        return (Session) redisTemplate.opsForValue().get(key);
    }


    private void setShiroSession(String key, Session session) {
        redisTemplate.opsForValue().set(key, session);
        //60分钟过期
        redisTemplate.expire(key, 60, TimeUnit.MINUTES); // todo 这里应该常量
    }

}

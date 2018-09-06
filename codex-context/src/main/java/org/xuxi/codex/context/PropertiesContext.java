package org.xuxi.codex.context;


import org.xuxi.codex.common.utils.DateUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 这里为用户属性上下文
 * 用户的主要属性从这里获取
 *
 * @author xuxi
 */
public class PropertiesContext {


    private static final ThreadLocal<PropertiesContext> threadLocalContext = new ThreadLocal<PropertiesContext>();

    private Map<String, String> propertiesMap;


    /**
     * 安全的获取线程本地变量的MAP值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return propertiesMap.get(key);
    }


    /**
     * 获取上下文
     *
     * @return
     */
    public static PropertiesContext getContext() {
        return threadLocalContext.get();
    }

    /**
     * 构建一个 PropertiesContext
     *
     * @return
     */
    public static PropertiesContext build() {

        threadLocalContext.set(new PropertiesContext());

        return threadLocalContext.get();
    }

    /**
     * 私有构造方法
     */
    private PropertiesContext() {
        this.propertiesMap = new LinkedHashMap();
        this.propertiesMap.put("entityPackagePath", "com.wonders.iron.db.entity");
        this.propertiesMap.put("mapperPackagePath", "com.wonders.iron.db.mapper");
        this.propertiesMap.put("servicePackagePath", "com.wonders.iron.db.service");
        this.propertiesMap.put("serviceImplPackagePath", "com.wonders.iron.db.service.impl");
        this.propertiesMap.put("mapperXmlPackagePath", "mapper");
        this.propertiesMap.put("email", "461720498@qq.com");
        this.propertiesMap.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN) );
        this.propertiesMap.put("author", "xuxi");


    }


}
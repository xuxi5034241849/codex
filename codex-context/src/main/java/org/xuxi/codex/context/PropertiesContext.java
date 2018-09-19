package org.xuxi.codex.context;


import org.xuxi.codex.common.utils.DateUtil;
import org.xuxi.codex.common.utils.SpringContextUtil;
import org.xuxi.codex.db.service.TemplateService;

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
    public static PropertiesContext build(String templateId) {

        threadLocalContext.set(new PropertiesContext(templateId));

        return threadLocalContext.get();
    }

    /**
     * 私有构造方法
     */
    private PropertiesContext(String templateId) {

        this.propertiesMap = SpringContextUtil.getBean(TemplateService.class).getConfig(templateId);
        this.propertiesMap.put("email", "461720498@qq.com");
        this.propertiesMap.put("datetime", DateUtil.getDateTime());
        this.propertiesMap.put("author", "xuxi");
    }


}

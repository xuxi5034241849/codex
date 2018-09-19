package org.xuxi.codex.db.service;

import com.baomidou.mybatisplus.service.IService;
import org.xuxi.codex.db.entity.TemplateEntity;
import org.xuxi.codex.db.entity.TemplateKeyConfigEntity;

import java.util.List;
import java.util.Map;


/**
 * 模板
 *
 * @author xuxi
 * @email 461720498@qq.com
 * @date 20180907165600
 */
public interface TemplateService extends IService<TemplateEntity> {


    /**
     * 模板配置保存
     *
     * @param templateEntity
     * @return
     */
    String addTemplateConfig(String userId, TemplateEntity templateEntity, List<TemplateKeyConfigEntity> templateKeyConfigEntityList);


    /**
     * 根据templateId 查询配置
     *
     * @param templateId
     * @return
     */
    Map<String, String> getConfig(String templateId);


    /**
     * 根据用户和类型查询列表
     *
     * @param userId
     * @param type
     * @return
     */
    List<TemplateEntity> getTemplateList(String userId, int type);
}


package org.xuxi.codex.db.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xuxi.codex.common.utils.DateUtil;
import org.xuxi.codex.common.utils.SnowflakeIdWorker;
import org.xuxi.codex.db.entity.TemplateEntity;
import org.xuxi.codex.db.entity.TemplateKeyConfigEntity;
import org.xuxi.codex.db.mapper.TemplateMapper;
import org.xuxi.codex.db.service.TemplateKeyConfigService;
import org.xuxi.codex.db.service.TemplateService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service("templateService")
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, TemplateEntity> implements TemplateService {

    @Autowired
    private TemplateKeyConfigService templateKeyConfigService;

    @Transactional
    @Override
    public String addTemplateConfig(String userId, TemplateEntity templateEntity, List<TemplateKeyConfigEntity> templateKeyConfigEntityList) {

        templateEntity.setTemplateId(SnowflakeIdWorker.getId());
        templateEntity.setCreateTime(DateUtil.getTime());
        templateEntity.setUserId(userId);
        baseMapper.insert(templateEntity);


        templateKeyConfigEntityList.stream().forEach(templateKeyConfigEntity -> {
            templateKeyConfigEntity.setId(SnowflakeIdWorker.getId());
            templateKeyConfigEntity.setUserId(userId);
            templateKeyConfigEntity.setTemplateId(templateEntity.getTemplateId());
            templateKeyConfigService.insert(templateKeyConfigEntity);
        });


        return templateEntity.getTemplateId();
    }


    @Override
    public Map<String, String> getConfig(String templateId) {

        Map<String, String> result = new LinkedHashMap<>();

        EntityWrapper<TemplateKeyConfigEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("template_id", templateId);
        List<TemplateKeyConfigEntity> list = templateKeyConfigService.selectList(wrapper);
        list.stream().forEach(templateKeyConfigEntity -> {
            result.put(templateKeyConfigEntity.getKey(), templateKeyConfigEntity.getValue());
        });

        return result;
    }

    @Override
    public List<TemplateEntity> getTemplateList(String userId, int type) {
        EntityWrapper<TemplateEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId)
                .eq("type", type);

        return baseMapper.selectList(wrapper);
    }
}

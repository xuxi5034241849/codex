package org.xuxi.codex.db.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xuxi.codex.db.entity.TemplateKeyConfigEntity;
import org.xuxi.codex.db.mapper.TemplateKeyConfigMapper;
import org.xuxi.codex.db.service.TemplateKeyConfigService;


@Service("templateKeyConfigService")
public class TemplateKeyConfigServiceImpl extends ServiceImpl<TemplateKeyConfigMapper, TemplateKeyConfigEntity> implements TemplateKeyConfigService {


}

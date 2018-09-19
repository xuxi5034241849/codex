package org.xuxi.codex.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xuxi.codex.common.constant.CommonConstant;
import org.xuxi.codex.common.models.R;
import org.xuxi.codex.common.valid.VG;
import org.xuxi.codex.db.entity.TemplateEntity;
import org.xuxi.codex.db.entity.TemplateKeyConfigEntity;
import org.xuxi.codex.db.service.TemplateService;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板
 */
@RestController
@RequestMapping("template")
public class TemplateController extends AbstractController {

    @Autowired
    private TemplateService templateService;

    /**
     * 设置模板，并配置信息
     *
     * @param templateEntity
     * @return
     */
    @PostMapping("config-put")
    public R putConfig(@RequestBody @Validated(value = TemplateEntity.Template1.class) TemplateEntity templateEntity) {

        List<TemplateKeyConfigEntity> templateKeyConfigEntitiesList = new ArrayList();
        templateKeyConfigEntitiesList.add(new TemplateKeyConfigEntity("entityPackagePath", templateEntity.getEntityPackagePath()));
        templateKeyConfigEntitiesList.add(new TemplateKeyConfigEntity("mapperPackagePath", templateEntity.getMapperPackagePath()));
        templateKeyConfigEntitiesList.add(new TemplateKeyConfigEntity("servicePackagePath", templateEntity.getServicePackagePath()));
        templateKeyConfigEntitiesList.add(new TemplateKeyConfigEntity("serviceImplPackagePath", templateEntity.getServiceImplPackagePath()));
        templateKeyConfigEntitiesList.add(new TemplateKeyConfigEntity("mapperXmlPackagePath", templateEntity.getMapperXmlPackagePath()));

        templateEntity.setType(CommonConstant.TemplateType.CRUD.getValue());

        templateService.addTemplateConfig(getUser().getUserId(), templateEntity, templateKeyConfigEntitiesList);

        return R.ok();
    }


    /**
     * 获取配置
     *
     * @param templateEntity
     * @return
     */
    @PostMapping("get-config")
    public R getConfig(@RequestBody @Validated(value = VG.Get.class) TemplateEntity templateEntity) {

        return R.ok().data(templateService.getConfig(templateEntity.getTemplateId()));
    }

    /**
     * 查询CRUD的模板名称
     *
     * @param templateEntity
     * @return
     */
    @PostMapping("list")
    public R list(@RequestBody @Validated(value = VG.List.class) TemplateEntity templateEntity) {

        return R.ok().data(templateService.getTemplateList(getUser().getUserId(), templateEntity.getType()));
    }

}

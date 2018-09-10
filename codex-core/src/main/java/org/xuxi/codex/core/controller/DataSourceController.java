package org.xuxi.codex.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xuxi.codex.common.utils.R;
import org.xuxi.codex.common.valid.VG;
import org.xuxi.codex.db.entity.DataSourceEntity;
import org.xuxi.codex.db.service.DataSourceService;

/**
 * 数据源链接信息配置接口
 */
@RestController
@RequestMapping("datasource")
public class DataSourceController extends AbstractController {

    @Autowired
    private DataSourceService dataSourceService;


    /**
     * 查询这个用户下的所有数据源一个数据源
     *
     * @return
     */
    @PostMapping(value = "list")
    public R list() {

        return R.ok().data(dataSourceService.getListByUser(getUser().getUserId()));
    }


    /**
     * 设置一个数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @PostMapping(value = "put")
    public R create(@Validated(VG.Add.class) @RequestBody DataSourceEntity dataSourceEntity) {
        dataSourceEntity.setUserId(getUser().getUserId());

        dataSourceService.insert(dataSourceEntity);

        return R.ok().data(dataSourceService.selectById(dataSourceEntity.getId()));
    }


    /**
     * 修改一个数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @PostMapping(value = "modify")
    public R modify(@Validated(VG.Update.class) @RequestBody DataSourceEntity dataSourceEntity) {
        dataSourceEntity.setUserId(getUser().getUserId());

        dataSourceService.updateById(dataSourceEntity);

        return R.ok().data(dataSourceService.selectById(dataSourceEntity.getId()));
    }

    /**
     * 查询一个数据源详情
     *
     * @param dataSourceEntity
     * @return
     */
    @PostMapping(value = "detail")
    public R detail(@Validated(VG.Get.class) @RequestBody DataSourceEntity dataSourceEntity) {

        return R.ok().data(dataSourceService.selectById(dataSourceEntity.getId()));
    }

    /**
     * 删除一个数据源
     *
     * @param dataSourceEntity
     * @return
     */
    @PostMapping(value = "del")
    public R del(@Validated(VG.Delete.class) @RequestBody DataSourceEntity dataSourceEntity) {

        dataSourceService.deleteById(dataSourceEntity.getId());

        return R.ok();
    }

}

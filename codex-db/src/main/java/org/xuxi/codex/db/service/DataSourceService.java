package org.xuxi.codex.db.service;

import com.baomidou.mybatisplus.service.IService;
import org.xuxi.codex.db.entity.DataSourceEntity;

import java.util.List;


/**
 * 数据源链接信息
 *
 * @author xuxi
 * @email 461720498@qq.com
 * @date 2018-09-07 13:07:48
 */
public interface DataSourceService extends IService<DataSourceEntity> {

    /**
     * 根据用户查询数据源信息
     *
     * @param userId
     * @return
     */
    List<DataSourceEntity> getListByUser(Long userId);

}


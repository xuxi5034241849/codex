package org.xuxi.codex.db.service;

import com.baomidou.mybatisplus.service.IService;
import org.xuxi.codex.db.entity.DataSourceEntity;

import java.util.List;
import java.util.Map;


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
    List<DataSourceEntity> getListByUser(String userId);


    /**
     * 查询数据源下所有的表信息
     * @param map
     * @return
     */
    List<Map<String, Object>> queryTableList(Map<String, Object> map);

    /**
     * 查询数据源下所有的表
     * @param map
     * @return
     */
    int queryTableTotal(Map<String, Object> map);


}


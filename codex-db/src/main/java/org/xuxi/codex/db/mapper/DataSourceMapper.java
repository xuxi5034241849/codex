package org.xuxi.codex.db.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface DataSourceMapper extends BaseMapper<DataSourceEntity> {

    /**
     * 查询当前数据源所有的表
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> queryTableList(Map<String, Object> map);

    /**
     * 查询当前数据源所有的表 的total
     *
     * @param map
     * @return
     */
    int queryTableTotal(Map<String, Object> map);

}

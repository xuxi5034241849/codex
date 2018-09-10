package org.xuxi.codex.db.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xuxi.codex.common.utils.DateUtil;
import org.xuxi.codex.common.utils.SnowflakeIdWorker;
import org.xuxi.codex.db.entity.DataSourceEntity;
import org.xuxi.codex.db.mapper.DataSourceMapper;
import org.xuxi.codex.db.service.DataSourceService;

import java.util.List;


@Service("dataSourceService")
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSourceEntity> implements DataSourceService {


    public List<DataSourceEntity> getListByUser(Long userId) {
        EntityWrapper<DataSourceEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        return baseMapper.selectList(wrapper);
    }


    @Override
    public boolean insert(DataSourceEntity entity) {
        entity.setId(SnowflakeIdWorker.getId());
        entity.setCreateTime(DateUtil.getTime());
        return super.insert(entity);
    }


    @Override
    public boolean updateById(DataSourceEntity entity) {
        entity.setUpdateTime(DateUtil.getTime());
        return super.updateById(entity);
    }
}

package org.xuxi.codex.db.conf.datasources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.xuxi.codex.common.exceptions.CodeDefined;
import org.xuxi.codex.common.exceptions.RException;
import org.xuxi.codex.common.utils.SpringContextUtil;
import org.xuxi.codex.db.entity.DataSourceEntity;
import org.xuxi.codex.db.service.DataSourceService;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    private Map<Object, Object> targetDataSources;

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {

        //设置默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();

        this.targetDataSources = targetDataSources;
    }




    @Override
    protected Object determineCurrentLookupKey() {
        //获取数据源，没有指定，则为默认数据源
        return getDataSource();
    }


    /**
     * 切换数据源
     *
     * @param dataSourceId
     */
    public void setDataSource(String dataSourceId) {

        DataSourceEntity dataSourceEntity = SpringContextUtil.getBean(DataSourceService.class).selectById(dataSourceId);

        if (dataSourceEntity == null) {
            throw new RException(CodeDefined.CODE_5002);
        }

        targetDataSources.put(DataSourceNames.DYNAMIC, dataSourceEntity.buildDataSource()); // 设置动态数据源

        contextHolder.set(DataSourceNames.DYNAMIC); // 使用动态数据源.
    }

    /**
     * 切回默认数据源
     */
    public void clearDataSource() {
        contextHolder.remove();
    }


    private static String getDataSource() {
        return contextHolder.get();
    }

}

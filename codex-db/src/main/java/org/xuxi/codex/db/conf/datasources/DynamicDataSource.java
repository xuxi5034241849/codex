package org.xuxi.codex.db.conf.datasources;

import org.springframework.beans.factory.annotation.Autowired;
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


    private DataSource defaultTargetDataSource;

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        //设置默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();

        this.defaultTargetDataSource = defaultTargetDataSource;
    }


    /**
     * 构建并切换一个数据源信息
     */
    public void rebuildDataSource(DataSource dataSource) {

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.DYNAMIC, dataSource);

        //设置默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);  //  这里覆盖原数据源，可能存在不安全 todo 请注意 这里必须要测试
        super.setTargetDataSources(targetDataSources);              //  比如：正常请求切换数据源，这时候同一时间，第二个请求请求过来，会把第一个请求的数据源覆盖
        super.afterPropertiesSet();
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

        this.rebuildDataSource(dataSourceEntity.buildDataSource());
        contextHolder.set(DataSourceNames.DYNAMIC);
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

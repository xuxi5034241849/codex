package org.xuxi.codex.stream.stream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.xuxi.codex.db.conf.datasources.DynamicDataSource;
import org.xuxi.codex.db.service.SysGeneratorService;
import org.xuxi.codex.stream.TableCodexTemplate;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * TABLE 相关coding 的template执行器
 */
@Controller
public class TableCodexTemplateStream {

    @Autowired
    private List<TableCodexTemplate> tableCodexTemplateList;


    @Autowired
    private SysGeneratorService sysGeneratorService;

    @Autowired
    private DynamicDataSource dynamicDataSource;



    /**
     * foreach template
     *
     * @param tableName
     */
    public byte[] doTemplate(String tableName, String dataSourceId) {

        // 压缩包
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // 切换数据源
        dynamicDataSource.setDataSource(dataSourceId);

        // 表&列信息
        Map<String, String> table = getTable(tableName);
        List<Map<String, String>> columns = getColumns(tableName);

        // 还原数据源
        dynamicDataSource.clearDataSource();

        //遍历
        tableCodexTemplateList.stream().forEach(tableCodexTemplate -> {
            tableCodexTemplate.setComponent(table, columns, zip);
            tableCodexTemplate.coding();
        });

        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


    /**
     * 查询表信息
     *
     * @param tableName
     * @return
     */
    private Map<String, String> getTable(String tableName) {
        return sysGeneratorService.queryTable(tableName);
    }

    /**
     * 查询列信息
     *
     * @param tableName
     * @return
     */
    private List<Map<String, String>> getColumns(String tableName) {
        return sysGeneratorService.queryColumns(tableName);
    }


}

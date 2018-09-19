package org.xuxi.codex.core.controller.models;


import javax.validation.constraints.NotBlank;

public class GeneratorEntity {

    /**
     * 数据源ID
     */
    @NotBlank()
    private String dataSourceId;

    /**
     * 表名
     */
    @NotBlank()
    private String tableName;

    /**
     * 配置
     */
    @NotBlank()
    private String templateId;


    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}

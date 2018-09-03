package org.xuxi.codex.stream;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xuxi.codex.common.utils.RRException;
import org.xuxi.codex.db.entity.ColumnEntity;
import org.xuxi.codex.db.entity.TableEntity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * TABLE相关的 实现的抽象类
 * 根据 数据库表 coding 的template 写这里
 *
 * @author xuxi
 */


public abstract class TableCodexTemplate implements CodexTemplate {


    /**
     * 表信息
     */
    protected TableEntity tableEntity = new TableEntity();

    /**
     * 压缩包信息
     */
    protected ZipOutputStream zip;


    /**
     * 渲染模板
     *
     * @param templateName
     * @param map
     * @param filepath
     */
    protected void setTemplate(String templateName, Map<String, Object> map, String filepath) {
        //渲染模板
        Template template = getTemplate(templateName);
        VelocityContext context = new VelocityContext(map);
        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        //添加到zip
        try {
            zip.putNextEntry(new ZipEntry(filepath));
            IOUtils.write(sw.toString(), zip, "UTF-8");
            IOUtils.closeQuietly(sw);
            zip.closeEntry();
        } catch (IOException e) {
            throw new RRException("渲染模板失败，表名：" + tableEntity.getClassName(), e);
        }
    }

    /**
     * 设置 组建信息
     *
     * @param table
     * @param columns
     * @param zip
     */
    public void setComponent(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {

        // 表信息
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));

        String className = tableToJava(tableEntity.getTableName(), ""); // todo 表名前缀忽略
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));


        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = getConfig().getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);

            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        // zip
        this.zip = zip;

    }


    /**
     * 根据名称获取模板
     *
     * @param templateName
     * @return
     */
    protected Template getTemplate(String templateName) {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //模板路径
        final String TEMPLATE_PATH = String.format("template/%s.vm", templateName);
        Template template = Velocity.getTemplate(TEMPLATE_PATH, "UTF-8");

        return template;
    }


    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }


    public static void main(String[] args) {
        System.out.println(TableCodexTemplate.tableToJava("demo", ""));

    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }


    /**
     * 获取文件名
     */
    protected static String buildFilePath(String templateName, String className, String packagePath, boolean isResources) {

        String headPackagePath;

        if (!isResources) {
            headPackagePath = "main" + File.separator + "java" + File.separator;
        } else {
            headPackagePath = "main" + File.separator + "resources" + File.separator;
        }

        return headPackagePath + packagePath.replace(".", File.separator) + File.separator + className + templateName;
    }

}

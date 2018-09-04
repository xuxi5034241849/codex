package org.xuxi.codex.stream.template;

import org.springframework.stereotype.Component;
import org.xuxi.codex.context.PropertiesContext;
import org.xuxi.codex.db.entity.ColumnEntity;
import org.xuxi.codex.stream.TableCodexTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper XML coding 模板
 */
@Component
public class MapperXmlTemplate extends TableCodexTemplate {


    private final String TEMPLATE_NAME = "Mapper.xml";

    @Override
    public void coding() {

        PropertiesContext propertiesContext = PropertiesContext.getContext();

        //表名转换成Java类名
        String entityPackagePath = propertiesContext.getValue("entityPackagePath");
        String mapperPackagePath = propertiesContext.getValue("mapperPackagePath");
        String comments = tableEntity.getComments();
        String className = tableEntity.getClassName();
        String classname = tableEntity.getClassname();
        List<ColumnEntity> columns = tableEntity.getColumns();


        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("entityPackagePath", entityPackagePath);
        map.put("mapperPackagePath", mapperPackagePath);
        map.put("comments", comments);
        map.put("className", className);
        map.put("classname", classname);
        map.put("columns", columns);
        buildTemplate(TEMPLATE_NAME, map, buildFilePath(TEMPLATE_NAME, className,
                propertiesContext.getValue("mapperXmlPackagePath"), true));
    }


}

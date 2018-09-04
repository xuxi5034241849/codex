package org.xuxi.codex.stream.template;

import org.springframework.stereotype.Component;
import org.xuxi.codex.context.PropertiesContext;
import org.xuxi.codex.db.entity.ColumnEntity;
import org.xuxi.codex.stream.TableCodexTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity类coding 模板
 */
@Component
public class EntityTemplate extends TableCodexTemplate {

    private final String TEMPLATE_NAME = "Entity.java";

    @Override
    public void coding() {

        PropertiesContext propertiesContext = PropertiesContext.getContext();

        //表名转换成Java类名
        String entityPackagePath = propertiesContext.getValue("entityPackagePath");
        String author = propertiesContext.getValue("author");
        String email = propertiesContext.getValue("email");
        String datetime = propertiesContext.getValue("datetime");
        String comments = tableEntity.getComments();
        String tableName = tableEntity.getTableName();
        String className = tableEntity.getClassName();
        ColumnEntity pk = tableEntity.getPk();
        List<ColumnEntity> columns = tableEntity.getColumns();


        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("entityPackagePath", entityPackagePath);
        map.put("author", author);
        map.put("email", email);
        map.put("datetime", datetime);
        map.put("comments", comments);
        map.put("tableName", tableName);
        map.put("className", className);
        map.put("columns", columns);
        map.put("pk", pk);
        buildTemplate(TEMPLATE_NAME, map, buildFilePath(TEMPLATE_NAME, className,
                entityPackagePath, false));
    }


}

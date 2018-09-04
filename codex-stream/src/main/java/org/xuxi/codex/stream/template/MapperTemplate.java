package org.xuxi.codex.stream.template;

import org.springframework.stereotype.Component;
import org.xuxi.codex.context.PropertiesContext;
import org.xuxi.codex.stream.TableCodexTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper类coding 模板
 */
@Component
public class MapperTemplate extends TableCodexTemplate {


    private final String TEMPLATE_NAME = "Mapper.java";

    @Override
    public void coding() {

        PropertiesContext propertiesContext = PropertiesContext.getContext();

        //表名转换成Java类名
        String mapperPackagePath = propertiesContext.getValue("mapperPackagePath");
        String entityPackagePath = propertiesContext.getValue("entityPackagePath");
        String author = propertiesContext.getValue("author");
        String email = propertiesContext.getValue("email");
        String datetime = propertiesContext.getValue("datetime");
        String comments = tableEntity.getComments();
        String className = tableEntity.getClassName();


        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("mapperPackagePath", mapperPackagePath);
        map.put("entityPackagePath", entityPackagePath);
        map.put("author", author);
        map.put("email", email);
        map.put("datetime", datetime);
        map.put("comments", comments);
        map.put("className", className);
        buildTemplate(TEMPLATE_NAME, map, buildFilePath(TEMPLATE_NAME, className,
                mapperPackagePath, false));
    }
}

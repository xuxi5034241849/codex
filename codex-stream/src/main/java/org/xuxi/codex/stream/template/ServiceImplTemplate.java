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
public class ServiceImplTemplate extends TableCodexTemplate {


    private final String TEMPLATE_NAME = "ServiceImpl.java";

    @Override
    public void coding() {

        PropertiesContext propertiesContext = PropertiesContext.getContext();

        //表名转换成Java类名
        String entityPackagePath = propertiesContext.getValue("entityPackagePath");
        String mapperPackagePath = propertiesContext.getValue("mapperPackagePath");
        String servicePackagePath = propertiesContext.getValue("servicePackagePath");
        String author = propertiesContext.getValue("author");
        String email = propertiesContext.getValue("email");
        String datetime = propertiesContext.getValue("datetime");
        String comments = tableEntity.getComments();
        String className = tableEntity.getClassName();
        String classname = tableEntity.getClassname();


        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("entityPackagePath", entityPackagePath);
        map.put("mapperPackagePath", mapperPackagePath);
        map.put("servicePackagePath", servicePackagePath);
        map.put("author", author);
        map.put("email", email);
        map.put("datetime", datetime);
        map.put("comments", comments);
        map.put("className", className);
        map.put("classname", classname);
        setTemplate(TEMPLATE_NAME, map, buildFilePath(TEMPLATE_NAME, className,
                propertiesContext.getValue("serviceImplPackagePath"), false));
    }
}

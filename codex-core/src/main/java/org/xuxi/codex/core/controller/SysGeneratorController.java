package org.xuxi.codex.core.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xuxi.codex.context.PropertiesContext;
import org.xuxi.codex.stream.stream.TableCodexTemplateStream;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 代码生成器
 */
@Controller
@RequestMapping("/generator")
public class SysGeneratorController {

    @Autowired
    private TableCodexTemplateStream tableCodexTemplateStream;

    /**
     * 生成代码
     */
    @GetMapping("/code/{dataSourceId}/{tableName}/{templateId}")
    public void code(HttpServletResponse response, @PathVariable String dataSourceId, @PathVariable String tableName, @PathVariable String templateId) throws IOException {

        PropertiesContext.build(templateId);

        byte[] data = tableCodexTemplateStream.doTemplate(tableName, dataSourceId);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + tableName + ".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}

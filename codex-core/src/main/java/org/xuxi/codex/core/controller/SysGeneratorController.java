package org.xuxi.codex.core.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/code")
    public void code(HttpServletResponse response, String tableName) throws IOException {

        PropertiesContext.build();

        byte[] data = tableCodexTemplateStream.doTemplate(tableName);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"codex.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}

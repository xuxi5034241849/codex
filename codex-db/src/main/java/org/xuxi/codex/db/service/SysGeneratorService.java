package org.xuxi.codex.db.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuxi.codex.db.mapper.SysGeneratorMapper;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 */
@Service
public class SysGeneratorService {


    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return sysGeneratorMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return sysGeneratorMapper.queryTotal(map);
    }

    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }

    public byte[] generatorCode(String... tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
//            GenUtils.generatorCode(table, columns, zip); todo 等会
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


}

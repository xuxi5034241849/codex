package org.xuxi.codex.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xuxi.codex.db.mapper.SysGeneratorMapper;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 */
@Service
public class SysGeneratorService {


    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }

}

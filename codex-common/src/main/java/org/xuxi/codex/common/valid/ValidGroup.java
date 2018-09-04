package org.xuxi.codex.common.valid;

/**
 * 使用jsr-bean验证规范
 *
 */
@SuppressWarnings(value = "all")
public interface ValidGroup {

    // 新增
    interface Add {
    }

    // 删除
    interface Delete {
    }

    // 批量删除
    interface BatDelete {
    }

    // 查询单条记录
    interface Get {
    }

    // 列表查询
    interface List {
    }

    // 更新
    interface Update {
    }

    // 登录
    interface Login {
    }


}

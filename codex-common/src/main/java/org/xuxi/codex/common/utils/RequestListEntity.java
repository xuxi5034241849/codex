package org.xuxi.codex.common.utils;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求参数
 *
 */

@Getter
@Setter
public class RequestListEntity {


    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNum;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}

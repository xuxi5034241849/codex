package org.xuxi.codex.common.exceptions;

import java.io.Serializable;

/**
 * 通用常量定义接口
 */
public interface IEnum<T, D> extends Serializable {
    /**
     * 得到常量key或值
     * @return
     */
    T getValue();

    /**
     * 得到常量的定义或描述
     * 如果国际化,在这里实现
     * @return
     */
    D getDesc();
}

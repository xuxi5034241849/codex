package org.xuxi.codex.core.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xuxi.codex.shiro.token.UserContextInfo;

/**
 * Controller公共组件
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected UserContextInfo getUser() {
        return (UserContextInfo) SecurityUtils.getSubject().getPrincipal();
    }

}

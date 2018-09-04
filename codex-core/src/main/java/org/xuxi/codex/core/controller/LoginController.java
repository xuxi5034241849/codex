package org.xuxi.codex.core.controller;


import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xuxi.codex.common.utils.R;
import org.xuxi.codex.common.valid.ValidGroup;
import org.xuxi.codex.db.entity.UserEntity;
import org.xuxi.codex.shiro.ShiroUtils;


@RestController
public class LoginController {

    @PostMapping("/login")
    public R login(@RequestBody  @Validated(ValidGroup.Login.class) UserEntity userEntity) {

        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUserName(), userEntity.getPassword());
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("登录名或密码不正确!");
        }

        return R.ok();
    }


    @PostMapping("/login/out")
    @ResponseBody
    public R out() {


        return R.ok();
    }


}




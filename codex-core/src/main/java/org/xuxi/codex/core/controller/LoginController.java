package org.xuxi.codex.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xuxi.codex.common.models.R;
import org.xuxi.codex.common.valid.VG;
import org.xuxi.codex.db.entity.UserEntity;
import org.xuxi.codex.db.service.UserService;
import org.xuxi.codex.shiro.token.TokenService;
import org.xuxi.codex.shiro.utils.ShiroUtils;


@RestController
public class LoginController extends AbstractController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody @Validated(VG.Login.class) UserEntity userEntity) {


        UserEntity user = userService.getUserByName(userEntity.getUserName());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(ShiroUtils.sha256(userEntity.getPassword(), user.getSalt()))) {
            return R.error("账号或密码不正确");
        }

        return R.ok().put("token", tokenService.createToken(user));
    }


    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/login/out")
    @ResponseBody
    public R out() {

        tokenService.cleanToken(getUser().getToken());

        return R.ok();
    }


}




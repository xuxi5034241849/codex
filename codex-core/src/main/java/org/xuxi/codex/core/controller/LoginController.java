package org.xuxi.codex.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
     * 获取上下文信息
     *
     * @return
     */
    @GetMapping("get-user")
    public R getUserInfo() {

        return R.ok().put("data", getUser());

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


    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public R register(@RequestBody @Validated(VG.Add.class) UserEntity userEntity) {

        userService.insert(userEntity);

        return R.ok();
    }


    /**
     * 修改密码
     *
     * @return
     */
    @PostMapping("/passwd/modify")
    @ResponseBody
    public R modifyPasswd(@RequestBody @Validated(VG.Passwd.class) UserEntity userEntity) {

        userService.modifyPasswd(userEntity.getId(), userEntity.getOldPassword(), userEntity.getPassword());

        return R.ok();
    }


    /**
     * 检查名用户是否重复
     *
     * @return
     */
    @GetMapping("/valid/user/{userName}")
    @ResponseBody
    public R validUser(@PathVariable String userName) {

        if (userService.getUserByName(userName) != null) {
            return R.valid("用户名重复");
        }

        return R.ok();
    }


}




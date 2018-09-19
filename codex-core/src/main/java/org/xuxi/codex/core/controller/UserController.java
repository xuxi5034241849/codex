package org.xuxi.codex.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xuxi.codex.common.models.R;
import org.xuxi.codex.common.valid.VG;
import org.xuxi.codex.db.entity.UserEntity;
import org.xuxi.codex.db.service.UserService;
import org.xuxi.codex.shiro.token.TokenService;
import org.xuxi.codex.shiro.token.UserContextInfo;


@RestController
public class UserController extends AbstractController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


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
     * 修改用户
     *
     * @return
     */
    @PostMapping("/user/modify")
    @ResponseBody
    public R modifyUser(@RequestBody @Validated(VG.Update.class) UserEntity userEntity) {

        UserEntity user = new UserEntity();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());
        user.setTelephone(userEntity.getTelephone());

        // 更新数据库
        userService.updateById(user);

        // 更新token上下文
        tokenService.refreshToken(getUser().getToken(), userService.selectById(userEntity.getId()));

        //根据accessToken，查询用户信息
        UserContextInfo userContextInfo = tokenService.getTokenEntity(getUser().getToken());

        return R.ok().put("data", userContextInfo);
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




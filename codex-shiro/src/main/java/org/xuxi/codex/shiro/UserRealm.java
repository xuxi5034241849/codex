/**
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.xuxi.codex.shiro;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xuxi.codex.db.entity.UserEntity;
import org.xuxi.codex.db.service.UserService;

/**
 * 认证
 */
@Component
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        UserEntity userEntity = userService.getUserByName(token.getUsername());

        //账号不存在
        if (userEntity == null) {
            throw new UnknownAccountException("登录名或密码不正确!");
        }


//        /*sha256加密、设置操作员的密码*/
//        String salt = RandomStringUtils.randomAlphanumeric(20);

        System.out.println(ShiroUtils.sha256(token.getUsername(), "111111"));

        return new SimpleAuthenticationInfo(userEntity, userEntity.getPassword(),
                ByteSource.Util.bytes("111111"), getName());
    }


    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permsSet);

        return null;
    }


    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}

package com.winconlab.clams.shiro;

import com.winconlab.clams.pojo.SysUser;
import com.winconlab.clams.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ClamsRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = sysUserService.findSysUserByUsername(token.getUsername());

        if (sysUser == null)
            throw new AuthenticationException("账号不存在");
        if (sysUser.getLocked().equals("1"))
            throw new AuthenticationException("账号被锁定");

        return new SimpleAuthenticationInfo(sysUser.getUsername(), sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}

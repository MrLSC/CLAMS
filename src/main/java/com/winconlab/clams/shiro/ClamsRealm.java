package com.winconlab.clams.shiro;

import com.winconlab.clams.pojo.*;
import com.winconlab.clams.service.SysPermissionService;
import com.winconlab.clams.service.SysRoleService;
import com.winconlab.clams.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClamsRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = sysUserService.findSysUserByUsername(token.getUsername());

        if (sysUser == null)
            throw new AuthenticationException("账号不存在");
        if (sysUser.getLocked().equals("1"))
            throw new AuthenticationException("账号被锁定");

        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        //查找用户角色
        List<SysUserRole> userRoles = sysRoleService.findRolesByUserId(sysUser.getUserId());
        if (userRoles == null || userRoles.size() <= 0)
            return null;

        Set<String> roles_name = new HashSet<>();
        Set<String> permissions_name = new HashSet<>();

        for (SysUserRole sysUserRole : userRoles) {
            SysRole role = sysRoleService.findRoleByRoleId(sysUserRole.getSysRoleId());
            if (role != null) {
                roles_name.add(role.getName());
                //查找角色对应权限
                List<SysRolePermission> rolePermissions = sysPermissionService.findPermissionsByRoleId(role.getRoleId());
                if (rolePermissions == null || rolePermissions.size() <= 0)
                    continue;
                for (SysRolePermission permission : rolePermissions) {
                    SysPermission sysPermission = sysPermissionService.findPermissionByPermissionId(permission.getSysPermissionId());
                    if (sysPermission == null) continue;
                    permissions_name.add(role.getName() + ":" + sysPermission.getPercode());
                }
            }
        }
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles_name);
        info.setStringPermissions(permissions_name);
        return info;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}

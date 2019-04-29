package com.winconlab.clams.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RoleOrAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);

        //配置的访问所需角色
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        //判断访问所需的角色是否包含当前用户的角色,如果有一个符合放行
        for (String roleName : rolesArray) {
            if (subject.hasRole(roleName)) {
                return true;
            }
        }
        return false;
    }
}

package com.winconlab.clams.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PermOrAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        Subject subject = this.getSubject(servletRequest, servletResponse);
        String[] perms = (String[])  mappedValue;

        if (perms == null && perms.length == 0) {
            return true;
        }

        for (String perm : perms) {
            if (subject.isPermitted(perm))
                return true;
        }

        return false;
    }
}

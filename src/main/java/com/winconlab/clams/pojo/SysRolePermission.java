package com.winconlab.clams.pojo;

public class SysRolePermission {
    private String rolePermId;

    private String sysRoleId;

    private String sysPermissionId;

    public String getRolePermId() {
        return rolePermId;
    }

    public void setRolePermId(String rolePermId) {
        this.rolePermId = rolePermId == null ? null : rolePermId.trim();
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }

    public String getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(String sysPermissionId) {
        this.sysPermissionId = sysPermissionId == null ? null : sysPermissionId.trim();
    }
}
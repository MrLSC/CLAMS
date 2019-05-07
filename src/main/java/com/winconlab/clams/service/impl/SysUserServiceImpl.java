package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.mapper.SysRoleMapper;
import com.winconlab.clams.mapper.SysUserMapper;
import com.winconlab.clams.mapper.SysUserRoleMapper;
import com.winconlab.clams.pojo.*;
import com.winconlab.clams.service.SysUserService;
import com.winconlab.clams.utils.IdUtil;
import com.winconlab.clams.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser findSysUserByUsername(String username) {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() > 0)
            return sysUsers.get(0);
        return null;
    }

    @Transactional
    @Override
    public void addSysUser(SysUser user) throws CustomMsgException {
        if (findSysUserByUsername(user.getUsername()) != null)
            throw new CustomMsgException("用户名重复");

        SysUserExample sysUserExample = new SysUserExample();
        int sys_user_count = sysUserMapper.countByExample(sysUserExample);

        user.setUserId(IdUtil.generateUUID());
        user.setUsercode(UserUtil.generateUserCode(sys_user_count));
        user.setSalt(UserUtil.generatePasswordSalt());
        user.setLocked("0");
        user.setPassword(UserUtil.generatePassword(user.getPassword(), user.getSalt()));

        sysUserMapper.insert(user);

    }

    @Transactional
    @Override
    public void delSysUserByUserId(int userId) {

    }

    @Transactional
    @Override
    public void updateSysUser(SysUser user) {

    }


}

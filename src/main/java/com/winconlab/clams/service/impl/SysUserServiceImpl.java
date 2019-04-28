package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.mapper.SysRoleMapper;
import com.winconlab.clams.mapper.SysUserMapper;
import com.winconlab.clams.mapper.SysUserRoleMapper;
import com.winconlab.clams.pojo.*;
import com.winconlab.clams.service.SysUserService;
import com.winconlab.clams.utils.IdUtil;
import com.winconlab.clams.utils.RandomUtil;
import com.winconlab.clams.utils.UserUtil;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        else return null;
    }

    @Override
    public void addSysUser(SysUser user) throws SysUserException {
        if (findSysUserByUsername(user.getUsername()) != null)
            throw new SysUserException("用户名重复");

        SysUserExample sysUserExample = new SysUserExample();
        int sys_user_count = sysUserMapper.countByExample(sysUserExample);

        user.setUserId(IdUtil.generateUUID());
        user.setUsercode(UserUtil.generateUserCode(sys_user_count));
        user.setSalt(UserUtil.generatePasswordSalt());
        user.setLocked("0");
        user.setPassword(UserUtil.generatePassword(user.getPassword(), user.getSalt()));

        sysUserMapper.insert(user);


    }

    @Override
    public void delSysUserByUserId(int userId) {

    }

    @Override
    public void updateSysUser(SysUser user) {

    }



}

package com.winconlab.clams.service.impl;

import com.winconlab.clams.mapper.UserMapper;
import com.winconlab.clams.pojo.GlobalResult;
import com.winconlab.clams.pojo.User;
import com.winconlab.clams.pojo.UserExample;
import com.winconlab.clams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public GlobalResult findAllUser() {
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        return GlobalResult.ok(users);
    }

    @Override
    public GlobalResult register(User user) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            return GlobalResult.build(-2, "该用户名已被注册");
        }

        Date date = new Date();
        user.setCreateTime(date);
        userMapper.insert(user);
        return GlobalResult.ok();
    }

    @Override
    public GlobalResult login(String username, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);

        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            return GlobalResult.ok(users.get(0));
        }

        return GlobalResult.build(-3, "用户名密码错误");
    }

    @Override
    public GlobalResult update(int user_id, String username, String newPassword, String userType) {
        User user = userMapper.selectByPrimaryKey(user_id);

        if (!StringUtils.isEmpty(username)) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameEqualTo(username);
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() > 0) {
                return GlobalResult.build(-2, "该用户名已被注册");
            }
            user.setUsername(username);
        }

        if (!StringUtils.isEmpty(newPassword)) {
            user.setPassword(newPassword);
        }

        if (!StringUtils.isEmpty(userType)) {
            user.setUserType(userType);
        }

        Date date = new Date();
        user.setUpdataTime(date);

        userMapper.updateByPrimaryKey(user);

        return GlobalResult.ok();
    }
}

package com.winconlab.clams.service;

import com.winconlab.clams.pojo.GlobalResult;
import com.winconlab.clams.pojo.User;

public interface UserService {
    GlobalResult findAllUser();

    GlobalResult register(User user);

    GlobalResult login(String username, String password);

    GlobalResult update(int user_id, String username, String newPassword,String userType);
}

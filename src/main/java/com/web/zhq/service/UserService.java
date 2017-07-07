package com.web.zhq.service;

import com.web.zhq.entity.User;
import com.web.zhq.entity.UserExample;

import java.util.List;

/**
 * @author : Created by zhq
 * @since : 2017/7/6
 */

public interface UserService {

    public List getUserList(User example);
}

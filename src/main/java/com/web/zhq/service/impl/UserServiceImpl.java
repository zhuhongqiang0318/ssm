package com.web.zhq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.web.zhq.dao.UserMapper;
import com.web.zhq.entity.User;
import com.web.zhq.entity.UserExample;
import com.web.zhq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Created by zhq
 * @since : 2017/7/6
 */
@Service
public class UserServiceImpl implements UserService {
    public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource  UserMapper UserMapper;

    public List getUserList(User user) {
        UserExample userExample = new UserExample();
        List<User> list = new ArrayList<User>();
        list = UserMapper.selectByExample(userExample);
        return list;
    }
}

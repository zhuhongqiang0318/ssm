package com.web.zhq.conrorller;

import com.alibaba.fastjson.JSONObject;
import com.web.zhq.entity.User;
import com.web.zhq.entity.UserExample;
import com.web.zhq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author : Created by zhq
 * @since : 2017/7/5
 */
@Controller
@RequestMapping(value = "user")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired private UserService userService;

    @RequestMapping(value = "/getInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUser(Model model,User user){
        logger.info("测试进入了嘛....");
        List<User> list = userService.getUserList(user);
        model.addAttribute("userList",list);
        return "/index";
    }

    @RequestMapping(value ="getJsonUser")
    @ResponseBody
    public Object getJSONUSER(){
        JSONObject jsonObject = new JSONObject();

        return  jsonObject;
    }
}

package com.web.zhq.conrorller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author : Created by zhq
 * @since : 2017/7/5
 */
@Controller
@RequestMapping(value = "user")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/getInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUser(){

        System.out.println("测试进入了嘛....");
        return "/index";
    }

    @RequestMapping(value ="getJsonUser")
    @ResponseBody
    public Object getJSONUSER(){
        JSONObject jsonObject = new JSONObject();

        return  jsonObject;
    }
}

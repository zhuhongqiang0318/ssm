package com.web.zhq.conrorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Created by zhq
 * @since : 2017/7/5
 */
@Controller
@RequestMapping(value = "user")
public class loginController {

    @RequestMapping(value = "/getInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUser(){

     return "/index";
    }


}

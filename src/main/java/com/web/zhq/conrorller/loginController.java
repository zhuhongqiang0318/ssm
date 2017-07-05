package com.web.zhq.conrorller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Created by zhq
 * @since : 2017/7/5
 */
@RequestMapping("user")
public class loginController {

    @RequestMapping("getUser")
    public String getUser(){

     return "/index";
    }


}

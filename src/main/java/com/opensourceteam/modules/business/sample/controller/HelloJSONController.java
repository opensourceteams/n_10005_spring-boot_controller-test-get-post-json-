package com.opensourceteam.modules.business.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 开发人:刘文
 * 日期:  2017/11/25.
 * 功能描述:
 */
@Controller
public class HelloJSONController {

    Logger logger = LoggerFactory.getLogger(HelloJSONController.class);

    @RequestMapping("/helloJSON")
    @ResponseBody
    String hello(@RequestBody String message) {
        logger.info("hello  message:{} " ,  message);
        return "Hello World! message: " + message;
    }


}

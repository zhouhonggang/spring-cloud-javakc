package com.zhou.javakc.security.oauth2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-09-24 16:19
 */
@RestController
@SessionAttributes("authorizationRequest")
public class ErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("/oauth/error")
    public String error(@RequestParam Map<String, String> parameters){
        String url = parameters.get("redirect_uri");
        log.info("重定向: {}", url);
        return "redirect:" + url + "?error=1";
    }

}

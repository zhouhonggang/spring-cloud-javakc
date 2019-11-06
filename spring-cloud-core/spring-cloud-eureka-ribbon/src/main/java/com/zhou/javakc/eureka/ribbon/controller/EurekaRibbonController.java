package com.zhou.javakc.eureka.ribbon.controller;

import com.zhou.javakc.eureka.ribbon.service.EurekaRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-08-08 11:50
 */
@RestController
@RequestMapping("ribbon")
public class EurekaRibbonController {

    @Autowired
    private EurekaRibbonService eurekaRibbonService;

    @GetMapping("/get")
    public String get() {
        return eurekaRibbonService.getName();
    }

}

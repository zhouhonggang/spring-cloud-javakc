package com.zhou.javakc.javakc.test.controller;

import com.zhou.javakc.component.data.entity.test.Test;
import com.zhou.javakc.component.data.redis.config.RedisRepository;
import com.zhou.javakc.javakc.test.service.JavakcTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-18 14:07
 */
@RestController
@RequestMapping("javakc")
public class JavacTestController {

    @Autowired
    private JavakcTestService javakcTestService;
    @Autowired
    private RedisRepository redisRepository;

    @GetMapping("test")
    public Page<Test> query(Test entity)
    {
        return javakcTestService.findAll(entity);
    }

    @PostMapping("test")
    public Test save(@RequestBody Test entity)
    {
        return javakcTestService.save(entity);
    }

    @DeleteMapping("test/{id}")
    public void delete(@PathVariable String id)
    {
        javakcTestService.delete(id);
    }

    @GetMapping("test/{id}")
    public Test load(@PathVariable String id)
    {
        return javakcTestService.get(id);
    }

    @GetMapping("test/export")
    public void export(HttpServletResponse response) throws IOException {
        javakcTestService.export(response);
    }



    @GetMapping("test/char")
    public List<Map<String, Object>> sexChar()
    {
        redisRepository.set("userName", "admin");
        return javakcTestService.queryGroupBySex();
    }

}

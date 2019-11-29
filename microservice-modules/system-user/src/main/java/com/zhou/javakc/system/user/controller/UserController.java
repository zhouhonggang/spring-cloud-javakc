package com.zhou.javakc.system.user.controller;

import com.zhou.javakc.component.data.entity.system.User;
import com.zhou.javakc.system.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-08-07 14:06
 */
@Api(value = "系统管理-用户管理")
@RefreshScope
@RestController
@RequestMapping("system")
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${name}")
//    private String name;
//
//    @GetMapping("user")
//    public String get(){
//        return name;
//    }

    @ApiOperation(value="展示用户", notes="分页查询用户列表")
    @ApiImplicitParam(name = "entity", value = "用户详细实体User", required = true, dataType = "User")
    @PostMapping("user/query")
    public Page<User> query(@RequestBody User entity)
    {
        return userService.findAll(entity);
    }

    @ApiOperation(value="获取用户", notes="根据请求参数的userId来获取用户详细信息")
    @ApiImplicitParam(name = "userId", value = "用户主键ID", required = true, dataType = "String")
    @GetMapping("user/{userId}")
    public User load(@PathVariable String userId)
    {
        return userService.get(userId);
    }

    @ApiOperation(value="删除用户", notes="根据请求参数的userId来指定删除用户")
    @ApiImplicitParam(name = "userId", value = "用户主键ID", required = true, dataType = "String")
    @DeleteMapping("user")
    public String delete(@RequestParam String userId)
    {
        userService.delete(userId);
        return "";
    }

    @ApiOperation(value="登录名唯一校验", notes="根据请求参数的loginName校验唯一性")
    @ApiImplicitParam(name = "loginName", value = "登陆名称", required = true, dataType = "String")
    @GetMapping("user/uniqueness")
    public boolean uniqueness(@RequestParam String loginName)
    {
        return userService.findByLoginName(loginName);
    }

    @ApiOperation(value="添加用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "entity", value = "用户详细实体User", required = true, dataType = "User")
    @PostMapping("user")
    public User save(@RequestBody User entity)
    {
        return userService.save(entity);
    }

    @ApiOperation(value="头像上传", notes="添加用户头像上传")
    @ApiImplicitParam(name = "photo", value = "photo只允许上传jpg图片", required = true, dataType = "MultipartFile")
    @PostMapping("user/photo")
    public String photo(@RequestParam("photo") MultipartFile photo)
    {
        String name = UUID.randomUUID().toString();
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            File photoFile = new File(path + File.separator + name);
            photo.transferTo(photoFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @GetMapping("user/export")
    public void export(HttpServletResponse response) throws IOException {
        userService.export(response);
    }

    @GetMapping("user/chart")
    public Map<String, List<?>> chart()
    {
        return userService.chart();
    }

}

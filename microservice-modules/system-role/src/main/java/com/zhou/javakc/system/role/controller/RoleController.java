package com.zhou.javakc.system.role.controller;

import com.zhou.javakc.component.data.entity.system.Role;
import com.zhou.javakc.system.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-09-06 09:06
 */
@Api(value = "系统管理-角色管理")
@RestController
@RequestMapping("system")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value="展示角色", notes="分页查询角色列表")
    @ApiImplicitParam(name = "entity", value = "角色详细实体Role", required = true, dataType = "Role")
    @PostMapping("role/query")
    public Page<Role> query(@RequestBody Role entity)
    {
        return roleService.findAll(entity);
    }

    @ApiOperation(value="展示角色", notes="获取全部角色列表")
    @GetMapping("role/query")
    public List<Role> query()
    {
        return roleService.findAll();
    }

    @ApiOperation(value="添加角色", notes="根据Role对象创建角色")
    @ApiImplicitParam(name = "entity", value = "角色详细实体Role", required = true, dataType = "Role")
    @PostMapping("role")
    public Role save(@RequestBody Role entity)
    {
        return roleService.save(entity);
    }

    @ApiOperation(value="获取角色", notes="根据请求参数的roleId来获取角色详细信息")
    @ApiImplicitParam(name = "roleId", value = "角色主键ID", required = true, dataType = "String")
    @GetMapping("role/{roleId}")
    public Role load(@PathVariable String roleId)
    {
        return roleService.get(roleId);
    }

    @ApiOperation(value="删除角色", notes="根据请求参数的roleId来指定删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色主键ID", required = true, dataType = "String")
    @DeleteMapping("role/{roleId}")
    public String delete(@PathVariable String roleId)
    {
        roleService.delete(roleId);
        return "";
    }

}

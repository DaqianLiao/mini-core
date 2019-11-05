package com.mini.web.test.controller.back;

import com.alibaba.fastjson.JSON;
import com.mini.jdbc.util.Paging;
import com.mini.security.digest.MD5;
import com.mini.util.DateUtil;
import com.mini.util.PKGenerator;
import com.mini.validate.ValidateUtil;
import com.mini.web.annotation.Action;
import com.mini.web.annotation.Controller;
import com.mini.web.model.JsonModel;
import com.mini.web.model.PageModel;
import com.mini.web.model.factory.ModelType;
import com.mini.web.test.entity.User;
import com.mini.web.test.service.UserService;
import com.mini.web.test.util.FileGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.mini.validate.ValidateUtil.sendError;

/**
 * 该组接口用于演示使用 Layui table 功能编写的后台管理功能
 * <p>
 * 该接口与Front/Demo接口有一些功能是相同的，只在演示不同的实现
 * </p>
 * @author xchao
 */
@Singleton
@Controller(path = "back/demo", url = "back/demo")
public class DemoController {
    @Inject
    private UserService userService;

    /**
     * 实体列表首页
     * @param model 数据模型渲染器
     */
    @Action(url = "index.htm")
    public void index(PageModel model) {
    }

    /**
     * 实体列表数据分页
     * @param model     数据模型渲染器
     * @param paging    数据分页工具
     * @param search    搜索关键字
     * @param sortType  排序方式
     * @param phone     手机号是否认证
     * @param email     邮箱是否认证
     * @param province  省
     * @param city      市
     * @param district  区/县
     * @param startTime 开始时间
     * @param endTime   开始时间
     * @param request   HttpServletRequest
     */
    @Action(value = ModelType.JSON, url = "pages.htm")
    public void pages(JsonModel model,Paging paging, String search, int sortType, int phone, int email, int province, int city,
            int district, LocalDate startTime, LocalDate endTime, HttpServletRequest request) {
        System.out.println("===================back========================");
        System.out.println(JSON.toJSONString(request.getParameterMap()));

        StringBuilder regionIdUri = new StringBuilder();
        if (province > 0) {
            regionIdUri.append(province);
        }
        if (city > 0) {
            regionIdUri.append(".");
            regionIdUri.append(city);
        }
        if (district > 0) {
            regionIdUri.append(".");
            regionIdUri.append(district);
        }
        System.out.println(regionIdUri.toString());
        model.addData("data", userService.search(paging, search, sortType, phone, email, regionIdUri.toString(), startTime, endTime)
                .stream().map(user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", String.valueOf(user.getId()));
                    map.put("name", user.getName());
                    map.put("phone", user.getPhone());
                    map.put("phoneAuth", String.valueOf(user.getPhoneAuth()));
                    map.put("fullName", user.getFullName());
                    map.put("email", user.getEmail());
                    map.put("emailAuth", String.valueOf(user.getEmailAuth()));
                    map.put("fullHeadUrl", FileGenerator.getPublicFullUrl(user.getHeadUrl()));
                    map.put("headUrl", user.getHeadUrl());
                    map.put("createTime", DateUtil.formatDateTime(user.getCreateTime()));
                    map.put("regionIdUri", user.getRegionIdUri());
                    map.put("regionNameUri", user.getRegionNameUri());
                    return map;
                }).toArray());
        model.addData("count", paging.getTotal());
        model.addData("msg", "查询成功");
        model.addData("code", 0);
    }

    /**
     * 添加用户信息处理
     * @param model 数据模型渲染器
     * @param user  实体信息
     */
    @Action(value = ModelType.JSON, url = "insert.htm")
    public void insert(JsonModel model,User user) throws Exception {
        ValidateUtil.isNotNull(user, 600, "用户信息为空，处理失败");
        ValidateUtil.isNotBlank(user.getName(), 600, "用户名不能为空");
        ValidateUtil.isNotBlank(user.getPassword(), 600, "用户密码不能为空");
        ValidateUtil.isNotBlank(user.getPhone(), 600, "用户手机号不能为空");
        // 加密密码和生成用户ID
        user.setPassword(MD5.encode(user.getPassword()));
        user.setCreateTime(new Date());
        user.setId(PKGenerator.id());
        // 添加用户信息到数据库
        if (userService.insert(user) != 1) {
            sendError(600, "添加用户信息失败");
            return;
        }
        // 返回用户信息到客户端
        model.addData("id", String.valueOf(user.getId()));
    }

    /**
     * 修改用户信息处理
     * @param model 数据模型渲染器
     * @param user  实体信息
     */
    @Action(value = ModelType.JSON, url = "update.htm")
    public void update(JsonModel model,User user) {
        ValidateUtil.isNotNull(user, 600, "用户信息为空，处理失败");
        ValidateUtil.isNotBlank(user.getName(), 600, "用户名不能为空");
        ValidateUtil.isNotBlank(user.getPhone(), 600, "用户手机号不能为空");

        User info = userService.queryById(user.getId());
        ValidateUtil.isNotNull(info, 600, "用户信息不存在");
        user.setCreateTime(info.getCreateTime());
        user.setPassword(info.getPassword());
        if (userService.update(user) != 1) {
            sendError(600, "修改用户信息失败");
        }
    }

    /**
     * 删除用户信息
     * @param model  数据模型渲染器
     * @param idList 要删除的数据ID List
     */
    @Action(value = ModelType.JSON, url = "delete.htm")
    public void delete(JsonModel model,long[] idList) {
        ValidateUtil.is(idList != null && idList.length > 0, 600, "未选中数据");
        userService.delete(idList);
    }
}

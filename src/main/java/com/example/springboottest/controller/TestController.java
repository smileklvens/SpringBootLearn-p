package com.example.springboottest.controller;

import com.example.springboottest.dao.UserMapper;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author 容联•云通讯-zhulk
 * @class describe  {@link #}
 * @time 2019-01-26 22:38
 */
@RestController
public class TestController {
    @Autowired
    UserMapper mUserMapper;

    @ApiOperation(value = "根据id查询user的信息",notes = "查询数据库中某个user的信息")
    @ApiImplicitParam(name ="id",value = "用户id",dataType = "Integer",paramType = "query",required = true,example = "1")
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到")
    })
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test(@RequestParam("id") Integer id) {
        return mUserMapper.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查询所有user的信息接口",notes = "查询数据库中所有user的信息")
    @ApiImplicitParam(name ="pageNum",value = "第几页",dataType = "Integer",paramType = "query",required = true,example = "3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value = "每页显示user信息条数",dataType = "Integer",paramType = "query",required = true,example = "3")
    })
    @ApiResponses({
            @ApiResponse(code=400,message = "请求参数没有填好"),
            @ApiResponse(code=404,message="请求路径没有找到")
    })
    @RequestMapping(value="/all",method = RequestMethod.GET)
    public Object getAllUsers(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return mUserMapper.selectAllUsers();
    }
}

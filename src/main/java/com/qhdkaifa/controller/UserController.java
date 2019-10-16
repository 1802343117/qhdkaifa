package com.qhdkaifa.controller;

import cn.hutool.db.Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qhdkaifa.factory.DaoFactory;
import com.qhdkaifa.utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @classname:UserController
 * @description:用户控制层
 * @author:zhuoran
 * @Date: 2019/10/10 10:33
 */
@WebServlet(urlPatterns = "/user")
public class UserController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Entity> entityList = null;
        try {
            entityList = DaoFactory.getUserDaoInstance().findAll();
        } catch (SQLException e) {
            System.err.println("查询用户数据出现异常");
        }

        ResponseObject ro = new ResponseObject();
        ro.setCode(resp.getStatus());
        if(resp.getStatus() == 200){
            ro.setMsg("请求成功");
        }else{
            ro.setMsg("请求失败");
        }
        ro.setData(entityList);

        resp.setHeader("Access-Control-Allow-Origin", "*");
        //设置响应对象的内容类型
        resp.setContentType("application/json;charset = utf-8");
        //创建一个Gson对象
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();

    }
}

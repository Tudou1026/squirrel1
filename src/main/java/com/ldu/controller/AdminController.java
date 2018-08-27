package com.ldu.controller;

import com.ldu.pojo.Goods;
import com.ldu.pojo.Image;
import com.ldu.pojo.User;
import com.ldu.service.GoodsService;
import com.ldu.service.ImageService;
import com.ldu.util.UserGrid;
import com.ldu.util.GoodGrid;
import com.ldu.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lenovo on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String adminHome() { return "/admin/home"; }

    @RequestMapping(value = "/goodList",method = RequestMethod.GET)
    public String goodList() { return "/admin/goodList"; }

    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public String userList() { return "/admin/userList"; }

    @RequestMapping(value="/getUserInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public User getUserById(@RequestParam("userId") int userId){
        User user = userService.selectByPrimaryKey(userId);
        System.out.println(user);
        return user;
    }
    @RequestMapping(value="/getGoodsInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Goods getImageById(@RequestParam("goodsId") int goodsId){
        Goods goods = goodsService.getGoodsById(goodsId);
        System.out.println(goods);
        return goods;

        //User user = userService.selectByPrimaryKey(userId);
        //return user;
    }

    @RequestMapping(value = "/getImage",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Image getGoodsById(@RequestParam("goodsId") int goodsId){

        List<Image> list = imageService.getImagesByGoodsPrimaryKey(goodsId);
        //System.out.println(goods);
        System.out.println(list);
        return list.get(0);
        //User user = userService.selectByPrimaryKey(userId);
        //return user;
    }

    @RequestMapping(value = "/users",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public UserGrid getUserList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = userService.getUserNum();
        List<User> list = userService.getPageUser(current,rowCount);
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrent(current);
        userGrid.setRowCount(rowCount);
        userGrid.setRows(list);
        userGrid.setTotal(total);
        return userGrid;
    }

    @RequestMapping(value = "/goods",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public GoodGrid getGoodList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = goodsService.UncheckedGoodsNum();
        List<Goods> list = goodsService.getPageGoods(current,rowCount);
        GoodGrid goodsGrid = new GoodGrid();
        goodsGrid.setCurrent(current);
        goodsGrid.setRowCount(rowCount);
        goodsGrid.setRows(list);
        goodsGrid.setTotal(total);
        return goodsGrid;
    }

    //将用户信息导出到Excel
    @RequestMapping("/exportUser")
    public void export(HttpServletResponse response) throws Exception{
        InputStream is=userService.getInputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
    }
}
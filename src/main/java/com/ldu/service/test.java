package com.ldu.service;

import com.ldu.pojo.Goods;
import com.ldu.pojo.Record;
import com.ldu.pojo.User;
import com.ldu.util.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:conf/applicationContext.xml"})
public class test {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    RecordService recordService;

    @Test
    public void Get() {
        System.out.println(goodsService.getGoodsByUserId(2));
    }

    @Test
    public void getGoods(){
        System.out.println(goodsService.getGoodsByPrimaryKey(49));
    }

    @Test
    public void  getImage(){
        System.out.println(imageService.getImagesByGoodsPrimaryKey(49));
    }

    @Test
    public void  getUser(){
        System.out.println(userService.selectByPrimaryKey(2));
        System.out.println(userService.getUserByPhone("18021018582"));
    }

    @Test
    public void getRecord(){
        List<Record> list = recordService.selectByUserId(2);
        System.out.println(list);
        System.out.println(recordService.selectByUserId(2));
    }

    @Test
    public void  getUncheckedGoods(){
        int num = goodsService.UncheckedGoodsNum();
        List<Goods> list = goodsService.getPageGoods(1,2);
        List<User> userList = userService.getPageUser(1,5);
        System.out.println(userList);
        System.out.println(list);
        System.out.println(num);
    }

}

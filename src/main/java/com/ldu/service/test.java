package com.ldu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:conf/applicationContext.xml"})
public class test {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void Get() {
        System.out.println(goodsService.getGoodsByUserId(2));
    }
}

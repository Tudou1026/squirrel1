package com.ldu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.ldu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldu.dao.GoodsMapper;
import com.ldu.pojo.Catelog;
import com.ldu.pojo.Goods;
import com.ldu.service.GoodsService;
import com.ldu.util.DateUtil;
/**
 * 对商品的操作类（增删改查）
 * @ClassName 	GoodServiceImpl
 * @date		2017-5-9下午9:22:24
 */

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public int addGood(Goods goods , Integer duration) {
        String startTime = DateUtil.getNowDay();
        String endTime = DateUtil.getLastTime(startTime, duration);
        String polishTime = startTime;
        //添加上架时间，下架时间，擦亮时间
        goods.setPolishTime(polishTime);
        goods.setEndTime(endTime);
        goods.setStartTime(startTime);
        return goodsMapper.insert(goods);
    }

    public Goods getGoodsByPrimaryKey(Integer goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        return goods;
    }

    public Goods getGoodsById(Integer goodsId){

        Goods goods = goodsMapper.selectById(goodsId);
        return goods;
    }

    public void deleteGoodsByPrimaryKey(Integer id) {
        goodsMapper.deleteByPrimaryKey(id);
    }

    public List<Goods> getAllGoods() {
        List<Goods> goods = goodsMapper.selectAllGoods();
        return goods;
    }

    public int UncheckedGoodsNum(){
        List<Goods> goods = goodsMapper.selectUnCheckedAllGoods();
        return goods.size();
    }

    public List<Goods> getPageGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页核心代码
        List<Goods> data= goodsMapper.selectUnCheckedAllGoods();
        return data;
    }
    /**
     * 查询最近发布的10个商品
     * @param
     * @return
     * @Author guoxilong
     */
    public List<Goods> selectOrderByDate() {
        List<Goods> goods = goodsMapper.selectOrderByDate();
        return goods;
    }

    /**
     * Author:guoxilong
     * 按商品成色排序
     */
    public List<Goods> searchGoodsOrderByNew(String name, String describle) {
        List<Goods> goods = goodsMapper.searchGoodsOrderByNew(name,describle);
        return  goods;
    }

    /**
     * Author:guoxilong
     * 按卖家信誉分排序
     */
    public List<Goods> searchGoodsOrderByCredit(String name, String describle) {
        List<Goods> goods = goodsMapper.searchGoodsOrderByCredit(name,describle);
        return  goods;
    }



    public List<Goods> getGoodsByCatelog(Integer id,String name,String describle) {
        List<Goods> goods = goodsMapper.selectByCatelog(id,name,describle);
        return goods;
    }

    public void updateGoodsByPrimaryKeyWithBLOBs(int goodsId,Goods goods) {
        goods.setId(goodsId);
        this.goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
    }

    public List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId,Integer limit) {
        List<Goods> goodsList = goodsMapper.selectByCatelogOrderByDate(catelogId , limit);
        return goodsList;
    }

    public List<Goods> getGoodsByUserId(Integer user_id) {
        List<Goods> goodsList = goodsMapper.getGoodsByUserId(user_id);
        return goodsList;
    }



}
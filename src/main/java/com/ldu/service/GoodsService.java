package com.ldu.service;

import java.util.List;

import com.ldu.pojo.Catelog;
import com.ldu.pojo.Goods;
import com.ldu.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface GoodsService {
    /**
     * 发布商品
     * @param goods
     * @param duration 允许上架时长
     */
    public int addGood(Goods goods , Integer duration);

    /**
     * 通过主键获取商品 已审核过的
     * @param goodsId
     * @return
     */


    public Goods getGoodsByPrimaryKey(Integer goodsId);

    /**
     * 查询所有
     * @param goodsId
     * @return
     */
    public Goods getGoodsById(Integer goodsId);

    /**
     * 更新商品信息
     * @param goods
     */
    public void updateGoodsByPrimaryKeyWithBLOBs(int goodsId ,Goods goods);

    /**
     * 通过主键删除商品
     * @param id
     */
    public void deleteGoodsByPrimaryKey(Integer id);

    /**
     * 获取所有商品信息
     */
    public List<Goods> getAllGoods();

    public int UncheckedGoodsNum();

    public List<Goods> getPageGoods(int pageNum, int pageSize);
    /**
     * 获取最近发布的10个商品信息
     */
    public List<Goods> selectOrderByDate();

    /**
     * Author:guoxilong
     * 按商品成色排序
     */
    List<Goods> searchGoodsOrderByNew(String name, String describle);

    /**
     * Author:guoxilong
     * 按卖家信誉分排序
     */
    List<Goods> searchGoodsOrderByCredit(String name, String describle);


    /**
     * 通过商品分类获取商品信息
     */
    public List<Goods> getGoodsByCatelog(Integer id,String name,String describle);

    /**
     * 根据分类id,并进行时间排序,获取前limit个结果
     * @param catelogId
     * @param limit
     * @return
     */
    public List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId,Integer limit);

    /**
     * 根据用户的id，查询出该用户的所有闲置
     * @param user_id
     * @return
     */
    public List<Goods> getGoodsByUserId(Integer user_id);

}
package com.ldu.service;

import com.ldu.pojo.Record;
import java.util.List;

/**
 * Author:guoxilong
 */
public interface RecordService {
    /**
     * @param userId
     * @param goodsId
     * 插入购物记录
     */
    int addRecord(Integer userId, Integer goodsId);

    /**
     * 根据id查询购物记录
     * @param id
     * @return
     */
    Record selectById(Integer id);

    /**
     * 删除某条记录
     * @param id
     * @return
     * @Author guoxilong
     */
    int deleteRecord(Integer id);

    /**
     * @param userId
     * 根据用户ID查询购物记录
     */
    List<Record> selectByUserId(Integer userId);

    /**
     * @param id
     * 更新购物记录的评价状态
     */
    int updateRecord(Integer id);
}

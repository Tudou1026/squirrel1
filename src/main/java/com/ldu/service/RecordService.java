package com.ldu.service;

import com.ldu.pojo.Record;
import java.util.List;

/**
 * Author:guoxilong
 */
public interface RecordService {
    /**
     * @param record
     * 插入购物记录
     */
    int insertRecord(Record record);

    /**
     * @param userId
     * 根据用户ID查询购物记录
     */
    List<Record> selectByUserId(Integer userId);

    /**
     * @param userId
     * @param goodsId
     * 更新购物记录的评价状态
     */
    int updateRecord(Integer userId, Integer goodsId);
}

package com.ldu.dao;

import com.ldu.pojo.Goods;
import com.ldu.pojo.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:guoxilong
 */
@Repository
public interface RecordMapper {
    int insertRecord(Record record);

    List<Record> selectByUserId(Integer userId);

    int updateRecord(Integer userId, Integer goodsId);
}

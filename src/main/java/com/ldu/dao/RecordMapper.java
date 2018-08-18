package com.ldu.dao;

import com.ldu.pojo.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Author:guoxilong
 */
@Repository
public interface RecordMapper {
    int addRecord(@Param("user_id") Integer user_id, @Param("goods_id") Integer goods_id);

    Record selectById(@Param("id") Integer id);

    List<Record> selectByUserId(Integer userId);

    int updateRecord(Integer id);

    int deleteRecord(Integer id);
}

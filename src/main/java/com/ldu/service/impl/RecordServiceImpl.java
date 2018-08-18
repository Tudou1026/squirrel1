package com.ldu.service.impl;

import com.ldu.dao.RecordMapper;
import com.ldu.pojo.Record;
import com.ldu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Author:guoxilong
 */
@Service("recordService")
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;

    public int addRecord(Integer userId, Integer goodsId){
        return recordMapper.addRecord(userId, goodsId);
    }

    public Record selectById(Integer id){
        return recordMapper.selectById(id);
    }

    public List<Record> selectByUserId(Integer userId){
        List<Record> records = recordMapper.selectByUserId(userId);
        return records;
    }

    public int deleteRecord(Integer id) {
        return recordMapper.deleteRecord(id);
    }

    public int updateRecord(Integer id){
        return recordMapper.updateRecord(id);
    }
}

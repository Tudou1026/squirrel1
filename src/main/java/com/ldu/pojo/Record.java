package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.interfaces.PBEKey;
import java.util.List;

/**
 * Author:guoxilong
 * 用户购物记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Integer id;

    private Integer userId;

    private Goods goods;
    //该记录是否评价
    private Boolean commentStatus;

    private java.sql.Timestamp createdAt;

    private java.sql.Timestamp updatedAt;
}

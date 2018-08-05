package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:guoxilong
 * 新增信誉分字段，由评价计算，用于商品排序
 * 新增权限字段
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String phone;

    private String username;

    private String password;

    private String qq;

    private String createAt;

    private Integer goodsNum;

    //信誉分
    private Double credit;

    private String lastLogin;

    //账号状态，是否被冻结
    private Byte status;

    private Boolean power;

}
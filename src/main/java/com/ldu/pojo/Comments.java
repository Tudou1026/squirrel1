package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String createAt;

    private String content;

}
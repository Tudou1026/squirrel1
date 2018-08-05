package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private Integer id;

    private Integer goodsId;

    private String imgUrl;

}
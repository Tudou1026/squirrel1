package com.ldu.util;

import com.ldu.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodGrid {
    private int current;//当前页面号

    private int rowCount;//每页行数

    private int total;//总行数

    private List<Goods> rows;

}

package com.ldu.util;

import com.ldu.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGrid {
    private int current;//当前页面号

    private int rowCount;//每页行数

    private int total;//总行数

    private List<User> rows;

}

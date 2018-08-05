package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer id;

    private Integer userId;

    private String createAt;

    private Byte status;

    private String context;

}
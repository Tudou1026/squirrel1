package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private Integer id;

    private Integer userId;

    private Integer atuserId;

    private Integer commetId;

    private String createAt;

    private String content;

}
package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Catelog {
    private Integer id;

    private String name;

    private Integer number;

    private Byte status;

}
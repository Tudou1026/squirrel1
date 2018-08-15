package com.ldu.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * author:guoxilong
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordExtend {

    private Record record;

    private List<Image> images = new ArrayList<Image>();
}

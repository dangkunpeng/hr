package com.magpie.devOps.utils.result;


import java.util.List;

import lombok.Data;

@Data
public class PageResult<T> {
    private Long total;

    private List<T> rows;
}

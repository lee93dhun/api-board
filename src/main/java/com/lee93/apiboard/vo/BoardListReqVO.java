package com.lee93.apiboard.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardListReqVO {
    private String StartDate;
    private String EndDate;
    private Integer category;
    private String keyword;

    private int offset;
    private int pageSize;
}

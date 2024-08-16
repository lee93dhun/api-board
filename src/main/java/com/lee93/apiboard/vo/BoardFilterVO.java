package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFilterVO {
    private String startDate;
    private String endDate;
    private Integer category;
    private String keyword;
}

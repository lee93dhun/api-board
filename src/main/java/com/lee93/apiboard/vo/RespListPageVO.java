package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespListPageVO {

    private List<CategoryVO> categoryList;
    private int postCount;
    private  BoardFilterVO boardFilterVO;
    private List<BoardListRespVO> boardListByFilter;
    private PageVO pageVO;
}

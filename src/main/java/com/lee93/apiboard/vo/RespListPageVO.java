package com.lee93.apiboard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RespListPageVO {

    private boolean success;
    private int status;
    private String message;

    private List<CategoryVO> categoryList;
    private int postCount;
    private  BoardFilterVO boardFilterVO;
    private List<BoardListRespVO> boardListByFilter;
    private PageVO pageVO;
}

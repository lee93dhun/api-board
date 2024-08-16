package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardListRespVO {

    private int postId;
    private String postWriter;
    private String postTitle;
    private int postHits;
    private LocalDateTime uploadDatetime;
    private LocalDateTime updateDatetime;

    private String categoryName;
}

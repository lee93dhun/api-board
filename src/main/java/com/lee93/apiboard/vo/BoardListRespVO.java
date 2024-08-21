package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardListRespVO {

    private int postId;
    private String postWriter;
    private String postTitle;
    private int postHits;
    private LocalDateTime uploadDatetime;
    private LocalDateTime updateDatetime;
    private boolean isDelete;

    private String categoryName;

    private boolean hasFiles;
}

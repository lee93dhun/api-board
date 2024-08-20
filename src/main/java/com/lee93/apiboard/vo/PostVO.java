package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostVO {
    private int postId;
    private String postTitle;
    private String postWriter;
    private String postContent;
    private int postHits;
    private LocalDateTime uploadDatetime;
    private LocalDateTime updateDatetime;

    private String categoryName;
}

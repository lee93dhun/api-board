package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReqVO {
    private int postId;
    private String commentWriter;
    private String commentContent;
}

package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRespVO {
    private int commentId;
    private int postId;
    private String commentWriter;
    private String commentContent;
    private String commentDatetime;
    private int commentDelYn;
}

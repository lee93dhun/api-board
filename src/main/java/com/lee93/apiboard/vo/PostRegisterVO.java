package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRegisterVO {
    private int categoryId;
    private String postWriter;
    private String postPw;
    private String postTitle;
    private String postContent;
}

package com.lee93.apiboard.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostRequestVO {
    private int postId;
    private int categoryId;
    private String postWriter;
    private String postPw;
    private String postTitle;
    private String postContent;

    private List<MultipartFile> files = new ArrayList<>();
}

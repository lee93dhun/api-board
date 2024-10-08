package com.lee93.apiboard.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileVO {

    private int fileId;
    private int postId;
    private String fileOrigin;
    private String fileNew;
    private String filePath;
    private Long fileSize;

}

package com.lee93.apiboard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DetailResponse {
    private boolean success;
    private int status;
    private String message;

    private PostVO postVO;
    private List<FileVO> files = new ArrayList<>();
//    private List<CommentRespVO> comments = new ArrayList<>();

    public DetailResponse(boolean success, int status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }
}

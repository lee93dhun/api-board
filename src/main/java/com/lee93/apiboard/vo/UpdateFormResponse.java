package com.lee93.apiboard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateFormResponse {
    private boolean success;
    private int status;
    private String message;

    private PostVO postVO;
    private List<CategoryVO> categoryList = new ArrayList<>();
    private List<FileVO> files = new ArrayList<>();

    public UpdateFormResponse(boolean success, int status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }
}

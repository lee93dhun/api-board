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
    private String message;
    private PostVO postVO;
    private List<FileVO> files = new ArrayList<>();
}

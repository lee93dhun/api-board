package com.lee93.apiboard.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private boolean success;
    private String message;
    private PostRegisterVO postRegisterVO;


}

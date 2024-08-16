package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.PostRegisterVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO {

    void postRegister(PostRegisterVO postRegisterVO);
}

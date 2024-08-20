package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.PostFileRegisterVO;
import com.lee93.apiboard.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO {

    void postRegister(PostFileRegisterVO postFileRegisterVO);

    PostVO getPost(int postId);
}

package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.PostRequestVO;
import com.lee93.apiboard.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO {

    void postRegister(PostRequestVO postRequestVO);

    PostVO getPost(int postId);

    String getPassword(int postId);

    void updatePost(PostRequestVO postRequestVO);

    boolean deletePost(int postId);

    boolean isExistsPost(int postId);
}

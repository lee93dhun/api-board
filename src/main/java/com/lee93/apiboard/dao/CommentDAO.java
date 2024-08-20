package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.CommentReqVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDAO {
    void saveComment(CommentReqVO commentReqVO);
}

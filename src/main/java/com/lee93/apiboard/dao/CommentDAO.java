package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.CommentReqVO;
import com.lee93.apiboard.vo.CommentRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO {
    void saveComment(CommentReqVO commentReqVO);

    List<CommentRespVO> getComments(int postId);
}

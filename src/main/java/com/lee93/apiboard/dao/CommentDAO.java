package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.CommentReqVO;
import com.lee93.apiboard.vo.CommentRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO {

    int registerComment(CommentReqVO commentReqVO);
    CommentRespVO getRegisteredComment(int commentId);

    List<CommentRespVO> getComments(int postId);

}

package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.CommentDAO;
import com.lee93.apiboard.vo.CommentReqVO;
import com.lee93.apiboard.vo.CommentRespVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CommentDAO commentDAO;

    /**
     * 댓글 저장
     * @param commentReqVO 저장할 댓글의 정보
     */
    public void saveComment(CommentReqVO commentReqVO) {
        logger.info(" ## saveComment() 실행");
        commentDAO.saveComment(commentReqVO);
    }

    /**
     * 댓글 리스트  불러오기
     * @param postId  불러올 댓글리스트의 게시물 id
     * @return 게시물 id에 따른 댓글 리스트
     */
    public List<CommentRespVO> getComments(int postId) {
        logger.info(" ## getComments 실행");
        return commentDAO.getComments(postId);
    }
}

package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.CommentDAO;
import com.lee93.apiboard.vo.CommentReqVO;
import com.lee93.apiboard.vo.CommentRespVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void saveComment(CommentReqVO commentReqVO) {
        logger.info(" ## saveComment() 실행");
        commentDAO.saveComment(commentReqVO);
    }
}

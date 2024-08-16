package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.BoardDAO;
import com.lee93.apiboard.vo.BoardFilterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ListService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BoardDAO boardDAO;

    public ListService(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public int getPostCount(BoardFilterVO boardFilterVO) {
        logger.info(" ## getPostCount() 실행");
        return boardDAO.getPostCount(boardFilterVO);
    }
}

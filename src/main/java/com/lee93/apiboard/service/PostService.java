package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.PostDAO;
import com.lee93.apiboard.vo.PostRegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public void postRegister(PostRegisterVO postRegisterVO) {
        logger.info(" ## postRegister() 실행 ");
        // 비밀번호 확인
        
        // 유효성 검사
        
        // 게시물 등록
        postDAO.postRegister(postRegisterVO);
    }
}

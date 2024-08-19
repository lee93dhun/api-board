package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.PostDAO;
import com.lee93.apiboard.vo.PostRegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PostDAO postDAO;
    private final PasswordEncoder passwordEncoder;

    public PostService(PostDAO postDAO, PasswordEncoder passwordEncoder) {
        this.postDAO = postDAO;
        this.passwordEncoder = passwordEncoder;
    }


    public void postRegister(PostRegisterVO postRegisterVO) {
        logger.info(" ## postRegister() 실행 ");

        String rawPassword = postRegisterVO.getPostPw();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        postRegisterVO.setPostPw(encodedPassword);
        logger.info("Encoded Password Check: {} ", postRegisterVO.getPostPw());
        // TODO 유효성 검사하기

        postDAO.postRegister(postRegisterVO);
    }

}

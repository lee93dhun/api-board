package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.PostDAO;
import com.lee93.apiboard.vo.PostVO;
import com.lee93.apiboard.vo.PostRequestVO;
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

    
    /**
     * 게시물 등록 서비스
     * @param postRequestVO 등록할 게시물과 첨부파일 클래스
     */
    public void postRegister(PostRequestVO postRequestVO) {
        logger.info(" ## postRegister() 실행 ");

        String rawPassword = postRequestVO.getPostPw();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        postRequestVO.setPostPw(encodedPassword);
        logger.info("Encoded Password Check: {} ", postRequestVO.getPostPw());
        // TODO 유효성 검사하기

        postDAO.postRegister(postRequestVO);
    }

    /**
     * 게시물 정보를 가져옴
     * @param postId 가져올 게시물의 id
     * @return postId에 해당하는 게시물 정보
     */
    public PostVO getPost(int postId) {
        logger.info(" ## getPost() 실행");
        return postDAO.getPost(postId);
    }

    public void updatePost(PostRequestVO postRequestVO) {
        logger.info(" ## updatePost() 실행");

        postDAO.updatePost(postRequestVO);
    }
}

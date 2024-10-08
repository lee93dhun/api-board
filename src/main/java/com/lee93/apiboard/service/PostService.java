package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.PostDAO;
import com.lee93.apiboard.vo.PostVO;
import com.lee93.apiboard.vo.PostRequestVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PostDAO postDAO;
    private final PasswordEncoder passwordEncoder;

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

    /**
     * 게시물 수정
     * @param postRequestVO 수정할 게시물 정보
     */
    public void updatePost(PostRequestVO postRequestVO) {
        logger.info(" ## updatePost() 실행");
        postDAO.updatePost(postRequestVO);
    }

    /**
     * 게시물 삭제 (DB 삭제 X)
     * @param postId 삭제할 게시물 id
     * @return 게시물 삭제 성공 여부
     */
    public boolean deletePost(int postId) {
        logger.info(" ## deletePost() 실행");
        return postDAO.deletePost(postId);
    }

    public boolean isExistsPost(int postId) {
        logger.info(" ## isExists() 실행");
        return postDAO.isExistsPost(postId);
    }

    public void hitsCountUp(int postId) {
        logger.info(" ## hitsCountUp() 실행");
        postDAO.hitsCountUp(postId);
    }
}

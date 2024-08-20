package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.PostDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder passwordEncoder;
    private final PostDAO postDAO;

    public SecurityService(PasswordEncoder passwordEncoder, PostDAO postDAO) {
        this.passwordEncoder = passwordEncoder;
        this.postDAO = postDAO;
    }

    /**
     * 비밀번호 확인
     * @param postId 확인할 비밀번호의 게시물 id
     * @param inputPw 클라이언트에서 요청한 비밀번호
     * @return 요청한 비밀번호와 DB의 비밀번호 비교 결과
     */
    public boolean isPasswordMatch(int postId, String inputPw) {
        String encodedPassword = postDAO.getPassword(postId);
      return  passwordEncoder.matches(inputPw, encodedPassword);
    }
}

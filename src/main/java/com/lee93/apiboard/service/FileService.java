package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.FileDAO;
import com.lee93.apiboard.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FileDAO fileDao;

    /**
     * 파일 정보를 DB에 저장
     * @param postId file 테이블에 저장될 게시물 번호
     * @param files 파일 정보
     */
    public void saveFiles(int postId, List<FileVO> files) {
        logger.info(" ## saveFile() 실행");
        if(CollectionUtils.isEmpty(files)){
            return;
        }
        for(FileVO file : files){
            file.setPostId(postId);
        }
        fileDao.saveFile(files);
    }

    public List<FileVO> getFiles(int postId) {
        logger.info(" ## getFiles() 실행");
        return fileDao.getFiles(postId);
    }
}

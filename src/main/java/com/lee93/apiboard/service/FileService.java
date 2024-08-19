package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.FileDAO;
import com.lee93.apiboard.vo.FileVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class FileService {
    private final FileDAO fileDao;

    public FileService(FileDAO fileDao) {
        this.fileDao = fileDao;
    }

    /**
     * 파일 정보를 DB에 저장
     * @param postId file 테이블에 저장될 게시물 번호
     * @param files 파일 정보
     */
    public void saveFiles(int postId, List<FileVO> files) {
        if(CollectionUtils.isEmpty(files)){
            return;
        }
        for(FileVO file : files){
            file.setPostId(postId);
        }
        fileDao.saveFile(files);
    }
}

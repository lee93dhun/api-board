package com.lee93.apiboard.common;

import com.lee93.apiboard.vo.FileVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {
    private final String uploadPath = Paths.get("E:","intellij-workspace","board","uploaded-files").toString();

    /**
     * 다중 파일 업로드
     * @param multipartFiles 파일 객체 리스트
     * @return 저장할 파일 정보 리스트
     */
    public List<FileVO> uploadFiles(final List<MultipartFile> multipartFiles) {
        List<FileVO> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            files.add(uploadFile(multipartFile));
        }
        return files;
    }

    /**
     * 단일 파일 업로드
     * @param multipartFile 파일 객체
     * @return 저장할 파일 정보
     */
    private FileVO uploadFile(final MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            return null;
        }
        String newName = generateSaveName(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString();
        String uploadPath = getUploadPath(today);
        File uploadFile = new File(uploadPath+File.separator+newName);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileVO.builder()
                .fileOrigin(multipartFile.getOriginalFilename())
                .fileNew(newName)
                .filePath(uploadPath)
                .fileSize(multipartFile.getSize())
                .build();
    }

    /**
     * 로컬에 저장될 파일명 생성
     * @param originalFilename 원본 파일명
     * @return 로컬에 저장될 파일명
     */
    private String generateSaveName(final String originalFilename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(originalFilename);
        return uuid+"."+extension;
    }

    /**
     * 업로드 경로 설정 (날짜 폴더 생성)
     * @param today 저장한 날짜
     * @return 업로드 경로 반환
     */
    private String getUploadPath(final String today) {
        return makeDir(uploadPath+ File.separator+today);
    }

    /**
     * 업로드 폴더 생성 및 경로 반환
     * @param uploadPath 업로드 경로
     * @return 업로드 경로
     */
    private String makeDir(final String uploadPath) {
        File dir = new File(uploadPath);
        if(dir.exists() == false){
            dir.mkdirs();
        }
        return dir.getPath();
    }
}

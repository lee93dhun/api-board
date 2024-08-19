package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDAO {

    void saveFile(List<FileVO> files);
}

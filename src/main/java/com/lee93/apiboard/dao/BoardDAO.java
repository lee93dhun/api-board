package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.BoardFilterVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO {

    int getPostCount(BoardFilterVO boardFilterVO);
}

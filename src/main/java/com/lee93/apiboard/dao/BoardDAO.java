package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.BoardFilterVO;
import com.lee93.apiboard.vo.BoardListReqVO;
import com.lee93.apiboard.vo.BoardListRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    int getPostCount(BoardFilterVO boardFilterVO);

    List<BoardListRespVO> getBoardListByFilter(BoardListReqVO boardListReqVO);
}

package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.BoardDAO;
import com.lee93.apiboard.vo.BoardFilterVO;
import com.lee93.apiboard.vo.BoardListReqVO;
import com.lee93.apiboard.vo.BoardListRespVO;
import com.lee93.apiboard.vo.PageVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BoardDAO boardDAO;

    public int getPostCount(BoardFilterVO boardFilterVO) {
        logger.info(" ## getPostCount() 실행");
        return boardDAO.getPostCount(boardFilterVO);
    }

    public BoardListReqVO buildBoardListReqVO(BoardFilterVO boardFilterVO, int page) {
        logger.info(" ## buildBoardListReqVO() 실행");
        int pageSize = 10; // 페이지 크기 기본 값
        int offset = (page - 1) * pageSize;

        return BoardListReqVO.builder()
                .StartDate(boardFilterVO.getStartDate())
                .EndDate(boardFilterVO.getEndDate())
                .category(boardFilterVO.getCategory())
                .keyword(boardFilterVO.getKeyword())
                .pageSize(pageSize)
                .offset(offset)
                .build();
    }

    public List<BoardListRespVO> getBoardListByFilter(BoardListReqVO boardListReqVO) {
        logger.info(" ## getBoardListByFilter() 실행");
        List<BoardListRespVO> boardListByFilter = boardDAO.getBoardListByFilter(boardListReqVO);

        return  boardListByFilter;
    }

    public PageVO getPageVO(int page, int postCount) {
            int pageSize = 10;
            int currentPage = page;
            int totalPage = getTotalPage(postCount, pageSize);

            PageVO pageDto = new PageVO();
            pageDto.setCurrentPage(currentPage);
            pageDto.setTotalPage(totalPage);
            return pageDto;

    }
    public int getTotalPage(int postCount, int pageSize) {
        int totalPage = 0;

        totalPage = postCount / pageSize;
        if (postCount % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }


}

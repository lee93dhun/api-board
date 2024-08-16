package com.lee93.apiboard.controller;

import com.lee93.apiboard.service.CategoryService;
import com.lee93.apiboard.service.ListService;
import com.lee93.apiboard.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path={"/","/board-api"})
public class BoardController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CategoryService categoryService;
    private final ListService listService;

    public BoardController(CategoryService categoryService, ListService listService) {
        this.categoryService = categoryService;
        this.listService = listService;
    }


    @GetMapping(path={"/","/board-api"})
    public String home(){
        return "redirect:/board-api/list";
    }

    @GetMapping(path="/list")
    public ResponseEntity getList(@ModelAttribute BoardFilterVO boardFilterVO,
                               @RequestParam(required = false, defaultValue = "1") int page){
        logger.info(":::: GET / list 요청 :::: ");

        List<CategoryVO> categoryList = categoryService.getCategoryList();
        int postCount = listService.getPostCount(boardFilterVO);
        BoardListReqVO boardListReqVO = listService.buildBoardListReqVO(boardFilterVO, page);
        PageVO  pageVO = listService.getPageVO(page, postCount);

        List<BoardListRespVO> boardListByFilter = listService.getBoardListByFilter(boardListReqVO);

        RespListPageVO respListPageVO = new RespListPageVO();
        respListPageVO.setCategoryList(categoryList);
        respListPageVO.setPostCount(postCount);
        respListPageVO.setBoardFilterVO(boardFilterVO);
        respListPageVO.setBoardListByFilter(boardListByFilter);
        respListPageVO.setPageVO(pageVO);

        return ResponseEntity.ok(respListPageVO);
    }

    @PostMapping("/post")
    public ResponseEntity<String> postRegister(@ModelAttribute PostRegisterVO postRegisterVO){
        logger.info(":::: POST / post 요청 :::: ");
        logger.info("postRegisterVO : {}", postRegisterVO);
        return null;
    }


}

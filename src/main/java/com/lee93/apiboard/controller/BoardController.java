package com.lee93.apiboard.controller;

import com.lee93.apiboard.service.CategoryService;
import com.lee93.apiboard.service.BoardListService;
import com.lee93.apiboard.service.PostService;
import com.lee93.apiboard.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path={"/","/board-api"})
public class BoardController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;
    private final CategoryService categoryService;
    private final BoardListService boardListService;

    public BoardController(CategoryService categoryService, BoardListService boardListService, PostService postService) {
        this.categoryService = categoryService;
        this.boardListService = boardListService;
        this.postService = postService;
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
        int postCount = boardListService.getPostCount(boardFilterVO);
        BoardListReqVO boardListReqVO = boardListService.buildBoardListReqVO(boardFilterVO, page);
        PageVO  pageVO = boardListService.getPageVO(page, postCount);

        List<BoardListRespVO> boardListByFilter = boardListService.getBoardListByFilter(boardListReqVO);

        RespListPageVO respListPageVO = new RespListPageVO();
        respListPageVO.setCategoryList(categoryList);
        respListPageVO.setPostCount(postCount);
        respListPageVO.setBoardFilterVO(boardFilterVO);
        respListPageVO.setBoardListByFilter(boardListByFilter);
        respListPageVO.setPageVO(pageVO);

        return ResponseEntity.ok(respListPageVO);
    }

    @GetMapping(path = "/post")
    public ResponseEntity postRegisterForm(){
        logger.info(" :::: GET / post 요청 :::: ");
        List<CategoryVO> categoryList = categoryService.getCategoryList();
        // TODO file service 추가하기
        return ResponseEntity.ok(categoryList);
    }


    @PostMapping(path="/post")
    public ResponseEntity<PostResponse> postRegister(@ModelAttribute PostRegisterVO postRegisterVO){
        logger.info(":::: POST / post 요청 :::: ");
        logger.info("postRegisterVO : {}", postRegisterVO);

        try{
            postService.postRegister(postRegisterVO);
            // TODO 파일 서비스 추가하기
            // TODO 유효성 검사에 따른 예외처리 추가 및 수정하기
            return ResponseEntity.ok(new PostResponse(true, null,null));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PostResponse(false, "서버 오류가 발생했습니다.", postRegisterVO));
        }

    }


}

package com.lee93.apiboard.controller;

import com.lee93.apiboard.service.CategoryService;
import com.lee93.apiboard.service.ListService;
import com.lee93.apiboard.vo.BoardFilterVO;
import com.lee93.apiboard.vo.CategoryVO;
import com.lee93.apiboard.vo.ResponseBoardList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity list(@ModelAttribute BoardFilterVO boardFilterVO,
                               @RequestParam(required = false, defaultValue = "1") int page){
        logger.info(":::: GET / list 요청 :::: ");
        List<CategoryVO> categoryList = categoryService.getCategoryList();
        int PostCount = listService.getPostCount(boardFilterVO);


        ResponseBoardList responseBoardList = new ResponseBoardList();
        return ResponseEntity.ok(responseBoardList);
    }


}

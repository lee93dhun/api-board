package com.lee93.apiboard.controller;

import com.lee93.apiboard.service.*;
import com.lee93.apiboard.common.FileUtils;
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
    private final CommentService commentService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;
    private final CategoryService categoryService;
    private final BoardListService boardListService;
    private final FileUtils fileUtils;
    private final FileService fileService;
    private final SecurityService securityService;

    public BoardController(CategoryService categoryService, BoardListService boardListService, PostService postService, FileUtils fileUtils, FileService fileService, CommentService commentService, SecurityService securityService) {
        this.categoryService = categoryService;
        this.boardListService = boardListService;
        this.postService = postService;
        this.fileUtils = fileUtils;
        this.fileService = fileService;
        this.commentService = commentService;
        this.securityService = securityService;
    }

    @GetMapping(path={"/","/board-api"})
    public String home(){
        return "redirect:/board-api/list";
    }

    /**
     * 게시판 리스트를 가져오는 요청
     * @param boardFilterVO 가져올 게시판 리스트의 조건이 담긴 객체
     * @param page 요청한 페이지 숫자
     * @return 게시판 리스트, 카테고리 리스트, 필터 정보 및 페이지 정보를 담은
     *      RespListPageVO 객체를 포함한 ResponseEntity
     */
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

    /**
     * 게시물 등록 페이지로 이동
     * @return 카테고리 리스트를 담은 ResponseEntity
     */
    @GetMapping(path = "/post")
    public ResponseEntity postRegisterForm(){
        logger.info(" :::: GET / post 요청 :::: ");
        List<CategoryVO> categoryList = categoryService.getCategoryList();
        return ResponseEntity.ok(categoryList);
    }

    // TODO 게시물 등록 OR 디스크에 파일 저장 OR DB에 파일정보 저장 실패에 따른 예외 처리 로직 추가
    // TODO 게시글 삭제시 DB 데이터 보존 후처리 생각해보기

    /**
     * 게시물 등록
     * @param postRequestVO 게시물 등록에 필요한 Value Object
     * @return 등록 성공여부에따라 메시지, PostRequestVo 객체를 포함한 응답 객체
     */
    @PostMapping(path="/post")
    public ResponseEntity<PostResponse> postRegister(@ModelAttribute PostRequestVO postRequestVO){
        logger.info(":::: POST / post 요청 :::: ");

        try{
            postService.postRegister(postRequestVO);
            int postId = postRequestVO.getPostId();

            // 파일을 로컬에 저장하기
            List<FileVO> files = fileUtils.uploadFiles(postRequestVO.getFiles());
            // DB에 파일 정보 저장
            fileService.saveFiles(postId, files);

            // TODO 유효성 검사에 따른 예외처리 추가 및 수정하기
            return ResponseEntity.ok(new PostResponse(true, null,null));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PostResponse(false, "서버 오류가 발생했습니다.", postRequestVO));
        }
    }

    /**
     * 게시물 상세보기 (게시물 내용, 첨부파일, 댓글)
     * @param postId 상세보기 할 게시물 id
     * @return
     */
    @GetMapping(path="/post/{postId}")
    public ResponseEntity<DetailResponse> getPost(@PathVariable int postId){
        logger.info(" :::: GET / post / {} 요청 ::::" , postId);
        // 게시물 내용 가져오기
        PostVO post = postService.getPost(postId);
        List<FileVO> files = fileService.getFiles(postId);
        List<CommentRespVO> comments = commentService.getComments(postId);
        return ResponseEntity.ok(new DetailResponse(true, null, post, files, comments));
        // TODO 실패할경우 로직
    }

    /**
     * 댓글 작성
     * @param postId 작성할 댓글의 게시물 id
     * @param commentReqVO 작성할 댓글 정보
     * @return
     */
    @PostMapping(path="/post/{postId}/comment")
    public ResponseEntity postComment(@PathVariable int postId, @ModelAttribute CommentReqVO commentReqVO){
        logger.info(" :::: POST / comment 요청 :::: ");
        commentReqVO.setPostId(postId);
        commentService.saveComment(commentReqVO);
        // TODO Comment 응답 값 및 예외처리
        return ResponseEntity.ok(true);
    }

    @GetMapping(path="/post/update/{postId}")
    public ResponseEntity postUpdateForm(@PathVariable int postId){
        logger.info(" :::: GET / post / update form 요청 ::::");

        List<CategoryVO>  categoryList = categoryService.getCategoryList();
        PostVO post = postService.getPost(postId);
        List<FileVO> files = fileService.getFiles(postId);

        return ResponseEntity.ok(new UpdateFormResponse(true, null, post, categoryList, files));
    }

    // TODO  PUT or PATCH ?
    @PutMapping(path = "/post/update/{postId}")
    public ResponseEntity<PostResponse> postUpdate(@PathVariable int postId, @ModelAttribute PostRequestVO postRequestVO){
        logger.info(" :::: PUT / post/ update 요청 :::: ");
        // 비밀번호 확인
        boolean pwResult = securityService.isPasswordMatch(postId, postRequestVO.getPostPw());
        if(!pwResult){
            return ResponseEntity.ok(new PostResponse(false, "비밀번호를 확인해 주세요.",postRequestVO));
        }
        // 게시물 수정
            postRequestVO.setPostId(postId);
            // 수정날짜 업데이트
            postService.updatePost(postRequestVO);
        // 파일 수정

        return ResponseEntity.ok(new PostResponse(true, postId+"번 게시물 수정 성공",null));
    }


}

package com.lee93.apiboard.service;

import com.lee93.apiboard.dao.CategoryDAO;
import com.lee93.apiboard.vo.CategoryVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryDAO categoryDAO;

    public List<CategoryVO> getCategoryList() {
        logger.info(" ## getCategoryList() 실행");
        return categoryDAO.getAllCategory();
    }
}

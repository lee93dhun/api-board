package com.lee93.apiboard.dao;

import com.lee93.apiboard.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {

    List<CategoryVO> getAllCategory();

}

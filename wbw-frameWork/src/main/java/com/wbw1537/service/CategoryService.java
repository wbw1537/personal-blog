package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.CategoryVo;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-09-10 17:59:27
 */
public interface CategoryService extends IService<Category> {
    List<CategoryVo> getCategoryList();
    List<CategoryVo> listAllCategory();
    ResponseResult exportCategory(HttpServletResponse response);
}

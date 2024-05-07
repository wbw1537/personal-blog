package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.CategoryVo;
import com.wbw1537.mapper.CategoryMapper;
import com.wbw1537.service.ArticleService;
import com.wbw1537.service.CategoryService;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-09-10 17:59:28
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    //注意： ①要求只展示有发布正式文章的分类 ②必须是正常状态的分类
    @Override
    public ResponseEntity getCategoryList() {
        //查询已发布文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleLambdaQueryWrapper);
        //获取文章分类id（去重）1
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //根据分类id取得分类名称
        List<Category> categories = listByIds(categoryIds);

        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return new ResponseEntity<>(categoryVos, HttpStatus.OK);
    }


    @Override
    public ResponseResult listAllCategory() {
        // 根据分类状态进行查询
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        // 封装数据
        List<Category> categories = list(queryWrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories,CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}

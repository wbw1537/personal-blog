package com.wbw1537.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.CategoryVo;
import com.wbw1537.domain.vo.ExcelCategoryVo;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.mapper.CategoryMapper;
import com.wbw1537.service.ArticleService;
import com.wbw1537.service.CategoryService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
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
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;


    //注意： ①要求只展示有发布正式文章的分类 ②必须是正常状态的分类
    @Override
    public List<CategoryVo> getCategoryList() {
        //查询已发布文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleMapper.selectList(articleLambdaQueryWrapper);
        //获取文章分类id（去重）1
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //根据分类id取得分类名称
        List<Category> categories = categoryMapper.selectBatchIds(categoryIds);

        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        return BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
    }


    @Override
    public List<CategoryVo> listAllCategory() {
        // 根据分类状态进行查询
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus,SystemConstants.STATUS_NORMAL);
        // 封装数据
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return BeanCopyUtils.copyBeanList(categories,CategoryVo.class);
    }

    @Override
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    public ResponseResult exportCategory(HttpServletResponse response) {
        try {
            // 设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 获取需要导出的数据
            List<Category> category = categoryMapper.selectList(null);
            // 转换数据类型
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(category, ExcelCategoryVo.class);
            // 导出数据
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出").doWrite(excelCategoryVos);
            return ResponseResult.okResult();
        } catch (Exception e) {
            throw new SystemException(AppHttpCodeEnum.DOWNLOAD_ERROR,e.getMessage());
        }
    }
}

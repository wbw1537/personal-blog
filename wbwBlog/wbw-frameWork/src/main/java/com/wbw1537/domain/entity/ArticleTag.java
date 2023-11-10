package com.wbw1537.domain.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author makejava
 * @since 2023-11-11 00:42:43
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article_tag")
public class ArticleTag {
//文章id
    private Long articleId;
//标签id
    private Long tagId;


    /**
     * 获取主键值
     *
     * @return 主键值
     */

    /**
     * 获取主键值
     *
     * @return 主键值
     */

}


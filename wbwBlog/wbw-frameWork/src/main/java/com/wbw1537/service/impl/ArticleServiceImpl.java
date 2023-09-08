package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}

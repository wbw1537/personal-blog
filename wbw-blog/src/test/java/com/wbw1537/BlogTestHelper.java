package com.wbw1537;

import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.User;

import java.util.ArrayList;

public class BlogTestHelper {
  public static final String ACCESS_ARTICLE_API_PATH = "/article/";
  public static final String ACCESS_HOT_ARTICLE_API_PATH = "/article/hotArticleList/";
  public static final AddArticleDto addArticleDto = new AddArticleDto("title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final UpdateArticleDto updateArticleDto = new UpdateArticleDto(66L,"title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final User adminUser = new User(1L,"userName","nickName","password","1" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final User normalUser = new User(2L,"userName","nickName","password","0" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
}

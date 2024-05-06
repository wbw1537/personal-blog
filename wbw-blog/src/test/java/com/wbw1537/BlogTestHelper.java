package com.wbw1537;

import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.LoginUser;
import com.wbw1537.domain.entity.User;

import java.util.ArrayList;

public class BlogTestHelper {
  public static final String ACCESS_ARTICLE_API_PATH = "/article/";
  public static final String ACCESS_HOT_ARTICLE_API_PATH = "/article/hotArticleList/";
  public static final AddArticleDto ADD_ARTICLE_DTO = new AddArticleDto("title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final UpdateArticleDto UPDATE_ARTICLE_DTO = new UpdateArticleDto(66L,"title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final User ADMIN_USER = new User(1L,"userName","nickName","password","1" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final User NORMAL_USER = new User(2L,"userName","nickName","password","0" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final UserLoginDto USER_LOGIN_DTO = new UserLoginDto("userName","password");
  public static final LoginUser LOGIN_USER = new LoginUser(BlogTestHelper.NORMAL_USER, new ArrayList<>());
  public static final UserLoginDto USER_LOGIN_DTO_WITHOUT_USERNAME = new UserLoginDto("","password");
}

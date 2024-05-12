package com.wbw1537;

import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.*;
import com.wbw1537.domain.vo.CategoryVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class BlogTestHelper {
  public static final String ARTICLE_API_PATH = "/article/";
  public static final String HOT_ARTICLE_API_PATH = "/article/hotArticleList/";
  public static final String LOGIN_API_PATH = "/login";
  public static final String LOGOUT_API_PATH = "/logout";
  public static final String GET_CATEGORY_LIST_API_PATH = "/category/getCategoryList";
  public static final String COMMENT_LIST_API_PATH = "/comment/commentList";
  public static final String LINK_COMMENT_LIST_API_PATH = "/comment/linkCommentList";
  public static final String ADD_COMMENT_API_PATH = "/comment";
  public static final String GET_LINK_LIST_API_PATH = "/link/getAllLink";
  public static final AddArticleDto ADD_ARTICLE_DTO = new AddArticleDto("title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final UpdateArticleDto UPDATE_ARTICLE_DTO = new UpdateArticleDto(66L,"title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final User ADMIN_USER = new User(1L,"userName","nickName","password","1" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final User NORMAL_USER = new User(2L,"userName","nickName","password","0" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final UserLoginDto USER_LOGIN_DTO = new UserLoginDto("userName","password");
  public static final LoginUser LOGIN_USER = new LoginUser(BlogTestHelper.NORMAL_USER, new ArrayList<>());
  public static final UserLoginDto USER_LOGIN_DTO_WITHOUT_USERNAME = new UserLoginDto("","password");
  public static final List<CategoryVo> CATEGORY_VO_LIST = new ArrayList<>();
  public static final ResponseEntity<List<CategoryVo>> CATEGORY_VO_LIST_RESPONSE = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
  public static final Article ARTICLE = new Article(1L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,0);
  public static final Article DELETED_ARTICLE = new Article(2L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,1);
  public static final List<Article> ARTICLE_LIST = getArticleList();
  public static final Category CATEGORY = new Category(1L,"name",-1L,"description","status",null,null,null,null,0);
  public static final Category DELETED_CATEGORY = new Category(2L,"name",-1L,"description","status",null,null,null,null,1);
  public static final List<Category> CATEGORY_LIST = getCategoryList();
  public static final Comment ARTICLE_COMMENT = new Comment(1L,"0",1L,1L,"content",2L,1L,null,null,null,null,0);
  public static final Comment LINK_COMMENT = new Comment(2L,"1",1L,1L,"content",2L,2L,null,null,null,null,0);


  private static List<Article> getArticleList(){
    List<Article> articleList = new ArrayList<>();
    articleList.add(ARTICLE);
    // articleList.add(DELETED_ARTICLE);
    return articleList;
  }
  private static List<Category> getCategoryList(){
    List<Category> categories = new ArrayList<>();
    categories.add(CATEGORY);
    // categories.add(DELETED_CATEGORY);
    return categories;
  }
}

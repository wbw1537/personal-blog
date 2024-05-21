package com.wbw1537;

import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.*;
import com.wbw1537.domain.vo.CategoryVo;
import com.wbw1537.domain.vo.UserInfoVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.naming.InterruptedNamingException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BlogTestHelper {
  public static final String ARTICLE_API_PATH = "/article";
  public static final String ARTICLE_LIST_API_PATH = "/article/articleList";
  public static final String HOT_ARTICLE_API_PATH = "/article/hotArticleList";
  public static final String UPDATE_VIEW_COUNT_API_PATH = "/article/updateViewCount";
  public static final String LOGIN_API_PATH = "/login";
  public static final String LOGOUT_API_PATH = "/logout";
  public static final String GET_CATEGORY_LIST_API_PATH = "/category/getCategoryList";
  public static final String COMMENT_LIST_API_PATH = "/comment/commentList";
  public static final String LINK_COMMENT_LIST_API_PATH = "/comment/linkCommentList";
  public static final String ADD_COMMENT_API_PATH = "/comment";
  public static final String GET_LINK_LIST_API_PATH = "/link/getAllLink";
  public static final String UPLOAD_IMAGE_API_PATH = "/upload";
  public static final String USER_INFO_API_PATH = "/user/userInfo";
  public static final String REGISTER_API_PATH = "/user/register";
  public static final User ADMIN_USER = new User(1L,"userName","nickName","password","1" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final User NORMAL_USER = new User(2L,"userName","nickName","password","0" ,"0" ,"email","phoneNumber","0","avatar",null,null,null,null,0);
  public static final UserLoginDto USER_LOGIN_DTO = new UserLoginDto("userName","password");
  public static final LoginUser LOGIN_USER = new LoginUser(BlogTestHelper.NORMAL_USER, new ArrayList<>());
  public static final UserLoginDto USER_LOGIN_DTO_WITHOUT_USERNAME = new UserLoginDto("", "password");
  public static final List<Category> CATEGORY_LIST = Arrays.asList(
          new Category(1L,"name",-1L,"description","status",1L,new Date(),1L,new Date(),0),
          new Category(2L,"name",-1L,"description","status",2L,new Date(),2L,new Date(),0)
  );
  public static final Article ARTICLE = new Article(1L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,0);
  public static final Article DELETED_ARTICLE = new Article(2L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,1);
  public static final List<Article> ARTICLE_LIST = Arrays.asList(
          new Article(1L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,0),
          new Article(2L,"title","content","summary",2L,"categoryName","thumbnail","isTop","status",2L,"isComment",null,null,null,null,0)
  );
  public static final Category CATEGORY = new Category(1L,"name",-1L,"description","status",null,null,null,null,0);
  public static final Category DELETED_CATEGORY = new Category(2L,"name",-1L,"description","status",null,null,null,null,1);
  public static final Comment ARTICLE_COMMENT = new Comment(1L,"0",1L,1L,"content",2L,1L,null,null,null,null,0);
  public static final Comment LINK_COMMENT = new Comment(2L,"1",1L,1L,"content",2L,2L,null,null,null,null,0);
  public static final MockMultipartFile IMG_FILE = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());
  public static final String UPLOAD_RETURN_URL = "http://localhost:8080/upload/2021/07/01/1.jpg";
}

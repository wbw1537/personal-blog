package com.wbw1537;

import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.TagDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.vo.*;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminBlogTestHelper {
  public static final String LIST_ALL_CATEGORY_API_PATH = "/content/category/listAllCategory";
  public static final String EXPORT_CATEGORY_API_PATH = "/content/category/export";
  public static final String ADMIN_LOGIN_API_PATH = "/user/login";
  public static final String LOGOUT_API_PATH = "/user/logout";
  public static final String GET_INFO_API_PATH = "/getInfo";
  public static final String GET_ROUTERS_API_PATH = "/getRouters";
  public static final String UPLOAD_IMAGE_API_PATH = "/upload";
  public static final String SYSTEM_MENU_LIST_API_PATH = "/system/menu/list";
  public static final String SYSTEM_MENU_API_PATH = "/system/menu";
  public static final String TAG_LIST_API_PATH = "/content/tag/list";
  public static final String TAG_LIST_ALL_API_PATH = "/content/tag/listAllTags";
  public static final String TAG_API_PATH = "/content/tag";
  public static final String ADMIN_ARTICLE_API_PATH = "/content/article";
  public static final String SEARCH_ARTICLE_LIST_API_PATH = "/content/article/list";
  public static final List<Category> CATEGORY_LIST = Arrays.asList(
          new Category(1L,"name",-1L,"description","status",1L,new Date(),1L,new Date(),0),
          new Category(2L,"name",-1L,"description","status",2L,new Date(),2L,new Date(),0)
  );
  public static final Category CATEGORY = new Category(1L,"name",-1L,"description","status",1L,new Date(),1L,new Date(),0);
  public static final UserLoginDto USER_LOGIN_DTO_WITHOUT_USERNAME = new UserLoginDto("","password");
  public static final UserLoginDto USER_LOGIN_DTO = new UserLoginDto("userName","password");
  public static final AdminUserInfoVo ADMIN_USER_INFO_VO = new AdminUserInfoVo();
  public static final RoutersVo ROUTERS_VO = new RoutersVo();
  public static final MockMultipartFile IMG_FILE = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test data".getBytes());
  public static final String UPLOAD_RETURN_URL = "http://localhost:8080/upload/2021/07/01/1.jpg";
  public static final List<String> PERMS = getPerms();
  public static final List<Menu> MENU_LIST = getMenuList();
  public static final Menu MENU = new Menu();
  public static final TagDto TAG_DTO = new TagDto("name","remark",1L);
  public static final PageVo SEARCH_TAG_RESULT = new PageVo();
  public static final List<TagVo> TAG_VO_LIST = new ArrayList<>();
  public static final AddArticleDto ADD_ARTICLE_DTO = new AddArticleDto("title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final UpdateArticleDto UPDATE_ARTICLE_DTO = new UpdateArticleDto(66L,"title","content","summary",1L,"thumbnail","isTop","status","isComment", new ArrayList<>());
  public static final Article ARTICLE = new Article(1L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,0);
  public static final List<Article> ARTICLE_LIST = Arrays.asList(
          new Article(1L,"title","content","summary",1L,"categoryName","thumbnail","isTop","status",1L,"isComment",null,null,null,null,0),
          new Article(2L,"title","content","summary",2L,"categoryName","thumbnail","isTop","status",2L,"isComment",null,null,null,null,0)
  );


  private static List<Menu> getMenuList() {
    List<Menu> menus = new ArrayList<>();
    menus.add(new Menu());
    return menus;
  }

  private static List<String> getPerms(){
    List<String> perms = new ArrayList<>();
    perms.add("system:user:list");
    perms.add("system:role:list");
    perms.add("system:menu:list");
    return perms;
  }
}

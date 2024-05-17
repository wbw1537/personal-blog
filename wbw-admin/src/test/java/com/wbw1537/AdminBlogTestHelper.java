package com.wbw1537;

import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.AdminUserInfoVo;
import com.wbw1537.domain.vo.RoutersVo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AdminBlogTestHelper {
  public static final String LIST_ALL_CATEGORY_API_PATH = "/content/category/listAllCategory";
  public static final String EXPORT_CATEGORY_API_PATH = "/content/category/export";
  public static final String ADMIN_LOGIN_API_PATH = "/user/login";
  public static final String LOGOUT_API_PATH = "/user/logout";
  public static final String GET_INFO_API_PATH = "/getInfo";
  public static final String GET_ROUTERS_API_PATH = "/getRouters";
  public static final List<Category> CATEGORY_LIST = getCategoryList();
  public static final Category CATEGORY = new Category(1L,"name",-1L,"description","status",null,null,null,null,0);
  public static final UserLoginDto USER_LOGIN_DTO_WITHOUT_USERNAME = new UserLoginDto("","password");
  public static final UserLoginDto USER_LOGIN_DTO = new UserLoginDto("userName","password");
  public static final AdminUserInfoVo ADMIN_USER_INFO_VO = new AdminUserInfoVo();
  public static final RoutersVo ROUTERS_VO = new RoutersVo();


  private static List<Category> getCategoryList(){
    List<Category> categories = new ArrayList<>();
    categories.add(CATEGORY);
    // categories.add(DELETED_CATEGORY);
    return categories;
  }
}

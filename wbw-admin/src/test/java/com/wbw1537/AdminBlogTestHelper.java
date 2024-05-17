package com.wbw1537;

import com.wbw1537.domain.entity.Category;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AdminBlogTestHelper {
  public static final String LIST_ALL_CATEGORY_API_PATH = "/content/category/listAllCategory";
  public static final String EXPORT_CATEGORY_API_PATH = "/content/category/export";
  public static final List<Category> CATEGORY_LIST = getCategoryList();
  public static final Category CATEGORY = new Category(1L,"name",-1L,"description","status",null,null,null,null,0);

  private static List<Category> getCategoryList(){
    List<Category> categories = new ArrayList<>();
    categories.add(CATEGORY);
    // categories.add(DELETED_CATEGORY);
    return categories;
  }
}

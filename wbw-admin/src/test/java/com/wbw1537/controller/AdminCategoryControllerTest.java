package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.WbwAdminApplication;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.CategoryVo;
import com.wbw1537.service.CategoryService;
import com.wbw1537.service.impl.PermissionService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.WebUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.wbw1537.AdminBlogTestHelper;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwAdminApplication.class)
public class AdminCategoryControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private CategoryService mockCategoryService;
  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void getCategoryListShouldReturnOkResult() throws Exception {
    List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(AdminBlogTestHelper.CATEGORY_LIST, CategoryVo.class);
    when(mockCategoryService.getCategoryList()).thenReturn(new ResponseEntity(categoryVos, HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.LIST_ALL_CATEGORY_API_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void exportCategoryShouldReturnOkResult() throws Exception {
    when(mockCategoryService.exportCategory(any())).thenReturn(new ResponseEntity(HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.EXPORT_CATEGORY_API_PATH))
        .andExpect(status().isOk());
  }
}

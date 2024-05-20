package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.AdminBlogTestHelper;
import com.wbw1537.WbwAdminApplication;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.vo.MenuListVo;
import com.wbw1537.service.MenuService;
import com.wbw1537.utils.BeanCopyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwAdminApplication.class)
public class MenuControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private MenuService mockMenuService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void getSystemMenuListShouldReturnOkResult() throws Exception {
    List<Menu> mockMenuList = AdminBlogTestHelper.MENU_LIST;
    List<MenuListVo> mockMenuVoList = BeanCopyUtils.copyBeanList(mockMenuList, MenuListVo.class);
    when(mockMenuService.searchSystemMenuList(any(), any()))
            .thenReturn(mockMenuVoList);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.SYSTEM_MENU_LIST_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(result -> mockMenuVoList.equals(result.getResponse()));
  }

  @Test
  public void addSystemMenuShouldReturnOkResult() throws Exception {
    Menu mockMenu = AdminBlogTestHelper.MENU;
    when(mockMenuService.addSystemMenu(any()))
            .thenReturn(ResponseResult.okResult());
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.SYSTEM_MENU_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockMenu)))
            .andExpect(status().isOk());
  }
}

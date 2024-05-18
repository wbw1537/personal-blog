package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.AdminBlogTestHelper;
import com.wbw1537.WbwAdminApplication;
import com.wbw1537.domain.dto.AddMenuDto;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.vo.MenuVo;
import com.wbw1537.service.MenuService;
import com.wbw1537.utils.BeanCopyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    List<MenuVo> mockMenuVoList = BeanCopyUtils.copyBeanList(mockMenuList, MenuVo.class);
    when(mockMenuService.getSystemMenuList(any(), any()))
            .thenReturn(new ResponseEntity<>(mockMenuVoList, HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.SYSTEM_MENU_LIST_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void addSystemMenuWithoutMenuShouldReturnIllegalArgumentException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.SYSTEM_MENU_API_PATH))
            .andExpect(status().is4xxClientError());
  }
  @Test
  public void addSystemMenuShouldReturnOkResult() throws Exception {
    Menu mockMenu = AdminBlogTestHelper.MENU;
    when(mockMenuService.addSystemMenu(any()))
            .thenReturn(new ResponseEntity<>(mockMenu, HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.SYSTEM_MENU_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(mockMenu)))
            .andExpect(status().isOk());
  }
}
